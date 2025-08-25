package com.zagirlek.presentation.di

import com.zagirlek.presentation.screen.characters.cmp.CharactersListComponent
import com.zagirlek.presentation.screen.details.cmp.CharacterDetailsComponent
import com.zagirlek.presentation.screen.root.RootComponentImpl
import org.koin.dsl.module

val presentationModule = module {
    single<CharactersListComponent.Factory> {
        CharactersListComponent.Factory(
            getPagingCharactersUseCase = get()
        )
    }

    single<CharacterDetailsComponent.Factory> {
        CharacterDetailsComponent.Factory(
            getSingleCharacterUseCase = get()
        )
    }

    single<RootComponentImpl.Factory> {
        RootComponentImpl.Factory(
            characterDetailsComponentFactory = get(),
            charactersListComponentFactory = get()
        )
    }

}