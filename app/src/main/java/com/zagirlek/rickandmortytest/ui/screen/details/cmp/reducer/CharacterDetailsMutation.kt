package com.zagirlek.rickandmortytest.ui.screen.details.cmp.reducer

import com.zagirlek.rickandmortytest.core.cmp.reducer.Mutation
import com.zagirlek.rickandmortytest.domain.model.Character

sealed class CharacterDetailsMutation: Mutation{
    object OnLoading: CharacterDetailsMutation()

    data class CharacterLoaded(val character: Character): CharacterDetailsMutation()

    object CharacterNotFoundError: CharacterDetailsMutation()
}