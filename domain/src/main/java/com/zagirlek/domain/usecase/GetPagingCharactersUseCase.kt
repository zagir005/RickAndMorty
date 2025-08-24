package com.zagirlek.domain.usecase

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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class GetPagingCharactersUseCase(
    private val charactersService: CharacterService,
    private val characterDatabase: CharacterDatabase,
) {

    suspend fun invoke(filter: CharacterFilters): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                initialLoadSize = 20
            ),
            remoteMediator = CharacterRemoteMediator(
                filters = filter,
                database = characterDatabase,
                network = charactersService
            ),
            pagingSourceFactory = { characterDatabase.characterDao().getCharactersPagingSource(
                name = filter.name,
                status = filter.status,
                species = filter.species,
                gender = filter.gender
            ) }
        ).flow.map {
            it.map { character ->
                character.toDomain()
            }
        }
    }
}