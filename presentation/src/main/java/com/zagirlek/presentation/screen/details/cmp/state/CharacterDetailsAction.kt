package com.zagirlek.presentation.screen.details.cmp.state

import com.zagirlek.core.cmp.Action

sealed class CharacterDetailsAction: Action {
    data class FetchData(val characterId: Int) : CharacterDetailsAction()

    object BackToList: CharacterDetailsAction()
}