package com.zagirlek.domain.usecase

import com.zagirlek.core.model.Character
import com.zagirlek.domain.repository.LocalCharacterRepository

class GetSingleCharacterUseCase(
    private val localCharacterRepository: LocalCharacterRepository
) {

    suspend operator fun invoke(characterId: Int): Character? =
        localCharacterRepository.getCharacterById(characterId)
}