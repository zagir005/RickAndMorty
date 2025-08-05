package com.zagirlek.rickandmortytest.data.mapper

import android.util.Log
import com.zagirlek.rickandmortytest.data.network.dto.CharacterDTO
import com.zagirlek.rickandmortytest.data.network.dto.CharactersPageDTO
import com.zagirlek.rickandmortytest.data.network.dto.GenderDTO
import com.zagirlek.rickandmortytest.data.network.dto.LocationUrlDTO
import com.zagirlek.rickandmortytest.data.network.dto.StatusDTO
import com.zagirlek.rickandmortytest.domain.model.Character
import com.zagirlek.rickandmortytest.domain.model.CharacterGender
import com.zagirlek.rickandmortytest.domain.model.CharacterLocation
import com.zagirlek.rickandmortytest.domain.model.CharacterStatus
import com.zagirlek.rickandmortytest.domain.model.CharactersPage

fun CharactersPageDTO.toDomain(): CharactersPage =
    CharactersPage(
        count = info.count,
        pages = info.pages,
        next = info.next?.getPageNumberFromUrl(),
        previous = info.previous?.getPageNumberFromUrl(),
        characterList = characterList.toDomain()
    )

fun List<CharacterDTO>.toDomain(): List<Character> = mapIndexed {index, it ->
    Log.d("LISTTAG", this[index].toString())
    Log.d("LISTTAG", it.toDomain().toString())
    it.toDomain()
}

fun CharacterDTO.toDomain() = Character(
    id = id,
    name = name,
    status = status.toDomain(),
    species = species,
    gender = gender.toDomain(),
    origin = origin.toDomain(),
    location = origin.toDomain(),
    image = image,
    url = url
)

fun LocationUrlDTO.toDomain() = CharacterLocation(
    id = url.getIdFromLocationUrl(),
    name = name
)

fun StatusDTO.toDomain(): CharacterStatus {
    return when(this){
        StatusDTO.DEAD -> CharacterStatus.DEAD
        StatusDTO.ALIVE -> CharacterStatus.ALIVE
        StatusDTO.UNKNOWN -> CharacterStatus.UNKNOWN
    }
}

fun GenderDTO.toDomain(): CharacterGender {
    return when(this){
        GenderDTO.MALE -> CharacterGender.MALE
        GenderDTO.GENDERLESS -> CharacterGender.GENDERLESS
        GenderDTO.FEMALE -> CharacterGender.FEMALE
        GenderDTO.UNKNOWN -> CharacterGender.UNKNOWN
    }
}


fun String.getIdFromLocationUrl(): Int = if (isNotEmpty()) substringAfterLast('/').toInt() else -1

fun String.getPageNumberFromUrl(): Int =
    Regex("page=(\\d+)")
        .find(this)
        ?.groupValues
        ?.getOrNull(1)
        ?.toIntOrNull()!!
