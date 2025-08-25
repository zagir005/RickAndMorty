package com.zagirlek.rickandmortytest

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.bundle.Bundle
import com.arkivanov.decompose.defaultComponentContext
import com.zagirlek.presentation.screen.root.RootComponent
import com.zagirlek.presentation.screen.root.RootComponentImpl
import com.zagirlek.presentation.screen.root.RootContent
import com.zagirlek.presentation.theme.RickAndMortyTestTheme
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        val rootComponent: RootComponent = get<RootComponentImpl.Factory>().invoke(
            componentContext = defaultComponentContext()
        )
        setContent {
            RickAndMortyTestTheme{
                RootContent(
                    rootComponent = rootComponent
                )
            }
        }
    }
}