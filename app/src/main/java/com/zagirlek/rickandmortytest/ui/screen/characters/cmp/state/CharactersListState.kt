package com.zagirlek.rickandmortytest.ui.screen.characters.cmp.state

import androidx.paging.PagingData
import com.zagirlek.rickandmortytest.core.cmp.ViewState
import com.zagirlek.rickandmortytest.domain.model.Character
import com.zagirlek.rickandmortytest.domain.model.CharacterFilters
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CharactersListState(
    val charactersPagingItems: Flow<PagingData<Character>> = emptyFlow<PagingData<Character>>(),
    val isFilterSheetShown: Boolean = false,
    val characterFilters: CharacterFilters = CharacterFilters(),
    val search: String? = null
): ViewState