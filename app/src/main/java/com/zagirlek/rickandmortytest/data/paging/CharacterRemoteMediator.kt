package com.zagirlek.rickandmortytest.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.zagirlek.rickandmortytest.data.local.CharacterDatabase
import com.zagirlek.rickandmortytest.data.local.entities.CharacterEntity
import com.zagirlek.rickandmortytest.data.local.entities.RemoteKeyEntity
import com.zagirlek.rickandmortytest.data.mapper.getPageNumberFromUrl
import com.zagirlek.rickandmortytest.data.mapper.toLocal
import com.zagirlek.rickandmortytest.data.network.service.CharacterService
import com.zagirlek.rickandmortytest.domain.model.Character
import com.zagirlek.rickandmortytest.domain.model.CharacterFilters
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val filters: CharacterFilters,
    private val database: CharacterDatabase,
    private val network: CharacterService
): RemoteMediator<Int, CharacterEntity>() {

    private val remoteKeyDao = database.remoteKeyDao()
    private val characterDao = database.characterDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {

        val pageKey = when(loadType){
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(true)
            LoadType.APPEND -> {
                remoteKeyDao.remoteKeysByFilters(filters)?.nextPageKey ?: return MediatorResult.Success(true)
            }
        }


        return try {
            val response = with(filters){
                network.filterCharactersPage(
                    name = name,
                    status = status?.name ?: "",
                    species = species,
                    type = type,
                    gender = gender?.name ?: "",
                    page = pageKey
                )
            }

            val nextPageKey = response.info.next?.getPageNumberFromUrl()
            val prevPageKey = response.info.previous?.getPageNumberFromUrl()

            database.withTransaction {
                if (loadType == LoadType.REFRESH){
                    characterDao.deleteByFilters(
                        name = filters.name,
                        status = filters.status?.name,
                        species = filters.species,
                        gender = filters.gender?.name
                    )
                    remoteKeyDao.deleteByFilters(filters)
                }
                remoteKeyDao.insertOrReplace(
                    RemoteKeyEntity(
                        filters = filters,
                        pageKey = pageKey,
                        nextPageKey = nextPageKey,
                        previousPageKey = prevPageKey)
                    )

                characterDao.insertAll(
                    characters = response.characterList.map { it.toLocal() }
                )
            }

            MediatorResult.Success(nextPageKey == null)
        }catch (e: IOException){
            MediatorResult.Error(e)
        }catch (e: HttpException){
            MediatorResult.Error(e)
        }
    }
}