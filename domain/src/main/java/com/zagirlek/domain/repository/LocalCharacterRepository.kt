package com.zagirlek.domain.repository

import com.zagirlek.core.model.Character
import com.zagirlek.core.model.CharacterFilters

interface LocalCharacterRepository {

    suspend fun getFilterCharacters(
        characterFilters: CharacterFilters
    ): List<Character>

    suspend fun getCharacterById(
        id: Int
    ): Character?
}