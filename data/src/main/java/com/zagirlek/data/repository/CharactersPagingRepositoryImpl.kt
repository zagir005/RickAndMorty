package com.zagirlek.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.zagirlek.data.local.CharacterDatabase
import com.zagirlek.data.network.service.CharacterService
import com.zagirlek.data.paging.CharacterRemoteMediator
import com.zagirlek.core.model.CharacterFilters
import com.zagirlek.domain.repository.CharactersPagingRepository
import com.zagirlek.data.mapper.toDomain
import com.zagirlek.core.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharactersPagingRepositoryImpl(
    private val charactersService: CharacterService,
    private val characterDatabase: CharacterDatabase
): CharactersPagingRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getFilterPaginatedCharactersList(
        characterFilters: CharacterFilters
    ): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                initialLoadSize = 20
            ),
            remoteMediator = CharacterRemoteMediator(
                filters = characterFilters,
                database = characterDatabase,
                network = charactersService
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