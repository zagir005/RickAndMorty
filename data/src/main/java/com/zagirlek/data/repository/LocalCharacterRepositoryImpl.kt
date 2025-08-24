package com.zagirlek.data.repository

import com.zagirlek.data.local.CharacterDatabase
import com.zagirlek.data.local.entities.CharacterEntity
import com.zagirlek.core.model.CharacterGender
import com.zagirlek.core.model.CharacterStatus
import com.zagirlek.domain.repository.LocalCharacterRepository

class LocalCharacterRepositoryImpl(
    private val characterDatabase: CharacterDatabase
): LocalCharacterRepository {
    private val characterDao = characterDatabase.characterDao()

    override suspend fun getFilterCharacters(
        name: String?,
        status: CharacterStatus?,
        species: String?,
        type: String?,
        gender: CharacterGender?
    ): List<CharacterEntity> =
        characterDao.getCharacters(
            name = name,
            status = status,
            species = species,
            gender = gender
        )

    override suspend fun getCharacterById(id: Int): CharacterEntity? =
        characterDao.getCharacterById(id)
}