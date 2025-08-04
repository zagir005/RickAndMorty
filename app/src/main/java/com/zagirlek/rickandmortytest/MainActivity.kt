package com.zagirlek.rickandmortytest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.zagirlek.rickandmortytest.data.network.dto.CharacterInfoDTO
import com.zagirlek.rickandmortytest.data.network.dto.CharactersListDTO
import com.zagirlek.rickandmortytest.data.network.retrofit.RickAndMortyRetrofit
import com.zagirlek.rickandmortytest.ui.theme.RickAndMortyTestTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyTestTheme {
                val charactersList = remember {
                    mutableStateListOf<CharacterInfoDTO>()
                }


                LazyColumn {
                    items(
                        items = charactersList,
                        key = {
                            it.id
                        }
                    ){
                        Text(text = it.name)
                    }
                }

                LaunchedEffect(key1 = null) {
                    lifecycleScope.launch {
                        RickAndMortyRetrofit
                            .provideCharacterService()
                            .getCharactersList()
                            .let {
                                charactersList.addAll(elements = it.characterList)
                            }
                    }

                }

            }
        }
    }
}
