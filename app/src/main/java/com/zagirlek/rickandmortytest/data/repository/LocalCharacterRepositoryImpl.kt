package com.zagirlek.rickandmortytest.data.repository

import com.zagirlek.rickandmortytest.data.local.CharacterDatabase
import com.zagirlek.rickandmortytest.data.local.entities.CharacterEntity
import com.zagirlek.rickandmortytest.domain.model.Character
import com.zagirlek.rickandmortytest.domain.model.CharacterGender
import com.zagirlek.rickandmortytest.domain.model.CharacterStatus
import com.zagirlek.rickandmortytest.domain.model.CharactersPage
import com.zagirlek.rickandmortytest.domain.repository.LocalCharacterRepository
import com.zagirlek.rickandmortytest.domain.repository.RemoteCharacterRepository

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