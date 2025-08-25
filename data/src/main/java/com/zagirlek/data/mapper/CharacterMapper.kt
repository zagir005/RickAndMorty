package com.zagirlek.data.mapper

import android.util.Log
import com.zagirlek.core.ext.getEpisodeId
import com.zagirlek.core.ext.getIdFromLocationUrl
import com.zagirlek.core.ext.getPageNumberFromUrl
import com.zagirlek.core.local.entities.CharacterEntity
import com.zagirlek.core.network.dto.CharacterDTO
import com.zagirlek.core.network.dto.CharactersPageDTO
import com.zagirlek.core.network.dto.GenderDTO
import com.zagirlek.core.network.dto.LocationDTO
import com.zagirlek.core.network.dto.StatusDTO
import com.zagirlek.core.model.CharacterGender
import com.zagirlek.core.model.CharacterLocation
import com.zagirlek.core.model.CharacterStatus
import com.zagirlek.core.model.CharactersPage
import com.zagirlek.core.model.Character


fun CharactersPageDTO.toDomain(): CharactersPage =
    CharactersPage(
        count = info.count,
        pages = info.pages,
        next = info.next?.getPageNumberFromUrl(),
        previous = info.previous?.getPageNumberFromUrl(),
        characterList = characterList.toDomain()
    )

fun List<CharacterDTO>.toDomain(): List<Character> = mapIndexed { index, it ->
    it.toDomain()
}

fun CharacterDTO.toLocal() = CharacterEntity(
    id = id,
    name = name,
    status = status.toDomain(),
    species = species,
    gender = gender.toDomain(),
    origin = origin.name,
    location = location.name,
    image = image,
    episode = episode.map { it.getEpisodeId() }
)

fun CharacterEntity.toDomain() = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    gender = gender,
    origin = CharacterLocation(0, origin),
    location = CharacterLocation(0, location),
    image = image,
    url = "",
    episode = episode
)
fun CharacterDTO.toDomain() = Character(
    id = id,
    name = name,
    status = status.toDomain(),
    species = species,
    gender = gender.toDomain(),
    origin = origin.toDomain(),
    location = location.toDomain(),
    image = image,
    url = url,
    episode = episode.map { it.getEpisodeId() }
)

fun LocationDTO.toDomain() = CharacterLocation(
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
