package com.zagirlek.rickandmortytest.ui.screen.characters

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zagirlek.rickandmortytest.domain.model.Character
import com.zagirlek.rickandmortytest.ui.screen.characters.elements.CharacterCard
import com.zagirlek.rickandmortytest.ui.screen.characters.viewmodel.CharactersViewModel

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    toDetails: (id: Int) -> Unit,
    viewModel: CharactersViewModel = viewModel(factory = CharactersViewModel.getCharacterViewModel())
) {
    val uiState by viewModel.uiState.collectAsState()

    when{
        uiState.isLoading() -> CharactersLoading()
        uiState.isError() -> CharactersError()
        uiState.isListLoaded() -> CharactersList(
            characterList = uiState.charactersList,
            onCharacterClick = {
                toDetails(it)
            }
        )
    }

}

@Composable
private fun CharactersList(
    characterList: List<Character>,
    onCharacterClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(
            items = characterList,
            key = { it.id }
        ){ character ->
            CharacterCard(
                character = character
            ) {
                onCharacterClick(character.id)
            }
        }
    }
}

@Composable
private fun CharactersLoading(modifier: Modifier = Modifier) {

}

@Composable
private fun CharactersError(modifier: Modifier = Modifier) {

}