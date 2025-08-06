package com.zagirlek.rickandmortytest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.zagirlek.rickandmortytest.data.mapper.toDomain
import com.zagirlek.rickandmortytest.data.network.service.CharacterService
import com.zagirlek.rickandmortytest.data.network.utils.CharactersFilters
import com.zagirlek.rickandmortytest.data.paging.ApiPagingSource
import com.zagirlek.rickandmortytest.domain.model.Character
import com.zagirlek.rickandmortytest.domain.repository.CharactersPagingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharactersPagingRepositoryImpl(
    private val charactersService: CharacterService
): CharactersPagingRepository{
    override fun getFilterPaginatedCharactersList(
        charactersFilters: CharactersFilters
    ): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {
                ApiPagingSource(
                    charactersService,
                    charactersFilters
                )
            }
        ).flow
            .map {
                it.map { characterDTO ->
                    characterDTO.toDomain()
                }
            }
    }
}