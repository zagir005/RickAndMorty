package com.zagirlek.presentation.screen.details.cmp.state

import com.zagirlek.rickandmortytest.core.cmp.ViewState

data class CharacterDetailsState(
    val character: Character = Character.emptyCharacter(),
    val loading: Boolean = false,
    val onError: Boolean = false
): ViewState
