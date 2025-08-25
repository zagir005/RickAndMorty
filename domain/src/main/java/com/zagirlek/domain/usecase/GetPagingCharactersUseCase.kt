package com.zagirlek.domain.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.zagirlek.core.model.Character
import com.zagirlek.core.model.CharacterFilters
import com.zagirlek.domain.repository.PagingCharacterRepository
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class GetPagingCharactersUseCase(
    private val pagingCharacterRepository: PagingCharacterRepository
) {

    operator fun invoke(filter: CharacterFilters): Flow<PagingData<Character>> =
        pagingCharacterRepository.getPagedFlowCharacters(characterFilters = filter)
}