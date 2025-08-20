package com.zagirlek.rickandmortytest

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.zagirlek.rickandmortytest.ui.screen.characters.navigation.CharactersScreen
import com.zagirlek.rickandmortytest.ui.screen.characters.navigation.charactersList
import com.zagirlek.rickandmortytest.ui.screen.details.navigation.CharacterDetailsScreen
import com.zagirlek.rickandmortytest.ui.screen.details.navigation.characterDetails

@Composable
fun AppUi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var currTopAppBar by remember { mutableStateOf<@Composable () -> Unit>( {} ) }

    Scaffold(
        topBar = {
            currTopAppBar()
        },
        bottomBar = {

        },
        modifier = modifier
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = CharactersScreen,
            modifier = Modifier.padding(paddingValues)
        ){
            charactersList(
                topAppBar = {
                    currTopAppBar = it
                },
                toDetails = {
                    navController.navigate(CharacterDetailsScreen(id = it))
                }
            )
            characterDetails(
                backToMain = {
                    navController.popBackStack()
                },
                topAppBar = {
                    currTopAppBar = it
                }
            )

        }
    }
}