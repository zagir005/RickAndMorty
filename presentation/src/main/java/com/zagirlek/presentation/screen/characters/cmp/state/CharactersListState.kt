package com.zagirlek.presentation.screen.characters.cmp.state

import androidx.paging.PagingData
import com.zagirlek.core.cmp.ViewState
import com.zagirlek.core.model.CharacterFilters
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import com.zagirlek.core.model.Character

data class CharactersListState(
    val charactersPagingItems: Flow<PagingData<Character>> = emptyFlow<PagingData<Character>>(),
    val isFilterSheetShown: Boolean = false,
    val characterFilters: CharacterFilters = CharacterFilters(),
    val search: String? = null,
    val loading: Boolean = false
): ViewState