package com.zagirlek.rickandmortytest.ui.screen.details.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.zagirlek.rickandmortytest.ui.screen.details.CharacterDetailsUi
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDetailsScreen(val id: Int)

fun NavGraphBuilder.characterDetails(
    backToMain: () -> Unit,
    topAppBar: (@Composable () -> Unit) -> Unit
){
    composable<CharacterDetailsScreen> { backStack ->
        CharacterDetailsUi(
            backToMain = backToMain,
            topAppBar = {
                topAppBar(it)
            }
        )
    }
}