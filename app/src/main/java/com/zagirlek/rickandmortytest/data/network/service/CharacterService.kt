package com.zagirlek.rickandmortytest.data.network.service

import com.zagirlek.rickandmortytest.data.network.dto.CharacterInfoDTO
import com.zagirlek.rickandmortytest.data.network.dto.CharactersListDTO
import retrofit2.http.GET


interface CharacterService {

    @GET("character")
    suspend fun getCharactersList(): CharactersListDTO

    @GET("character/{id}")
    suspend fun getCharacterById(): CharacterInfoDTO
}