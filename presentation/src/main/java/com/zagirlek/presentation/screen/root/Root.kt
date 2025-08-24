package com.zagirlek.presentation.screen.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.zagirlek.rickandmortytest.RickAndMortyApp
import com.zagirlek.rickandmortytest.domain.usecase.GetSingleCharacterUseCase
import com.zagirlek.rickandmortytest.ui.screen.characters.cmp.CharactersList
import com.zagirlek.rickandmortytest.ui.screen.details.cmp.CharacterDetails
import kotlinx.serialization.Serializable

class Root(
    componentContext: ComponentContext,
    private val app: RickAndMortyApp
): RootComponent, ComponentContext by componentContext {

    private val nav = StackNavigation<Config>()

    override val state: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = nav,
            initialConfiguration = Config.CharactersList,
            serializer = Config.serializer(),
            handleBackButton = true,
            childFactory = ::componentChild
        )

    @OptIn(DelicateDecomposeApi::class)
    private fun componentChild(config: Config, component: ComponentContext): RootComponent.Child {
        return when(config){
            Config.CharactersList -> {
                RootComponent.Child.CharactersListChild(
                    _root_ide_package_.com.zagirlek.presentation.screen.characters.cmp.CharactersList(
                        componentContext = component,
                        charactersPagerRepository = app.charactersPagingRepository,
                        onItemSelected = {
                            nav.push(configuration = Config.CharacterDetails(characterId = it))
                        }
                    )
                )
            }
            is Config.CharacterDetails -> {
                RootComponent.Child.CharacterDetailsChild(
                    _root_ide_package_.com.zagirlek.presentation.screen.details.cmp.CharacterDetails(
                        componentContext = component,
                        getSingleCharacterUseCase = GetSingleCharacterUseCase(
                            localCharacterRepository = app.localCharacterRepository
                        ),
                        characterId = config.characterId
                    ) {
                        nav.pop()
                    }
                )
            }
        }
    }


    @Serializable
    sealed class Config{
        @Serializable
        data object CharactersList: Config()

        @Serializable
        data class CharacterDetails(val characterId: Int): Config()
    }
}