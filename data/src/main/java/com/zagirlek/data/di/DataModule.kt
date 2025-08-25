package com.zagirlek.data.di

import com.zagirlek.core.local.CharacterDatabase
import com.zagirlek.core.network.service.CharacterService
import com.zagirlek.data.repository.LocalCharacterRepositoryImpl
import com.zagirlek.data.repository.PagingCharacterRepositoryImpl
import com.zagirlek.data.repository.RemoteCharacterRepositoryImpl
import com.zagirlek.domain.repository.LocalCharacterRepository
import com.zagirlek.domain.repository.PagingCharacterRepository
import com.zagirlek.domain.repository.RemoteCharacterRepository
import org.koin.dsl.module

val dataModule = module {

    single<RemoteCharacterRepository> {
        RemoteCharacterRepositoryImpl(
            characterService = get<CharacterService>()
        )
    }

    single<LocalCharacterRepository> {
        LocalCharacterRepositoryImpl(
            characterDatabase = get<CharacterDatabase>()
        )
    }

    single<PagingCharacterRepository> {
        PagingCharacterRepositoryImpl(
            characterDatabase = get(),
            characterService = get()
        )
    }
}