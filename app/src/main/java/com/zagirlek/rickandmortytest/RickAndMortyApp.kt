package com.zagirlek.rickandmortytest

import android.app.Application
import androidx.room.Room
import com.zagirlek.rickandmortytest.data.local.CharacterDatabase
import com.zagirlek.rickandmortytest.data.network.retrofit.RickAndMortyRetrofit
import com.zagirlek.rickandmortytest.data.repository.RemoteCharacterRepositoryImpl
import com.zagirlek.rickandmortytest.data.repository.CharactersPagingRepositoryImpl
import com.zagirlek.rickandmortytest.data.repository.LocalCharacterRepositoryImpl
import com.zagirlek.rickandmortytest.domain.repository.RemoteCharacterRepository
import com.zagirlek.rickandmortytest.domain.repository.CharactersPagingRepository
import com.zagirlek.rickandmortytest.domain.repository.LocalCharacterRepository

class RickAndMortyApp: Application() {
    lateinit var characterDatabase: CharacterDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        characterDatabase = Room.databaseBuilder(
            context = this,
            klass = CharacterDatabase::class.java,
            name = "CharacterDatabase"
        )
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }

    val localCharacterRepository: LocalCharacterRepository by lazy {
        LocalCharacterRepositoryImpl(
            characterDatabase
        )
    }

    val charactersPagingRepository: CharactersPagingRepository by lazy {
        CharactersPagingRepositoryImpl(
            charactersService = RickAndMortyRetrofit.provideCharacterService(),
            characterDatabase = characterDatabase
        )
    }
}