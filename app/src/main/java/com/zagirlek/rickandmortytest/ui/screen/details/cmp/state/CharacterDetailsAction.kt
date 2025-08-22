package com.zagirlek.rickandmortytest.ui.screen.details.cmp.state

import com.zagirlek.rickandmortytest.core.cmp.Action

sealed class CharacterDetailsAction: Action {
    data class FetchData(val characterId: Int) : CharacterDetailsAction()
}