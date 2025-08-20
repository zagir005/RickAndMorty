package com.zagirlek.rickandmortytest.data.mapper

import android.util.Log
import com.zagirlek.rickandmortytest.data.local.entities.CharacterEntity
import com.zagirlek.rickandmortytest.data.mapper.ext.getEpisodeId
import com.zagirlek.rickandmortytest.data.mapper.ext.getIdFromLocationUrl
import com.zagirlek.rickandmortytest.data.mapper.ext.getPageNumberFromUrl
import com.zagirlek.rickandmortytest.data.network.dto.CharacterDTO
import com.zagirlek.rickandmortytest.data.network.dto.CharactersPageDTO
import com.zagirlek.rickandmortytest.data.network.dto.GenderDTO
import com.zagirlek.rickandmortytest.data.network.dto.LocationDTO
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
