package com.zagirlek.data.di

import com.zagirlek.core.local.CharacterDatabase
import com.zagirlek.core.network.service.CharacterService
import com.zagirlek.data.paging.CharacterRemoteMediator
import com.zagirlek.data.repository.CharactersPagingRepositoryImpl
import com.zagirlek.domain.repository.CharactersPagingRepository
import org.koin.dsl.module

val dataModule = module {

    single<CharactersPagingRepository> {
        CharactersPagingRepositoryImpl(
            charactersService = get<CharacterService>(),
            characterDatabase = get<CharacterDatabase>()
        )
    }

    single<CharacterRemoteMediator> {
        CharacterRemoteMediator(
            filters =
        )
    }
}