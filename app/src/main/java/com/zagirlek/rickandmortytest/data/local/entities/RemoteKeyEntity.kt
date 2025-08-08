package com.zagirlek.rickandmortytest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.zagirlek.rickandmortytest.data.local.converters.Converters
import com.zagirlek.rickandmortytest.domain.model.CharacterFilters

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
