package com.zagirlek.rickandmortytest.ui.screen.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.zagirlek.rickandmortytest.domain.model.Character
import com.zagirlek.rickandmortytest.ui.screen.characters.elements.CharacterCard
import com.zagirlek.rickandmortytest.ui.screen.characters.elements.FilterCharacterBottomSheet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersListScreen(
    modifier: Modifier = Modifier,
    toDetails: (id: Int) -> Unit,
    viewModel: CharactersViewModel = viewModel(factory = CharactersViewModel.getCharacterViewModel())
) {
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    val paginatedCharactersList = viewModel.paginatedCharacters.collectAsLazyPagingItems()
    val isLoading = paginatedCharactersList.loadState.refresh is LoadState.Loading

    var showFiltersBottomSheet by remember { mutableStateOf(false) }
    val filterBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showFiltersBottomSheet = true
                }
            ) {
                Icon(imageVector = Icons.Default.Build, contentDescription = null)
            }
        }
    ) { paddingValues ->

        if (showFiltersBottomSheet){
            FilterCharacterBottomSheet(
                bottomSheetState = filterBottomSheetState,
                currFilters = uiState.characterFilters,
                onDismiss = {

                    scope.launch {
                        filterBottomSheetState.hide()
                    }.invokeOnCompletion {
                        if (!filterBottomSheetState.isVisible)
                            showFiltersBottomSheet = false
                    }
                },
                onFilter = {
                    viewModel.updateCharactersByFilter(characterFilters = it)
                    scope.launch {
                        filterBottomSheetState.hide()
                    }.invokeOnCompletion {
                        if (!filterBottomSheetState.isVisible)
                            showFiltersBottomSheet = false
                    }
                }
            )
        }

        RefreshingPaginatedCharactersList(
            characterList = paginatedCharactersList,
            onCharacterClick = {

            },
            onRefresh = {
                paginatedCharactersList.refresh()

            },
            isRefreshing = isLoading,
            modifier = Modifier
                .padding(paddingValues)
        )
    }


    when{
        uiState.isLoading() -> CharactersLoading()
        uiState.isError() -> CharactersError(

        )
    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RefreshingPaginatedCharactersList(
    characterList: LazyPagingItems<Character>,
    onCharacterClick: (id: Int) -> Unit,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    modifier: Modifier = Modifier
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                count = characterList.itemCount,
                key = characterList.itemKey { it.id }
            ){ index ->
                CharacterCard(
                    character = characterList[index]!!
                ) {
                    onCharacterClick(characterList[index]?.id ?: 1)
                }
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