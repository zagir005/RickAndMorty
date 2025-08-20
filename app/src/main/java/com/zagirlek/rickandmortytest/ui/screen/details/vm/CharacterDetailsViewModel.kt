package com.zagirlek.rickandmortytest.ui.screen.details.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.toRoute
import com.zagirlek.rickandmortytest.RickAndMortyApp
import com.zagirlek.rickandmortytest.core.vm.BaseViewModel
import com.zagirlek.rickandmortytest.domain.usecase.GetSingleCharacterUseCase
import com.zagirlek.rickandmortytest.ui.screen.details.elements.state.CharacterDetailsState
import com.zagirlek.rickandmortytest.ui.screen.details.navigation.CharacterDetailsScreen
import com.zagirlek.rickandmortytest.ui.screen.details.vm.reducer.CharacterDetailsMutation
import com.zagirlek.rickandmortytest.ui.screen.details.vm.reducer.CharacterDetailsReducer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val getSingleCharacterUseCase: GetSingleCharacterUseCase,
    private val savedStateHandle: SavedStateHandle
): BaseViewModel<CharacterDetailsState, CharacterDetailsMutation, CharacterDetailsReducer>(
    reducer = CharacterDetailsReducer()
) {

    val characterId = savedStateHandle.toRoute<CharacterDetailsScreen>().id

    private val _uiState: MutableStateFlow<CharacterDetailsState> = MutableStateFlow(
        value = CharacterDetailsState(loading = true)
    )
    val uiState = _uiState.asStateFlow()

    init {
        action(action = CharacterDetailsAction.FetchData(characterId))
    }

    fun action(action: CharacterDetailsAction){
        when(action){
            is CharacterDetailsAction.FetchData -> {
                viewModelScope.launch {
                    CharacterDetailsMutation.OnLoading.reduce(_uiState)
                    val character = getSingleCharacterUseCase(characterId)

                    if (character != null)
                        CharacterDetailsMutation.CharacterLoaded(character).reduce(_uiState)
                    else
                        CharacterDetailsMutation.CharacterNotFoundError.reduce(_uiState)
                }
            }
        }
    }

    companion object{
        fun factory() = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RickAndMortyApp
                val savedStateHandle = createSavedStateHandle()

                val getSingleCharacterUseCase = GetSingleCharacterUseCase(
                    localCharacterRepository = app.localCharacterRepository
                )

                CharacterDetailsViewModel(
                    getSingleCharacterUseCase = getSingleCharacterUseCase,
                    savedStateHandle = savedStateHandle
                )
            }
        }
    }
}
