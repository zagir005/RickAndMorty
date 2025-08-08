package com.zagirlek.rickandmortytest.data.network.service

import com.zagirlek.rickandmortytest.data.network.dto.CharacterDTO
import com.zagirlek.rickandmortytest.data.network.dto.CharactersPageDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CharacterService {

    @GET("character/")
    suspend fun filterCharactersPage(
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("species") species: String? = null,
        @Query("type") type: String? = null,
        @Query("gender") gender: String? = null,
        @Query("page") page: Int? = null
    ): CharactersPageDTO

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterDTO

}