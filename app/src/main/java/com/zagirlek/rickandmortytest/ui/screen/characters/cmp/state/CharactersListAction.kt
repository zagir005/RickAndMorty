package com.zagirlek.rickandmortytest.ui.screen.characters.cmp.state

import com.zagirlek.rickandmortytest.core.cmp.Action
import com.zagirlek.rickandmortytest.domain.model.CharacterFilters

sealed class CharactersListAction: Action {
    data class LoadFilterCharactersList(val filter: CharacterFilters = CharacterFilters()): CharactersListAction()

    object HideFilterBottomSheet: CharactersListAction()

    object ShowFilterBottomSheet: CharactersListAction()

    object ResetFilter: CharactersListAction()

    data class ItemClick(val characterId: Int): CharactersListAction()

    data class Search(val name: String): CharactersListAction()

    data class ResetSearch(val name: String): CharactersListAction()


}