package com.zagirlek.rickandmortytest.ui.screen.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.zagirlek.rickandmortytest.RickAndMortyApp
import com.zagirlek.rickandmortytest.domain.repository.CharactersPagingRepository
import com.zagirlek.rickandmortytest.ui.screen.characters.model.CharactersAction
import com.zagirlek.rickandmortytest.ui.screen.characters.model.CharactersState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val charactersPagingRepository: CharactersPagingRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<CharactersState> =
        MutableStateFlow(
            value = CharactersState()
        )
    val uiState: StateFlow<CharactersState> = _uiState.asStateFlow()

    init {
        action(CharactersAction.LoadCharacters())
    }

    fun action(charactersAction: CharactersAction){
        when(val action = charactersAction){
            is CharactersAction.LoadCharacters -> {
                _uiState.update {
                    it.copy(
                        charactersPagingItems = charactersPagingRepository
                            .getFilterPaginatedCharactersList(characterFilters = action.filter)
                    )
                }
            }
            is CharactersAction.ShowFilterBottomSheet -> {
                _uiState.update {
                    it.copy(
                        isFilterSheetShown = true
                    )
                }
            }
            is CharactersAction.HideFilterBottomSheet -> {
                _uiState.update {
                    it.copy(
                        isFilterSheetShown = false
                    )
                }
            }
            is CharactersAction.SearchByName -> {
                _uiState.update {
                    it.copy(
                        charactersPagingItems = charactersPagingRepository
                            .getFilterPaginatedCharactersList(
                                characterFilters = _uiState.value.characterFilters.copy(
                                    name = action.name.let { name ->
                                        name.ifBlank { null }
                                    }
                                )
                            )
                    )
                }
            }
        }
    }

    companion object{
        fun getCharacterViewModel() = object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val app = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RickAndMortyApp

                return CharactersViewModel(
                    charactersPagingRepository = app.charactersPagingRepository
                ) as T
            }
        }
    }
}