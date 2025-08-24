package com.zagirlek.data.repository

import com.zagirlek.data.mapper.toDomain
import com.zagirlek.core.model.Character
import com.zagirlek.core.model.CharactersPage
import com.zagirlek.core.network.service.CharacterService
import com.zagirlek.domain.repository.RemoteCharacterRepository

class RemoteCharacterRepositoryImpl(
    private val characterService: CharacterService
): RemoteCharacterRepository {

    override suspend fun getFilterCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?,
        page: Int?
    ): Result<CharactersPage> = runCatching {

        val characters = characterService
            .filterCharactersPage(
                name = name,
                status = status,
                species = species,
                type = type,
                gender = gender,
                page = page
            )
            .toDomain()

        characters
    }


    override suspend fun getCharacterById(id: Int): Result<Character> = runCatching {
        characterService
            .getCharacterById(id)
            .toDomain()
    }


}