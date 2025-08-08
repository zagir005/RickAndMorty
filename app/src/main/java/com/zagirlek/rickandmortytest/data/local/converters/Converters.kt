package com.zagirlek.rickandmortytest.data.local.converters

import androidx.room.TypeConverter
import com.zagirlek.rickandmortytest.domain.model.CharacterFilters
import com.zagirlek.rickandmortytest.domain.model.CharacterGender
import com.zagirlek.rickandmortytest.domain.model.CharacterStatus

class Converters {
    @TypeConverter
    fun toGender(value: String): CharacterGender = enumValueOf(name = value)

    @TypeConverter
    fun fromGender(value: CharacterGender): String = value.name

    @TypeConverter
    fun toStatus(value: String): CharacterStatus = enumValueOf(name = value)

    @TypeConverter
    fun fromStatus(value: CharacterStatus): String = value.name

    @TypeConverter
    fun fromCharacterFilters(value: CharacterFilters?): String {
        return value?.let {
            val name = it.name ?: ""
            val status = it.status?.name ?: ""
            val species = it.species ?: ""
            val gender = it.gender?.name ?: ""
            "$name/$status/$species/$gender"
        } ?: ""
    }

    @TypeConverter
    fun toCharacterFilters(value: String?): CharacterFilters? {
        if (value.isNullOrBlank()) return null
        val parts = value.split("/")
        return CharacterFilters(
            name = parts.getOrNull(0)?.ifBlank { null },
            status = parts.getOrNull(1)?.let { if (it.isNotBlank()) CharacterStatus.valueOf(it) else null },
            species = parts.getOrNull(2)?.ifBlank { null },
            gender = parts.getOrNull(3)?.let { if (it.isNotBlank()) CharacterGender.valueOf(it) else null }
        )
    }


}