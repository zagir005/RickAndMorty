package com.zagirlek.data.repository

import com.zagirlek.core.local.CharacterDatabase
import com.zagirlek.core.model.Character
import com.zagirlek.core.model.CharacterFilters
import com.zagirlek.data.mapper.toDomain
import com.zagirlek.domain.repository.LocalCharacterRepository

class LocalCharacterRepositoryImpl(
    characterDatabase: CharacterDatabase
): LocalCharacterRepository {
    private val characterDao = characterDatabase.characterDao()

    override suspend fun getFilterCharacters(
        characterFilters: CharacterFilters
    ): List<Character> =
        characterDao.getCharacters(
            name = characterFilters.name,
            status = characterFilters.status,
            species = characterFilters.species,
            gender = characterFilters.gender
        ).map { it.toDomain() }

    override suspend fun getCharacterById(id: Int): Character? =
        characterDao.getCharacterById(id)?.toDomain()
}