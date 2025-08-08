package com.zagirlek.rickandmortytest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.zagirlek.rickandmortytest.data.local.converters.Converters
import com.zagirlek.rickandmortytest.domain.model.CharacterGender
import com.zagirlek.rickandmortytest.domain.model.CharacterStatus

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
    val image: String
)
