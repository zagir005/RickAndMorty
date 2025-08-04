package com.zagirlek.rickandmortytest.data.network.retrofit

import com.zagirlek.rickandmortytest.data.network.service.CharacterService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RickAndMortyRetrofit{

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    fun provideCharacterService() =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterService::class.java)

}

