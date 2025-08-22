package com.zagirlek.rickandmortytest.ui.screen.characters.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.zagirlek.rickandmortytest.RickAndMortyApp
import com.zagirlek.rickandmortytest.core.cmp.BaseViewModel
import com.zagirlek.rickandmortytest.domain.repository.CharactersPagingRepository
import com.zagirlek.rickandmortytest.ui.screen.characters.elements.state.CharactersState
import com.zagirlek.rickandmortytest.ui.screen.characters.vm.reducer.CharactersListMutation
import com.zagirlek.rickandmortytest.ui.screen.characters.vm.reducer.CharactersListReducer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val charactersPagingRepository: CharactersPagingRepository,

    ): BaseViewModel<CharactersState, CharactersListMutation, CharactersListReducer>
    (reducer = CharactersListReducer()) {
    private val _uiState: MutableStateFlow<CharactersState> =
        MutableStateFlow(
            value = CharactersState()
        )
    val uiState: StateFlow<CharactersState> = _uiState.asStateFlow()

    init {
        action(CharactersListAction.LoadFilterCharactersList())
    }

    fun action(charactersListAction: CharactersListAction){
        when(val action = charactersListAction){
            is CharactersListAction.LoadFilterCharactersList -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val characters =
                        charactersPagingRepository.getFilterPaginatedCharactersList(
                            characterFilters = action.filter
                        )

                    CharactersListMutation.LoadCharactersList(
                        characters = characters,
                        filter = action.filter
                    ).reduce(_uiState)
                }
            }
            is CharactersListAction.ShowFilterBottomSheet -> {
                CharactersListMutation.ShowFilterBottomSheet.reduce(_uiState)
            }
            is CharactersListAction.HideFilterBottomSheet -> {
                CharactersListMutation.HideFilterBottomSheet.reduce(_uiState)
            }
            is CharactersListAction.Search -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    val characters =
                        charactersPagingRepository.getFilterPaginatedCharactersList(
                            characterFilters = _uiState.value.characterFilters.copy(name = action.name)
                        )

                    CharactersListMutation.CharactersListBySearch(
                        characters = characters,
                        query = action.name
                    ).reduce(_uiState)
                }
            }
            is CharactersListAction.ItemClick -> {

            }
            CharactersListAction.ResetFilter -> {
                viewModelScope.launch {
                    CharactersListMutation.ResetFilterCharactersList(
                        characters = charactersPagingRepository.getFilterPaginatedCharactersList()
                    ).reduce(_uiState)
                }
            }
            is CharactersListAction.ResetSearch -> {
                viewModelScope.launch {
                    CharactersListMutation.CharactersListByEmptySearch(
                        characters = charactersPagingRepository.getFilterPaginatedCharactersList(
                            _uiState.value.characterFilters
                        )
                    ).reduce(_uiState)
                }
            }
        }
    }



    companion object{
        fun viewModelFactory() = object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val app = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RickAndMortyApp

                return CharactersViewModel(
                    charactersPagingRepository = app.charactersPagingRepository
                ) as T
            }
        }
    }
}