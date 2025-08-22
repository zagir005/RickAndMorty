package com.zagirlek.rickandmortytest.ui.screen.details.cmp

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.zagirlek.rickandmortytest.core.cmp.BaseComponent
import com.zagirlek.rickandmortytest.domain.usecase.GetSingleCharacterUseCase
import com.zagirlek.rickandmortytest.ui.screen.details.cmp.reducer.CharacterDetailsMutation
import com.zagirlek.rickandmortytest.ui.screen.details.cmp.reducer.CharacterDetailsReducer
import com.zagirlek.rickandmortytest.ui.screen.details.cmp.state.CharacterDetailsAction
import com.zagirlek.rickandmortytest.ui.screen.details.cmp.state.CharacterDetailsState
import kotlinx.coroutines.launch

interface CharacterDetails{
    val state: Value<CharacterDetailsState>

}

class DefaultCharacterDetails(
    componentContext: ComponentContext,
    private val getSingleCharacterUseCase: GetSingleCharacterUseCase,
    private val characterId: Int
): CharacterDetails, BaseComponent<CharacterDetailsState, CharacterDetailsMutation, CharacterDetailsAction, CharacterDetailsReducer>(
        reducer = CharacterDetailsReducer(), componentContext = componentContext)
{
    private val _state: MutableValue<CharacterDetailsState> =
        MutableValue(initialValue = CharacterDetailsState())

    override val state: Value<CharacterDetailsState> = _state

    init {
        action(CharacterDetailsAction.FetchData(characterId))
    }

    override fun action(action: CharacterDetailsAction) {
        when(action){
            is CharacterDetailsAction.FetchData -> {
                componentScope.launch {
                    CharacterDetailsMutation.OnLoading.reduce(_state)
                    val character = getSingleCharacterUseCase(characterId)

                    if (character == null)
                        CharacterDetailsMutation.CharacterNotFoundError.reduce(_state)
                    else
                        CharacterDetailsMutation.CharacterLoaded(character).reduce(_state)
                }
            }
        }
    }
}