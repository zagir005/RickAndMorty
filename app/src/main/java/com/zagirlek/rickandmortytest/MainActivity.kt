package com.zagirlek.rickandmortytest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.zagirlek.rickandmortytest.ui.screen.root.Root
import com.zagirlek.rickandmortytest.ui.screen.root.RootContent
import com.zagirlek.rickandmortytest.ui.theme.RickAndMortyTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val root = Root(
            defaultComponentContext(),
            application as RickAndMortyApp
        )
        setContent {
            RickAndMortyTestTheme{
                RootContent(
                    root
                )
            }
        }
    }
}
