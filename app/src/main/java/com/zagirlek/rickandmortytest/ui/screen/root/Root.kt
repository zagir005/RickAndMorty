package com.zagirlek.rickandmortytest.ui.screen.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.router.stack.childStack
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
                    CharactersList(
                        component,
                        app.charactersPagingRepository,
                        onItemSelected = {
                            nav.push(Config.CharacterDetails(it))
                        }
                    )
                )
            }
            is Config.CharacterDetails -> {
                RootComponent.Child.CharacterDetailsChild(
                    CharacterDetails(
                        component,
                        GetSingleCharacterUseCase(app.localCharacterRepository),
                        config.characterId
                    )
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