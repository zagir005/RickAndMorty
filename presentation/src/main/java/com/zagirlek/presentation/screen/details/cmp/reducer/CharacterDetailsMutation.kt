package com.zagirlek.presentation.screen.details.cmp.reducer

import com.zagirlek.rickandmortytest.core.cmp.reducer.Mutation

sealed class CharacterDetailsMutation: Mutation{
    object OnLoading: CharacterDetailsMutation()

    data class CharacterLoaded(val character: Character): CharacterDetailsMutation()

    object CharacterNotFoundError: CharacterDetailsMutation()

}