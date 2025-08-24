package com.zagirlek.rickandmortytest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val root = Root(
            defaultComponentContext(),
            Activity.getApplication as RickAndMortyApp
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