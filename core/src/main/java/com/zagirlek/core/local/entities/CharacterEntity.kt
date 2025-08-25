package com.zagirlek.core.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.zagirlek.core.local.converters.Converters
import com.zagirlek.core.model.CharacterGender
import com.zagirlek.core.model.CharacterStatus

@Entity(
    tableName = "characters",
)
@TypeConverters(
    Converters::class
)
data class CharacterEntity(
    @PrimaryKey() val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val gender: CharacterGender,
    val origin: String,
    val location: String,
    val episode: List<Int>,
    val image: String
)
