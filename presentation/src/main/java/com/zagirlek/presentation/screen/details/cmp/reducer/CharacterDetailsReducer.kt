package com.zagirlek.presentation.screen.details.cmp.reducer

import com.zagirlek.rickandmortytest.core.cmp.reducer.Reducer
import com.zagirlek.rickandmortytest.ui.screen.details.cmp.state.CharacterDetailsState

class CharacterDetailsReducer: Reducer<com.zagirlek.presentation.screen.details.cmp.state.CharacterDetailsState, CharacterDetailsMutation> {

    override fun reduce(
        currentState: com.zagirlek.presentation.screen.details.cmp.state.CharacterDetailsState,
        mutation: CharacterDetailsMutation
    ): com.zagirlek.presentation.screen.details.cmp.state.CharacterDetailsState {

        return when(mutation){
            is CharacterDetailsMutation.CharacterLoaded -> currentState.copy(
                character = mutation.character,
                loading = false
            )
            CharacterDetailsMutation.OnLoading -> currentState.copy(
                loading = true
            )
            CharacterDetailsMutation.CharacterNotFoundError -> currentState.copy(
                loading = false,
                onError = true
            )
        }
    }
}