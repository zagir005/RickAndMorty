package com.zagirlek.domain.di

import com.zagirlek.core.local.CharacterDatabase
import com.zagirlek.core.network.service.CharacterService
import com.zagirlek.domain.repository.LocalCharacterRepository
import com.zagirlek.domain.usecase.GetPagingCharactersUseCase
import com.zagirlek.domain.usecase.GetSingleCharacterUseCase
import org.koin.dsl.module

val domainModule = module{
    single<GetSingleCharacterUseCase>{
        GetSingleCharacterUseCase(
            localCharacterRepository = get<LocalCharacterRepository>()
        )
    }

    single<GetPagingCharactersUseCase> {
        GetPagingCharactersUseCase(
            charactersService = get<CharacterService>(),
            characterDatabase = get<CharacterDatabase>()
        )
    }
}