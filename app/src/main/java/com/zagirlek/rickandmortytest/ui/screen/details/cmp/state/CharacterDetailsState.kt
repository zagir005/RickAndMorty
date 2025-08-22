package com.zagirlek.rickandmortytest.ui.screen.details.cmp.state

import com.zagirlek.rickandmortytest.core.cmp.ViewState
import com.zagirlek.rickandmortytest.domain.model.Character

data class CharacterDetailsState(
    val character: Character = Character.emptyCharacter(),
    val loading: Boolean = false,
    val onError: Boolean = false
): ViewState{

}
