package com.zagirlek.rickandmortytest.ui.screen.details.vm.reducer

import com.zagirlek.rickandmortytest.core.vm.reducer.Reducer
import com.zagirlek.rickandmortytest.ui.screen.details.elements.state.CharacterDetailsState

class CharacterDetailsReducer: Reducer<CharacterDetailsState, CharacterDetailsMutation> {

    override fun reduce(
        currentState: CharacterDetailsState,
        mutation: CharacterDetailsMutation
    ): CharacterDetailsState {

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