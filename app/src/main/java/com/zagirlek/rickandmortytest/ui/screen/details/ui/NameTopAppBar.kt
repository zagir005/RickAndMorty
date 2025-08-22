package com.zagirlek.rickandmortytest.ui.screen.details.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameTopAppBar(
    name: String,
    modifier: Modifier = Modifier,
    goBack: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = name)
        },
        navigationIcon = {
            IconButton(
                onClick = goBack
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "GoBack"
                )
            }
        }
    )
}