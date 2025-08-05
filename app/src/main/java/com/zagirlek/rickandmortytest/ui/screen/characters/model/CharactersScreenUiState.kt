package com.zagirlek.rickandmortytest.ui.screen.characters.model

import com.zagirlek.rickandmortytest.domain.model.Character

data class CharactersScreenUiState(
    val loading: Boolean = false,
    val error: Throwable? = null,
    val charactersList: List<Character> = emptyList<Character>()
){
    fun isLoading() = loading && error == null

    fun isError() = error != null && !loading

    fun isListLoaded() = charactersList.isNotEmpty()
}
