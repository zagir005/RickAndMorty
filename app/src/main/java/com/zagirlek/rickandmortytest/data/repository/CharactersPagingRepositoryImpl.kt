package com.zagirlek.rickandmortytest.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.zagirlek.rickandmortytest.data.local.CharacterDatabase
import com.zagirlek.rickandmortytest.data.local.entities.CharacterEntity
import com.zagirlek.rickandmortytest.data.mapper.toDomain
import com.zagirlek.rickandmortytest.data.network.service.CharacterService
import com.zagirlek.rickandmortytest.domain.model.CharacterFilters
import com.zagirlek.rickandmortytest.data.paging.CharacterRemoteMediator
import com.zagirlek.rickandmortytest.domain.model.Character
import com.zagirlek.rickandmortytest.domain.repository.CharactersPagingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharactersPagingRepositoryImpl(
    private val charactersService: CharacterService,
    private val characterDatabase: CharacterDatabase
): CharactersPagingRepository{

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
            it.map { entity ->
                entity.toDomain()
            }
        }

    }
}