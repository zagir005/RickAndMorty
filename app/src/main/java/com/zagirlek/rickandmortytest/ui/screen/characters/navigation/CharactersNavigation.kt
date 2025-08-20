package com.zagirlek.rickandmortytest.ui.screen.characters.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zagirlek.rickandmortytest.ui.screen.characters.CharactersListUi
import kotlinx.serialization.Serializable

@Serializable
object CharactersScreen

fun NavGraphBuilder.charactersList(
    modifier: Modifier = Modifier,
    topAppBar: (@Composable () -> Unit) -> Unit,
    toDetails: (id: Int) -> Unit
){
    composable<CharactersScreen> {
        CharactersListUi(
            searchTopAppBar = { topAppBar(it) },
            toDetails = {
                toDetails(it)
            },
            modifier = modifier
        )
    }
}