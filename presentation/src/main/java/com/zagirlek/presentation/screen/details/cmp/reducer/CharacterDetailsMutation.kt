package com.zagirlek.presentation.screen.details.cmp.reducer

import com.zagirlek.core.cmp.reducer.Mutation
import com.zagirlek.core.model.Character


sealed class CharacterDetailsMutation: Mutation {
    object OnLoading: CharacterDetailsMutation()

    data class CharacterLoaded(val character: Character): CharacterDetailsMutation()

    object CharacterNotFoundError: CharacterDetailsMutation()

}