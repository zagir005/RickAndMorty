package com.zagirlek.rickandmortytest.domain.usecase

import com.zagirlek.rickandmortytest.data.mapper.toDomain
import com.zagirlek.rickandmortytest.domain.model.Character
import com.zagirlek.rickandmortytest.domain.repository.LocalCharacterRepository

class GetSingleCharacterUseCase(
    private val localCharacterRepository: LocalCharacterRepository
) {

    suspend operator fun invoke(characterId: Int): Character? =
        localCharacterRepository.getCharacterById(characterId)?.toDomain()
}