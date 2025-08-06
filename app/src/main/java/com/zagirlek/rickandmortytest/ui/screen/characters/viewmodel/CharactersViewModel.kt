package com.zagirlek.rickandmortytest.ui.screen.characters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.paging.cachedIn
import com.zagirlek.rickandmortytest.RickAndMortyApp
import com.zagirlek.rickandmortytest.domain.model.CharactersFilters
import com.zagirlek.rickandmortytest.domain.repository.CharactersPagingRepository
import com.zagirlek.rickandmortytest.ui.screen.characters.model.CharactersScreenUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update

class CharactersViewModel(
    private val charactersPagingRepository: CharactersPagingRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<CharactersScreenUiState> =
        MutableStateFlow(
            value = CharactersScreenUiState(loading = true)
        )

    val uiState: StateFlow<CharactersScreenUiState> = _uiState.asStateFlow()

    private val currFilters = MutableStateFlow(_uiState.value.charactersFilters)

    @OptIn(ExperimentalCoroutinesApi::class)
    val paginatedCharacters = currFilters
        .flatMapLatest { filters ->
            charactersPagingRepository.getFilterPaginatedCharactersList(filters)
        }
        .cachedIn(viewModelScope)


    fun updateCharactersByFilter(
        charactersFilters: CharactersFilters
    ){
        _uiState.update {
            it.copy(
                charactersFilters = charactersFilters
            )
        }
        currFilters.update {
            charactersFilters
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