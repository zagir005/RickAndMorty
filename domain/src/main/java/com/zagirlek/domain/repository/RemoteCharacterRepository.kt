package com.zagirlek.domain.repository


import com.zagirlek.core.model.CharactersPage
import com.zagirlek.core.model.Character

interface RemoteCharacterRepository {

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