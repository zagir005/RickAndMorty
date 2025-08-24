package com.zagirlek.domain.usecase

import com.zagirlek.core.model.Character

interface GetSingleCharacterUseCase {

    suspend operator fun invoke(id: Int): Character?
}