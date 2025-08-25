package com.zagirlek.presentation.screen.characters.cmp

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.zagirlek.core.cmp.BaseComponent
import com.zagirlek.core.model.CharacterFilters
import com.zagirlek.core.model.Character
import com.zagirlek.domain.usecase.GetPagingCharactersUseCase
import com.zagirlek.presentation.screen.characters.cmp.reducer.CharactersListMutation
import com.zagirlek.presentation.screen.characters.cmp.reducer.CharactersListReducer
import com.zagirlek.presentation.screen.characters.cmp.state.CharactersListAction
import com.zagirlek.presentation.screen.characters.cmp.state.CharactersListState
import kotlinx.coroutines.flow.Flow

class CharactersListComponent(
    componentContext: ComponentContext,
    private val getPagingCharactersUseCase: GetPagingCharactersUseCase,
    private val onItemSelected: (id: Int) -> Unit
): BaseComponent<CharactersListState, CharactersListMutation, CharactersListAction, CharactersListReducer>(
    reducer = CharactersListReducer(),
    componentContext = componentContext
) {

    private val _state = MutableValue(CharactersListState())

    val state: Value<CharactersListState> = _state

    init {
        action(CharactersListAction.LoadFilterCharactersList())
    }

    private fun getPagedCharacters(characterFilters: CharacterFilters = CharacterFilters()): Flow<PagingData<Character>> {
        return getPagingCharactersUseCase(characterFilters).cachedIn(componentScope)
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


    class Factory(
        private val getPagingCharactersUseCase: GetPagingCharactersUseCase
    ){
        operator fun invoke(
            componentContext: ComponentContext,
            onItemSelected: (Int) -> Unit
        ): CharactersListComponent{
            return CharactersListComponent(
                componentContext = componentContext,
                getPagingCharactersUseCase = getPagingCharactersUseCase,
                onItemSelected = onItemSelected
            )
        }
    }
}
