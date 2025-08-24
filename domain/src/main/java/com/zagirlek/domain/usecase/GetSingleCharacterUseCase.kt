package com.zagirlek.domain.usecase

import com.zagirlek.data.mapper.toDomain
import com.zagirlek.core.model.Character
import com.zagirlek.domain.repository.LocalCharacterRepository
import com.zagirlek.domain.usecase.GetSingleCharacterUseCase

class GetSingleCharacterUseCase(
    private val localCharacterRepository: LocalCharacterRepository
) {

    suspend operator fun invoke(characterId: Int): Character? =
        localCharacterRepository.getCharacterById(characterId)?.toDomain()
}