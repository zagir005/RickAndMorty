package com.zagirlek.domain.repository

import com.zagirlek.data.local.entities.CharacterEntity
import com.zagirlek.core.model.CharacterGender
import com.zagirlek.core.model.CharacterStatus

interface LocalCharacterRepository {

    suspend fun getFilterCharacters(
        name: String?,
        status: CharacterStatus?,
        species: String?,
        type: String?,
        gender: CharacterGender?
    ): List<CharacterEntity>

    suspend fun getCharacterById(
        id: Int
    ): CharacterEntity?
}