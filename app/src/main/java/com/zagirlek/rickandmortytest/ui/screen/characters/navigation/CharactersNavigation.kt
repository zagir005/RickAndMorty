package com.zagirlek.rickandmortytest.ui.screen.characters.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zagirlek.rickandmortytest.ui.screen.characters.CharactersListScreen
import kotlinx.serialization.Serializable

@Serializable
object CharactersScreen

fun NavGraphBuilder.charactersScreen(
    modifier: Modifier = Modifier,
    toDetails: (id: Int) -> Unit,
){
    composable<CharactersScreen> {
        CharactersListScreen(
            toDetails = {
                toDetails(it)
            },
            modifier = modifier
        )
    }
}