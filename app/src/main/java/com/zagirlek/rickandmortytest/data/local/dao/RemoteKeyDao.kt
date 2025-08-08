package com.zagirlek.rickandmortytest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zagirlek.rickandmortytest.data.local.entities.RemoteKeyEntity
import com.zagirlek.rickandmortytest.domain.model.CharacterFilters

@Dao
interface RemoteKeyDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKeyEntity)

    @Query("SELECT * FROM remote_keys WHERE filters = :filters")
    suspend fun remoteKeysByFilters(filters: CharacterFilters): RemoteKeyEntity?

    @Query("DELETE FROM remote_keys WHERE filters = :filters")
    suspend fun deleteByFilters(filters: CharacterFilters)
}
