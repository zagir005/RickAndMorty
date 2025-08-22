package com.zagirlek.rickandmortytest.ui.screen.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.zagirlek.rickandmortytest.ui.screen.characters.cmp.CharactersList
import com.zagirlek.rickandmortytest.ui.screen.details.cmp.CharacterDetails

interface RootComponent {
    val state: Value<ChildStack<*, Child>>

    sealed class Child{
        data class CharactersListChild(val charactersList: CharactersList): Child()

        data class CharacterDetailsChild(val characterDetails: CharacterDetails): Child()
    }


}