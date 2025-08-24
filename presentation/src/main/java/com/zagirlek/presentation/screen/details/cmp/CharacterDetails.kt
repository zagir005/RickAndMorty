package com.zagirlek.presentation.screen.details.cmp

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.zagirlek.rickandmortytest.core.cmp.BaseComponent
import com.zagirlek.rickandmortytest.domain.usecase.GetSingleCharacterUseCase
import com.zagirlek.rickandmortytest.ui.screen.details.cmp.reducer.CharacterDetailsMutation
import com.zagirlek.rickandmortytest.ui.screen.details.cmp.reducer.CharacterDetailsMutation.*
import com.zagirlek.rickandmortytest.ui.screen.details.cmp.reducer.CharacterDetailsReducer
import com.zagirlek.presentation.screen.details.cmp.state.CharacterDetailsAction
import com.zagirlek.rickandmortytest.ui.screen.details.cmp.state.CharacterDetailsState
import kotlinx.coroutines.launch

class CharacterDetails(
    componentContext: ComponentContext,
    private val getSingleCharacterUseCase: GetSingleCharacterUseCase,
    private val characterId: Int,
    private val onBack: () -> Unit
): BaseComponent<com.zagirlek.presentation.screen.details.cmp.state.CharacterDetailsState, com.zagirlek.presentation.screen.details.cmp.reducer.CharacterDetailsMutation, CharacterDetailsAction, com.zagirlek.presentation.screen.details.cmp.reducer.CharacterDetailsReducer>(
        reducer = _root_ide_package_.com.zagirlek.presentation.screen.details.cmp.reducer.CharacterDetailsReducer(), componentContext = componentContext)
{
    private val _state: MutableValue<com.zagirlek.presentation.screen.details.cmp.state.CharacterDetailsState> =
        MutableValue(initialValue = _root_ide_package_.com.zagirlek.presentation.screen.details.cmp.state.CharacterDetailsState())

    val state: Value<com.zagirlek.presentation.screen.details.cmp.state.CharacterDetailsState> = _state

    init {
        action(CharacterDetailsAction.FetchData(characterId))
    }

    override fun action(action: CharacterDetailsAction) {
        when(action){
            is CharacterDetailsAction.FetchData -> {
                componentScope.launch {
                    _root_ide_package_.com.zagirlek.presentation.screen.details.cmp.reducer.CharacterDetailsMutation.OnLoading.reduce(_state)
                    val character = getSingleCharacterUseCase(characterId)

                    if (character == null)
                        _root_ide_package_.com.zagirlek.presentation.screen.details.cmp.reducer.CharacterDetailsMutation.CharacterNotFoundError.reduce(_state)
                    else
                        _root_ide_package_.com.zagirlek.presentation.screen.details.cmp.reducer.CharacterDetailsMutation.CharacterLoaded(
                            character
                        ).reduce(_state)
                }
            }
            CharacterDetailsAction.BackToList -> {
                onBack()
            }
        }
    }

}