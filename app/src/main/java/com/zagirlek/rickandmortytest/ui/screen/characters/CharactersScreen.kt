package com.zagirlek.rickandmortytest.ui.screen.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.zagirlek.rickandmortytest.domain.model.Character
import com.zagirlek.rickandmortytest.ui.screen.characters.elements.CharacterCard
import com.zagirlek.rickandmortytest.ui.screen.characters.viewmodel.CharactersViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    toDetails: (id: Int) -> Unit,
    viewModel: CharactersViewModel = viewModel(factory = CharactersViewModel.getCharacterViewModel())
) {
    val uiState by viewModel.uiState.collectAsState()

    when{
        uiState.isLoading() -> CharactersLoading()
        uiState.isError() -> CharactersError(

        )
        uiState.isListLoaded() -> PaginatedCharactersList(
            characterList = viewModel.paginatedDataFlow,
            onCharacterClick = {
                toDetails(it)
            }
        )
    }

}

@Composable
private fun PaginatedCharactersList(
    characterList: Flow<PagingData<Character>>,
    onCharacterClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagingCharacters = characterList.collectAsLazyPagingItems()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(
            count = pagingCharacters.itemCount,
            key = pagingCharacters.itemKey { it.id }
        ){ index ->
            CharacterCard(
                character = pagingCharacters[index]!!
            ) {
                onCharacterClick(pagingCharacters[index]?.id ?: 1)
            }
        }
    }
}

@Composable
private fun CharactersLoading(modifier: Modifier = Modifier) {

}

@Composable
private fun CharactersError(){

}