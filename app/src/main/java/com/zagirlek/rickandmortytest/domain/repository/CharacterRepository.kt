package com.zagirlek.rickandmortytest.domain.repository


import com.zagirlek.rickandmortytest.domain.model.Character
import com.zagirlek.rickandmortytest.domain.model.CharactersPage

interface CharacterRepository {

    suspend fun getFilterCharacters(
        name: String? = null,
        status: String? = null,
        species: String? = null,
        type: String? = null,
        gender: String? = null,
        page: Int? = null
    ): Result<CharactersPage>

    suspend fun getCharacterById(
        id: Int
    ): Result<Character>

}