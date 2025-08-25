package com.zagirlek.core.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import com.zagirlek.core.local.converters.Converters
import com.zagirlek.core.local.entities.RemoteKeyEntity
import com.zagirlek.core.model.CharacterFilters

@Dao
@TypeConverters(
    Converters::class
)
interface RemoteKeyDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKeyEntity)

    @Query("SELECT * FROM remote_keys WHERE filters = :filters")
    suspend fun remoteKeysByFilters(filters: CharacterFilters): RemoteKeyEntity?

    @Query("DELETE FROM remote_keys WHERE filters = :filters")
    suspend fun deleteByFilters(filters: CharacterFilters)
}
