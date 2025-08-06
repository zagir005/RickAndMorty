package com.zagirlek.rickandmortytest.ui.screen.characters.model

import com.zagirlek.rickandmortytest.domain.model.CharactersFilters
import com.zagirlek.rickandmortytest.domain.model.Character

data class CharactersScreenUiState(
    val loading: Boolean = false,
    val error: Throwable? = null,
    val charactersFilters: CharactersFilters = CharactersFilters()
){
    fun isLoading() = loading && error == null

    fun isError() = error != null && !loading


}
