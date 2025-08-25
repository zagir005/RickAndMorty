package com.zagirlek.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.zagirlek.core.local.CharacterDatabase
import com.zagirlek.core.model.Character
import com.zagirlek.core.model.CharacterFilters
import com.zagirlek.core.network.service.CharacterService
import com.zagirlek.data.mapper.toDomain
import com.zagirlek.data.paging.CharacterRemoteMediator
import com.zagirlek.domain.repository.PagingCharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class PagingCharacterRepositoryImpl(
    private val characterDatabase: CharacterDatabase,
    private val characterService: CharacterService
): PagingCharacterRepository {
    override fun getPagedFlowCharacters(characterFilters: CharacterFilters): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                initialLoadSize = 20
            ),
            remoteMediator = CharacterRemoteMediator(
                filters = characterFilters,
                database = characterDatabase,
                network = characterService
            ),
            pagingSourceFactory = { characterDatabase.characterDao().getCharactersPagingSource(
                name = characterFilters.name,
                status = characterFilters.status,
                species = characterFilters.species,
                gender = characterFilters.gender
            ) }
        ).flow.map {
            it.map { character ->
                character.toDomain()
            }
        }
    }

}