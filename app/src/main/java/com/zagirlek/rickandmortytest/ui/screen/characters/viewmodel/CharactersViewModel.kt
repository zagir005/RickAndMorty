package com.zagirlek.rickandmortytest.ui.screen.characters.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.zagirlek.rickandmortytest.RickAndMortyApp
import com.zagirlek.rickandmortytest.domain.repository.CharacterRepository
import com.zagirlek.rickandmortytest.ui.screen.characters.model.CharactersScreenUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class CharactersViewModel(
    private val characterRepository: CharacterRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<CharactersScreenUiState> = MutableStateFlow(value = CharactersScreenUiState(loading = true))
    val uiState: StateFlow<CharactersScreenUiState> = _uiState.asStateFlow()

    init {
        loadCharacters()
        Log.d("TAG", _uiState.value.charactersList.size.toString())
    }

    private fun loadCharacters(){
        viewModelScope.launch(context = Dispatchers.IO) {
            characterRepository
                .getFilterCharacters()
                .onSuccess { charactersPage ->
                    _uiState.update {
                        it.copy(loading = false, error = null, charactersList = charactersPage.characterList)
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(loading = false, error = error.cause, charactersList = listOf())
                    }
                }
        }
    }

    companion object{
        fun getCharacterViewModel() = object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val app = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RickAndMortyApp

                return CharactersViewModel(
                    characterRepository = app.characterRepository
                ) as T
            }
        }
    }
}