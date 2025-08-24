package com.zagirlek.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.zagirlek.core.ext.getPageNumberFromUrl
import com.zagirlek.data.mapper.toLocal
import com.zagirlek.core.model.CharacterFilters
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException


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

        val response = try {
            delay(2000)
            with(filters){
                network.filterCharactersPage(
                    name = name,
                    status = status?.name ?: "",
                    species = species,
                    type = type,
                    gender = gender?.name ?: "",
                    page = pageKey
                )
            }
        }catch (e: IOException){
            return MediatorResult.Error(e)
        }catch (e: HttpException){
            return MediatorResult.Error(e)
        }

        return try {
            val nextPageKey = response.info.next?.getPageNumberFromUrl()
            val prevPageKey = response.info.previous?.getPageNumberFromUrl()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
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
                        previousPageKey = prevPageKey
                    )
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