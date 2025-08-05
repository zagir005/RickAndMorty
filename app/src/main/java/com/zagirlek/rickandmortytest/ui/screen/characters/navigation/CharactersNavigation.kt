package com.zagirlek.rickandmortytest.ui.screen.characters.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zagirlek.rickandmortytest.ui.screen.characters.CharactersScreen
import kotlinx.serialization.Serializable

@Serializable
object CharactersScreen

fun NavGraphBuilder.charactersScreen(
    toDetails: (id: Int) -> Unit
){
    composable<CharactersScreen> {
        CharactersScreen(
            toDetails = {
                toDetails(it)
            }
        )
    }
}