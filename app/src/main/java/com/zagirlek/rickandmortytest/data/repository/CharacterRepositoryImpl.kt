package com.zagirlek.rickandmortytest.data.repository

import android.util.Log
import com.zagirlek.rickandmortytest.data.mapper.toDomain
import com.zagirlek.rickandmortytest.data.network.service.CharacterService
import com.zagirlek.rickandmortytest.domain.model.Character
import com.zagirlek.rickandmortytest.domain.model.CharactersPage
import com.zagirlek.rickandmortytest.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val characterService: CharacterService
): CharacterRepository {

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