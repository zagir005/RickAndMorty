package com.zagirlek.presentation.screen.details.cmp.reducer

import com.zagirlek.core.cmp.reducer.Reducer
import com.zagirlek.presentation.screen.details.cmp.state.CharacterDetailsState


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