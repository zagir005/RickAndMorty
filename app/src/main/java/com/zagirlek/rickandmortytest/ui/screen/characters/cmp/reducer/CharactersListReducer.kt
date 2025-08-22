package com.zagirlek.rickandmortytest.ui.screen.characters.cmp.reducer

import com.zagirlek.rickandmortytest.core.cmp.reducer.Reducer
import com.zagirlek.rickandmortytest.domain.model.CharacterFilters
import com.zagirlek.rickandmortytest.ui.screen.characters.cmp.state.CharactersListState

class CharactersListReducer: Reducer<CharactersListState, CharactersListMutation> {
    override fun reduce(currentState: CharactersListState, mutation: CharactersListMutation): CharactersListState {
        return when(mutation){
            is CharactersListMutation.CharactersListByEmptySearch -> currentState.copy(
                charactersPagingItems = mutation.characters,
                search = null
            )
            is CharactersListMutation.CharactersListBySearch -> currentState.copy(
                charactersPagingItems = mutation.characters,
                search = mutation.query
            )
            CharactersListMutation.HideFilterBottomSheet -> currentState.copy(
                isFilterSheetShown = false
            )
            CharactersListMutation.ShowFilterBottomSheet -> currentState.copy(
                isFilterSheetShown = true
            )
            is CharactersListMutation.LoadCharactersList -> currentState.copy(
                charactersPagingItems = mutation.characters,
                isFilterSheetShown = false,
                characterFilters = mutation.filter
            )
            is CharactersListMutation.ResetFilterCharactersList -> currentState.copy(
                charactersPagingItems = mutation.characters,
                characterFilters = CharacterFilters()
            )
        }
    }

}