package com.zagirlek.rickandmortytest.data.network.dto


data class CharacterInfoDTO(
    val id: Int,
    val name: String,
    val status: StatusDTO,
    val species: String,
    val gender: GenderDTO,
    val origin: LocationDTO,
    val location: LocationDTO,
    val image: String,
    val url: String
)
