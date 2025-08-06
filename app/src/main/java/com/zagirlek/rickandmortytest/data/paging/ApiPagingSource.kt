package com.zagirlek.rickandmortytest.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zagirlek.rickandmortytest.data.mapper.getPageNumberFromUrl
import com.zagirlek.rickandmortytest.data.network.dto.CharacterDTO
import com.zagirlek.rickandmortytest.data.network.dto.CharactersPageDTO
import com.zagirlek.rickandmortytest.data.network.service.CharacterService
import com.zagirlek.rickandmortytest.domain.model.CharactersFilters
import com.zagirlek.rickandmortytest.domain.model.Character
import kotlinx.coroutines.delay
import retrofit2.HttpException

class ApiPagingSource(
    private val characterService: CharacterService,
    private val charactersFilters: CharactersFilters
): PagingSource<Int, CharacterDTO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDTO> {
        return runCatching {
            val currentPage = params.key ?: 1

            val responseData = with(receiver = charactersFilters){
                characterService.filterCharactersPage(
                    name = name,
                    status = status?.name,
                    species = species,
                    type = type,
                    gender = gender?.name,
                    page = currentPage
                )
            }

            delay(2000L)

            LoadResult.Page(
                data = responseData.characterList,
                prevKey = responseData.info.previous?.getPageNumberFromUrl(),
                nextKey = responseData.info.next?.getPageNumberFromUrl()
            )
        }.getOrElse { exception ->
            if (exception is HttpException && exception.code() == 404){
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }else{
                LoadResult.Error(exception)
            }
        }

    }

    override fun getRefreshKey(state: PagingState<Int, CharacterDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}