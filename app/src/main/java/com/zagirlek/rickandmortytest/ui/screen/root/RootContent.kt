package com.zagirlek.rickandmortytest.ui.screen.root

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.zagirlek.rickandmortytest.ui.screen.characters.CharactersListUi
import com.zagirlek.rickandmortytest.ui.screen.details.CharacterDetailsUi

@Composable
fun RootContent(rootComponent: RootComponent) {
    Children(
        stack = rootComponent.state,
        animation = stackAnimation(animator = fade() + scale()),
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.CharacterDetailsChild -> CharacterDetailsUi(
                characterDetailsComponent = child.characterDetails
            )
            is RootComponent.Child.CharactersListChild -> CharactersListUi(
                component = child.charactersList
            )
        }
    }
}