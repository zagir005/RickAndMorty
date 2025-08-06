package com.zagirlek.rickandmortytest.domain.repository

import androidx.paging.PagingData
import com.zagirlek.rickandmortytest.domain.model.CharactersFilters
import com.zagirlek.rickandmortytest.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersPagingRepository {

    fun getFilterPaginatedCharactersList(
        charactersFilters: CharactersFilters = CharactersFilters()
    ): Flow<PagingData<Character>>

}