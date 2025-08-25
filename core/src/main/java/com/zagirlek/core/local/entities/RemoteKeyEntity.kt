package com.zagirlek.core.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.zagirlek.core.local.converters.Converters
import com.zagirlek.core.model.CharacterFilters

@Entity(
    tableName = "remote_keys"
)
@TypeConverters(
    Converters::class
)
data class RemoteKeyEntity(
    @PrimaryKey
    val filters: CharacterFilters,
    val pageKey: Int,
    val nextPageKey: Int?,
    val previousPageKey: Int?,
)
