package com.zagirlek.presentation.screen.details.cmp.state

import com.zagirlek.core.cmp.ViewState
import com.zagirlek.core.model.Character

data class CharacterDetailsState(
    val character: Character = Character.emptyCharacter(),
    val loading: Boolean = false,
    val onError: Boolean = false
): ViewState
