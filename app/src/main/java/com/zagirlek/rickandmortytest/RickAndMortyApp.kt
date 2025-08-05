package com.zagirlek.rickandmortytest

import android.app.Application
import com.zagirlek.rickandmortytest.data.network.retrofit.RickAndMortyRetrofit
import com.zagirlek.rickandmortytest.data.repository.CharacterRepositoryImpl
import com.zagirlek.rickandmortytest.domain.repository.CharacterRepository

class RickAndMortyApp: Application() {
    val characterRepository: CharacterRepository by lazy {
        CharacterRepositoryImpl(
            characterService = RickAndMortyRetrofit.provideCharacterService()
        )
    }


}