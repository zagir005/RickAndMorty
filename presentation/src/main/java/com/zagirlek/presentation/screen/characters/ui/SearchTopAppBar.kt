package com.zagirlek.presentation.screen.characters.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
    query: String?,
    onSearch: (String?) -> Unit = {},
    onFilter: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    modifier: Modifier = Modifier
) {
    var isOnSearch by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            if (isOnSearch)
                OutlinedTextField(
                    value = query.orEmpty(),
                    onValueChange = {
                        onSearch(it)
                    },
                    singleLine = true,
                    label = {
                        Text("Имя...")
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                onSearch(null)
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
                        onSearch(null)
                        isOnSearch = false
                    }
                ) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack,"")
                }

        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showSystemUi = true
)
@Composable
fun SearchTopAppBarPreview(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { SearchTopAppBar("") }
    ) {

    }
}