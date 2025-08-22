package com.zagirlek.rickandmortytest.ui.screen.characters.cmp.reducer

import androidx.paging.PagingData
import com.zagirlek.rickandmortytest.core.cmp.reducer.Mutation
import com.zagirlek.rickandmortytest.domain.model.Character
import com.zagirlek.rickandmortytest.domain.model.CharacterFilters
import kotlinx.coroutines.flow.Flow

sealed class CharactersListMutation: Mutation {

    data class LoadCharactersList(
        val characters: Flow<PagingData<Character>>,
        val filter: CharacterFilters
    ): CharactersListMutation()

    data class ResetFilterCharactersList(
        val characters: Flow<PagingData<Character>>
    ): CharactersListMutation()

    data class CharactersListBySearch(
        val characters: Flow<PagingData<Character>>,
        val query: String
    ): CharactersListMutation()

    data class CharactersListByEmptySearch(
        val characters: Flow<PagingData<Character>>
    ): CharactersListMutation()

    object ShowFilterBottomSheet: CharactersListMutation()

    object HideFilterBottomSheet: CharactersListMutation()


}