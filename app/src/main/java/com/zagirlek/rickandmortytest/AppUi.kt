package com.zagirlek.rickandmortytest

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.zagirlek.rickandmortytest.ui.screen.characters.navigation.CharactersScreen
import com.zagirlek.rickandmortytest.ui.screen.characters.navigation.charactersScreen

@Composable
fun AppUi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {

        },
        bottomBar = {

        }
    ) { paddingValues ->
        RickAndMortyNavGraph(
            navController = navController,
            modifier = Modifier
                .padding(paddingValues)
        )
    }
}

@Composable
private fun RickAndMortyNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = CharactersScreen,
        modifier = modifier
    ){
        charactersScreen {

        }
    }
}