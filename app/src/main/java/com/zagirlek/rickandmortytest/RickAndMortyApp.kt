package com.zagirlek.rickandmortytest

import android.app.Application
import androidx.room.Room
import com.zagirlek.rickandmortytest.data.local.CharacterDatabase
import com.zagirlek.rickandmortytest.data.network.retrofit.RickAndMortyRetrofit
import com.zagirlek.rickandmortytest.data.repository.CharacterRepositoryImpl
import com.zagirlek.rickandmortytest.data.repository.CharactersPagingRepositoryImpl
import com.zagirlek.rickandmortytest.domain.repository.CharacterRepository
import com.zagirlek.rickandmortytest.domain.repository.CharactersPagingRepository

class RickAndMortyApp: Application() {
    val characterRepository: CharacterRepository by lazy {
        CharacterRepositoryImpl(
            characterService = RickAndMortyRetrofit.provideCharacterService()
        )
    }

    val charactersPagingRepository: CharactersPagingRepository by lazy {
        CharactersPagingRepositoryImpl(
            charactersService = RickAndMortyRetrofit.provideCharacterService()
        )
    }

    val characterDatabase by lazy{
        Room.databaseBuilder(
            context = this,
            klass = CharacterDatabase::class.java,
            name = "CharacterDatabase"
        )
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }
}