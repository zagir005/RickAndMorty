package com.zagirlek.rickandmortytest.domain.repository

import androidx.paging.PagingData
import com.zagirlek.rickandmortytest.domain.model.CharacterFilters
import com.zagirlek.rickandmortytest.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersPagingRepository {

    fun getFilterPaginatedCharactersList(
        characterFilters: CharacterFilters = CharacterFilters()
    ): Flow<PagingData<Character>>

}