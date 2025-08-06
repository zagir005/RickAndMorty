package com.zagirlek.rickandmortytest.ui.screen.characters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.paging.cachedIn
import com.zagirlek.rickandmortytest.RickAndMortyApp
import com.zagirlek.rickandmortytest.data.network.utils.CharactersFilters
import com.zagirlek.rickandmortytest.domain.model.Character
import com.zagirlek.rickandmortytest.domain.repository.CharacterRepository
import com.zagirlek.rickandmortytest.domain.repository.CharactersPagingRepository
import com.zagirlek.rickandmortytest.ui.screen.characters.model.CharactersScreenUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update

class CharactersViewModel(
    private val characterRepository: CharacterRepository,
    private val charactersPagingRepository: CharactersPagingRepository
): ViewModel() {

    private val currFilters = MutableStateFlow(CharactersFilters())
    @OptIn(ExperimentalCoroutinesApi::class)
    val paginatedCharacters = currFilters
        .flatMapLatest { filters ->
            charactersPagingRepository.getFilterPaginatedCharactersList(filters)
        }
        .cachedIn(viewModelScope)

    private val _uiState: MutableStateFlow<CharactersScreenUiState> =
        MutableStateFlow(
            value = CharactersScreenUiState(loading = true)
        )
    val uiState: StateFlow<CharactersScreenUiState> = _uiState.asStateFlow()

    fun updateCharactersByFilter(
        charactersFilters: CharactersFilters
    ){
        currFilters.update {
            charactersFilters
        }
    }

    companion object{
        fun getCharacterViewModel() = object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val app = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RickAndMortyApp

                return CharactersViewModel(
                    characterRepository = app.characterRepository,
                    charactersPagingRepository = app.charactersPagingRepository
                ) as T
            }
        }
    }
}