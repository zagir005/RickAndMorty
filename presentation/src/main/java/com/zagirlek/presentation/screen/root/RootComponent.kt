package com.zagirlek.presentation.screen.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.zagirlek.presentation.screen.characters.cmp.CharactersListComponent
import com.zagirlek.presentation.screen.details.cmp.CharacterDetailsComponent

interface RootComponent {
    val state: Value<ChildStack<*, Child>>

    sealed class Child{
        data class CharactersListChild(val charactersListComponent: CharactersListComponent): Child()

        data class CharacterDetailsChild(val characterDetailsComponent: CharacterDetailsComponent): Child()
    }
}