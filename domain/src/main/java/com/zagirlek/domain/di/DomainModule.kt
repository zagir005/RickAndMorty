package com.zagirlek.domain.di

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
            pagingCharacterRepository = get()
        )
    }
}