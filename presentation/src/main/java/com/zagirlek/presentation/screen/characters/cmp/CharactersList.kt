package com.zagirlek.presentation.screen.characters.cmp

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.zagirlek.rickandmortytest.core.cmp.BaseComponent
import com.zagirlek.rickandmortytest.domain.model.CharacterFilters
import com.zagirlek.rickandmortytest.domain.repository.CharactersPagingRepository
import com.zagirlek.rickandmortytest.ui.screen.characters.cmp.reducer.CharactersListMutation
import com.zagirlek.rickandmortytest.ui.screen.characters.cmp.reducer.CharactersListReducer
import com.zagirlek.rickandmortytest.ui.screen.characters.cmp.state.CharactersListAction
import com.zagirlek.rickandmortytest.ui.screen.characters.cmp.state.CharactersListState
import kotlinx.coroutines.flow.Flow

class CharactersList(
    componentContext: ComponentContext,
    private val charactersPagerRepository: CharactersPagingRepository,
    private val onItemSelected: (id: Int) -> Unit
): BaseComponent<com.zagirlek.presentation.screen.characters.cmp.state.CharactersListState, com.zagirlek.presentation.screen.characters.cmp.reducer.CharactersListMutation, com.zagirlek.presentation.screen.characters.cmp.state.CharactersListAction, com.zagirlek.presentation.screen.characters.cmp.reducer.CharactersListReducer>(
    reducer = _root_ide_package_.com.zagirlek.presentation.screen.characters.cmp.reducer.CharactersListReducer(),
    componentContext = componentContext
) {
    private val _state = MutableValue(_root_ide_package_.com.zagirlek.presentation.screen.characters.cmp.state.CharactersListState())

    val state: Value<com.zagirlek.presentation.screen.characters.cmp.state.CharactersListState> = _state

    private class Handler(initialState: com.zagirlek.presentation.screen.characters.cmp.state.CharactersListState): InstanceKeeper.Instance{
        val state = MutableValue(initialState)
    }

    init {
        action(_root_ide_package_.com.zagirlek.presentation.screen.characters.cmp.state.CharactersListAction.LoadFilterCharactersList())
    }

    private fun getPagedCharacters(characterFilters: CharacterFilters = CharacterFilters()): Flow<PagingData<Character>> {
        return charactersPagerRepository.getFilterPaginatedCharactersList(characterFilters).cachedIn(componentScope)
    }

    override fun action(action: com.zagirlek.presentation.screen.characters.cmp.state.CharactersListAction) {
        when (action) {
            is com.zagirlek.presentation.screen.characters.cmp.state.CharactersListAction.LoadFilterCharactersList -> {
                val characters = getPagedCharacters(characterFilters = action.filter)

                _root_ide_package_.com.zagirlek.presentation.screen.characters.cmp.reducer.CharactersListMutation.LoadCharactersList(
                    characters = characters,
                    filter = action.filter
                ).reduce(_state)
            }

            is com.zagirlek.presentation.screen.characters.cmp.state.CharactersListAction.ShowFilterBottomSheet -> {
                _root_ide_package_.com.zagirlek.presentation.screen.characters.cmp.reducer.CharactersListMutation.ShowFilterBottomSheet.reduce(_state)
            }

            is com.zagirlek.presentation.screen.characters.cmp.state.CharactersListAction.HideFilterBottomSheet -> {
                _root_ide_package_.com.zagirlek.presentation.screen.characters.cmp.reducer.CharactersListMutation.HideFilterBottomSheet.reduce(_state)
            }

            is com.zagirlek.presentation.screen.characters.cmp.state.CharactersListAction.Search -> {
                val characters = getPagedCharacters(
                    characterFilters = _state.value.characterFilters
                        .copy(name = action.name)
                )

                _root_ide_package_.com.zagirlek.presentation.screen.characters.cmp.reducer.CharactersListMutation.CharactersListBySearch(
                    characters = characters,
                    query = action.name
                ).reduce(_state)
            }

            is com.zagirlek.presentation.screen.characters.cmp.state.CharactersListAction.ItemClick -> {
                onItemSelected(action.characterId)
            }

            _root_ide_package_.com.zagirlek.presentation.screen.characters.cmp.state.CharactersListAction.ResetFilter -> {
                _root_ide_package_.com.zagirlek.presentation.screen.characters.cmp.reducer.CharactersListMutation.ResetFilterCharactersList(
                    characters = getPagedCharacters()
                ).reduce(_state)
            }

            is com.zagirlek.presentation.screen.characters.cmp.state.CharactersListAction.ResetSearch -> {
                _root_ide_package_.com.zagirlek.presentation.screen.characters.cmp.reducer.CharactersListMutation.CharactersListByEmptySearch(
                    characters = getPagedCharacters(
                        characterFilters = _state.value.characterFilters
                    )
                ).reduce(_state)
            }
        }
    }

    companion object{
        private const val INSTANCE_KEY = "instance_key"
    }
}
