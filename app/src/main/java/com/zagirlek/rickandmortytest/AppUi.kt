package com.zagirlek.rickandmortytest

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zagirlek.rickandmortytest.ui.screen.characters.CharactersListScreen
import com.zagirlek.rickandmortytest.ui.screen.characters.navigation.CharactersScreen
import com.zagirlek.rickandmortytest.ui.screen.characters.navigation.charactersScreen

@Composable
fun AppUi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var topAppBar by remember { mutableStateOf<@Composable () -> Unit>( {} ) }

    Scaffold(
        topBar = {
            topAppBar()
        },
        bottomBar = {

        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ){
            NavHost(
                navController = navController,
                startDestination = CharactersScreen,
                modifier = modifier
            ){
                composable<CharactersScreen> {
                    CharactersListScreen(
                        searchTopAppBar = { topAppBar = it },
                    )
                }
            }
        }
    }
}