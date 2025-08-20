package com.zagirlek.rickandmortytest.domain.repository

import com.zagirlek.rickandmortytest.data.local.entities.CharacterEntity
import com.zagirlek.rickandmortytest.domain.model.CharacterGender
import com.zagirlek.rickandmortytest.domain.model.CharacterStatus

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