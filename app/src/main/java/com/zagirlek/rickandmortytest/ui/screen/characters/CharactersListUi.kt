package com.zagirlek.rickandmortytest.ui.screen.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.zagirlek.rickandmortytest.domain.model.Character
import com.zagirlek.rickandmortytest.ui.screen.characters.cmp.CharactersList
import com.zagirlek.rickandmortytest.ui.screen.characters.cmp.state.CharactersListAction
import com.zagirlek.rickandmortytest.ui.screen.characters.ui.CharacterCard
import com.zagirlek.rickandmortytest.ui.screen.characters.ui.FilterCharacterBottomSheet
import com.zagirlek.rickandmortytest.ui.screen.characters.ui.SearchTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersListUi(
    searchTopAppBar: ( @Composable () -> Unit ) -> Unit = {},
    component: CharactersList,
) {
    val uiState by component.state.subscribeAsState()

    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val characterGridItems = uiState.charactersPagingItems.collectAsLazyPagingItems()
    val charactersGridState = rememberLazyGridState()

    val topAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    LaunchedEffect(bottomSheetState) {
        if (uiState.isFilterSheetShown)
            bottomSheetState.show()
        else
            bottomSheetState.hide()
    }

    searchTopAppBar {
        SearchTopAppBar(
            query = uiState.search,
            onSearch = {
                component.action(CharactersListAction.Search(name = it.orEmpty()))
            },
            onFilter = {
                component.action(CharactersListAction.ShowFilterBottomSheet)
            },
            scrollBehavior = topAppBarScrollBehavior,
            modifier = Modifier
                .shadow(6.dp)
        )
    }

    if (uiState.isFilterSheetShown){
        FilterCharacterBottomSheet(
            currFilters = uiState.characterFilters,
            onDismiss = {
                component.action(CharactersListAction.HideFilterBottomSheet)
            },
            onFilter = {
                component.action(CharactersListAction.LoadFilterCharactersList(filter = it))
            },
            bottomSheetState = bottomSheetState
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        PullToRefresh(
            isRefreshing = characterGridItems.loadState.refresh is LoadState.Loading,
            onRefresh = {
                characterGridItems.refresh()
            },
            modifier = Modifier
                .fillMaxSize()
        ) {
            PaginatedCharactersList(
                characterList = characterGridItems,
                onCharacterClick = { id ->
                    component.action(CharactersListAction.ItemClick(id))
                },
                modifier = Modifier
                    .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
                state = charactersGridState
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PaginatedCharactersList(
    characterList: LazyPagingItems<Character>,
    onCharacterClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
    state: LazyGridState = rememberLazyGridState()
) {

    val refreshError = characterList.loadState.refresh as? LoadState.Error
    val appendError = characterList.loadState.append as? LoadState.Error

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        state = state,
        modifier = modifier
    ) {
        items(
            count = characterList.itemCount,
            key = characterList.itemKey { it.id }
        ) { index ->
            CharacterCard(
                character = characterList[index]!!
            ) {
                onCharacterClick(characterList[index]?.id ?: 1)
            }
        }

        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            if ((refreshError != null || appendError != null) && characterList.itemCount != 0){
                ErrorRetryAlert {
                    characterList.refresh()
                }
            }
        }
    }

    if (refreshError != null && characterList.itemCount == 0){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Проверьте интернет")
            Text(text = "Потяните вниз, что бы попробовать снова")
        }
    }
}



@Composable
fun ErrorRetryAlert(
    message: String = "Отсутствует интернет-соединие",
    modifier: Modifier = Modifier,
    onRetry: () -> Unit = {  }
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message)
        Button(
            onClick = {
                onRetry()
            }
        ) {
            Text(text = "Попытаться снова")
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PullToRefresh(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier
            .fillMaxSize()
    ) {
        content()
    }
}
