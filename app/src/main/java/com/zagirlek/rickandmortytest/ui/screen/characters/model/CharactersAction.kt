package com.zagirlek.rickandmortytest.ui.screen.characters.model

import androidx.paging.compose.LazyPagingItems
import com.zagirlek.rickandmortytest.domain.model.CharacterFilters

sealed class CharactersAction {
    data class LoadCharacters(val filter: CharacterFilters = CharacterFilters()): CharactersAction()

    object HideFilterBottomSheet: CharactersAction()

    object ShowFilterBottomSheet: CharactersAction()

    data class SearchByName(val name: String): CharactersAction()
}