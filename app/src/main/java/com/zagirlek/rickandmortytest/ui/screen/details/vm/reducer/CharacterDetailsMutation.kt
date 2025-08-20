package com.zagirlek.rickandmortytest.ui.screen.details.vm.reducer

import com.zagirlek.rickandmortytest.core.vm.reducer.Mutation
import com.zagirlek.rickandmortytest.domain.model.Character

sealed class CharacterDetailsMutation: Mutation{
    object OnLoading: CharacterDetailsMutation()

    data class CharacterLoaded(val character: Character): CharacterDetailsMutation()

    object CharacterNotFoundError: CharacterDetailsMutation()
}