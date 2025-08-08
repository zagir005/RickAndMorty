package com.zagirlek.rickandmortytest.ui.screen.characters

import com.zagirlek.rickandmortytest.domain.model.CharacterFilters

data class CharactersScreenUiState(
    val loading: Boolean = false,
    val error: Throwable? = null,
    val isFilterBottomSheetVisible: Boolean = false,
    val characterFilters: CharacterFilters = CharacterFilters()
){
    fun isLoading() = loading && error == null

    fun isError() = error != null && !loading


}