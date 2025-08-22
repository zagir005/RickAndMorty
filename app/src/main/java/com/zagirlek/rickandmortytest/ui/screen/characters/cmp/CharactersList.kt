package com.zagirlek.rickandmortytest.ui.screen.characters.cmp

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.zagirlek.rickandmortytest.core.cmp.BaseComponent
import com.zagirlek.rickandmortytest.domain.repository.CharactersPagingRepository
import com.zagirlek.rickandmortytest.ui.screen.characters.cmp.reducer.CharactersListMutation
import com.zagirlek.rickandmortytest.ui.screen.characters.cmp.reducer.CharactersListReducer
import com.zagirlek.rickandmortytest.ui.screen.characters.cmp.state.CharactersListAction
import com.zagirlek.rickandmortytest.ui.screen.characters.cmp.state.CharactersListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersList(
    componentContext: ComponentContext,
    private val charactersPagingRepository: CharactersPagingRepository,
    private val onItemSelected: (id: Int) -> Unit
): BaseComponent<CharactersListState, CharactersListMutation, CharactersListAction, CharactersListReducer>(
    reducer = CharactersListReducer(),
    componentContext = componentContext
) {
    private val _state: MutableValue<CharactersListState> = MutableValue(CharactersListState())
    val state: Value<CharactersListState> = _state

    init {
        action(CharactersListAction.LoadFilterCharactersList())
    }

    override fun action(action: CharactersListAction) {
        when (action) {
            is CharactersListAction.LoadFilterCharactersList -> {
                componentScope.launch(Dispatchers.IO) {
                    val characters =
                        charactersPagingRepository.getFilterPaginatedCharactersList(
                            characterFilters = action.filter
                        )

                    CharactersListMutation.LoadCharactersList(
                        characters = characters,
                        filter = action.filter
                    ).reduce(_state)
                }
            }

            is CharactersListAction.ShowFilterBottomSheet -> {
                CharactersListMutation.ShowFilterBottomSheet.reduce(_state)
            }

            is CharactersListAction.HideFilterBottomSheet -> {
                CharactersListMutation.HideFilterBottomSheet.reduce(_state)
            }

            is CharactersListAction.Search -> {
                componentScope.launch(context = Dispatchers.IO) {
                    val characters =
                        charactersPagingRepository.getFilterPaginatedCharactersList(
                            characterFilters = _state.value.characterFilters.copy(name = action.name)
                        )

                    CharactersListMutation.CharactersListBySearch(
                        characters = characters,
                        query = action.name
                    ).reduce(_state)
                }
            }

            is CharactersListAction.ItemClick -> {
                onItemSelected(action.characterId)
            }

            CharactersListAction.ResetFilter -> {
                componentScope.launch {
                    CharactersListMutation.ResetFilterCharactersList(
                        characters = charactersPagingRepository.getFilterPaginatedCharactersList()
                    ).reduce(_state)
                }
            }

            is CharactersListAction.ResetSearch -> {
                componentScope.launch {
                    CharactersListMutation.CharactersListByEmptySearch(
                        characters = charactersPagingRepository.getFilterPaginatedCharactersList(
                            _state.value.characterFilters
                        )
                    ).reduce(_state)
                }
            }
        }
    }
}
