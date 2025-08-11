package com.zagirlek.rickandmortytest.ui.screen.characters.elements

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zagirlek.rickandmortytest.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(
    onSearch: (String) -> Unit = {},
    onFilter: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    modifier: Modifier = Modifier
) {
    var isOnSearch by remember { mutableStateOf(false) }
    var searchValue by remember { mutableStateOf("") }

    TopAppBar(
        title = {
            if (isOnSearch)
                OutlinedTextField(
                    value = searchValue,
                    onValueChange = {
                        searchValue = it
                        onSearch(it)
                    },
                    singleLine = true,
                    label = {
                        Text("Введите имя...")
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    trailingIcon = {
                        IconButton(
                            onClick ={
                                searchValue = ""
                                onSearch(searchValue)
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Clear, contentDescription = "")
                        }
                    }
                )
            else
                Text(text = "RickAndMorty App")
        },
        actions = {
            if (!isOnSearch){
                FilledIconButton(
                    onClick = {
                        isOnSearch = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search"
                    )
                }
            }
            FilledIconButton(
                onClick = {
                    onFilter()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.filter_alt_24),
                    contentDescription = "filter"
                )
            }
        },
        navigationIcon = {
            if(isOnSearch)
                IconButton(
                    onClick ={
                        searchValue = ""
                        isOnSearch = false
                    }
                ) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack,"")
                }

        },
        scrollBehavior = scrollBehavior,
        modifier = modifier,
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true
)
@Composable
fun SearchTopAppBarPreview(modifier: Modifier = Modifier) {
    SearchTopAppBar()
}