package com.zagirlek.rickandmortytest.ui.screen.details.vm

sealed class CharacterDetailsAction {
    data class FetchData(val characterId: Int): CharacterDetailsAction()
}