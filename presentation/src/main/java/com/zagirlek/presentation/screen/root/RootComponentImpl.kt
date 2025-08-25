package com.zagirlek.presentation.screen.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.zagirlek.presentation.screen.characters.cmp.CharactersListComponent
import com.zagirlek.presentation.screen.details.cmp.CharacterDetailsComponent
import kotlinx.serialization.Serializable

class RootComponentImpl(
    componentContext: ComponentContext,
    private val characterDetailsComponentFactory: CharacterDetailsComponent.Factory,
    private val charactersListComponentFactory: CharactersListComponent.Factory
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
                    charactersListComponent = charactersListComponentFactory(
                        componentContext = component
                    ){ id ->
                        nav.push(
                            configuration = Config.CharacterDetails(id)
                        )
                    }
                )
            }
            is Config.CharacterDetails -> {
                RootComponent.Child.CharacterDetailsChild(
                    characterDetailsComponent = characterDetailsComponentFactory(
                        characterId = config.characterId,
                        componentContext = component
                    ){
                        nav.pop()
                    }
                )
            }
        }
    }

    class Factory(
        private val characterDetailsComponentFactory: CharacterDetailsComponent.Factory,
        private val charactersListComponentFactory: CharactersListComponent.Factory
    ){
        operator fun invoke(componentContext: ComponentContext): RootComponent = RootComponentImpl(
            componentContext = componentContext,
            characterDetailsComponentFactory = characterDetailsComponentFactory,
            charactersListComponentFactory = charactersListComponentFactory
        )
    }

    @Serializable
    sealed class Config{
        @Serializable
        data object CharactersList: Config()

        @Serializable
        data class CharacterDetails(val characterId: Int): Config()
    }
}