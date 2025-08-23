package com.zagirlek.rickandmortytest.ui.screen.characters.cmp

import android.util.Log
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.zagirlek.rickandmortytest.core.cmp.BaseComponent
import com.zagirlek.rickandmortytest.domain.model.Character
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
): BaseComponent<CharactersListState, CharactersListMutation, CharactersListAction, CharactersListReducer>(
    reducer = CharactersListReducer(),
    componentContext = componentContext
) {
    private val _state = MutableValue(CharactersListState())

    val state: Value<CharactersListState> = _state

    private class Handler(initialState: CharactersListState): InstanceKeeper.Instance{
        val state = MutableValue(initialState)
    }

    init {
        action(CharactersListAction.LoadFilterCharactersList())
    }

    private fun getPagedCharacters(characterFilters: CharacterFilters = CharacterFilters()): Flow<PagingData<Character>> {
        Log.d("COMPONENT PAGER", "COMPONENT PAGER CALL")
        return charactersPagerRepository.getFilterPaginatedCharactersList(characterFilters).cachedIn(componentScope)
    }

    override fun action(action: CharactersListAction) {
        when (action) {
            is CharactersListAction.LoadFilterCharactersList -> {
                val characters = getPagedCharacters(characterFilters = action.filter)

                CharactersListMutation.LoadCharactersList(
                    characters = characters,
                    filter = action.filter
                ).reduce(_state)
            }

            is CharactersListAction.ShowFilterBottomSheet -> {
                CharactersListMutation.ShowFilterBottomSheet.reduce(_state)
            }

            is CharactersListAction.HideFilterBottomSheet -> {
                CharactersListMutation.HideFilterBottomSheet.reduce(_state)
            }

            is CharactersListAction.Search -> {
                val characters = getPagedCharacters(
                    characterFilters = _state.value.characterFilters
                        .copy(name = action.name)
                )

                CharactersListMutation.CharactersListBySearch(
                    characters = characters,
                    query = action.name
                ).reduce(_state)
            }

            is CharactersListAction.ItemClick -> {
                onItemSelected(action.characterId)
            }

            CharactersListAction.ResetFilter -> {
                CharactersListMutation.ResetFilterCharactersList(
                    characters = getPagedCharacters()
                ).reduce(_state)
            }

            is CharactersListAction.ResetSearch -> {
                CharactersListMutation.CharactersListByEmptySearch(
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
