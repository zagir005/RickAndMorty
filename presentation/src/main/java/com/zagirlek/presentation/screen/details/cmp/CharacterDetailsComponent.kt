package com.zagirlek.presentation.screen.details.cmp

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.zagirlek.core.cmp.BaseComponent
import com.zagirlek.domain.usecase.GetSingleCharacterUseCase
import com.zagirlek.presentation.screen.details.cmp.reducer.CharacterDetailsMutation
import com.zagirlek.presentation.screen.details.cmp.reducer.CharacterDetailsReducer
import com.zagirlek.presentation.screen.details.cmp.state.CharacterDetailsAction
import com.zagirlek.presentation.screen.details.cmp.state.CharacterDetailsState
import kotlinx.coroutines.launch

class CharacterDetailsComponent(
    componentContext: ComponentContext,
    private val getSingleCharacterUseCase: GetSingleCharacterUseCase,
    private val characterId: Int,
    private val onBack: () -> Unit
): BaseComponent<CharacterDetailsState, CharacterDetailsMutation, CharacterDetailsAction, CharacterDetailsReducer>(
        reducer = CharacterDetailsReducer(), componentContext = componentContext)
{
    private val _state: MutableValue<CharacterDetailsState> =
        MutableValue(initialValue = CharacterDetailsState())

    val state: Value<CharacterDetailsState> = _state

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
                        CharacterDetailsMutation.CharacterLoaded(
                            character
                        ).reduce(_state)
                }
            }
            CharacterDetailsAction.BackToList -> {
                onBack()
            }
        }
    }

    class Factory(
        private val getSingleCharacterUseCase: GetSingleCharacterUseCase
    ) {
        operator fun invoke(
            characterId: Int,
            componentContext: ComponentContext,
            onBack: () -> Unit
        ) = CharacterDetailsComponent(
            getSingleCharacterUseCase = getSingleCharacterUseCase,
            componentContext = componentContext,
            characterId = characterId,
            onBack = onBack
        )
    }

}