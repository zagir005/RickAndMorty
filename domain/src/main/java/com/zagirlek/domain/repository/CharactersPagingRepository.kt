package com.zagirlek.domain.repository

import androidx.paging.PagingData
import com.zagirlek.core.model.Character
import com.zagirlek.core.model.CharacterFilters
import kotlinx.coroutines.flow.Flow

interface CharactersPagingRepository {

    fun getFilterPaginatedCharactersList(
        characterFilters: CharacterFilters = CharacterFilters(),
    ): Flow<PagingData<Character>>

}