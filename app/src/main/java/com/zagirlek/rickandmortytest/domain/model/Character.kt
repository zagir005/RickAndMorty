package com.zagirlek.rickandmortytest.domain.model

data class Character(
    val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val gender: CharacterGender,
    val origin: CharacterLocation,
    val location: CharacterLocation,
    val image: String,
    val url: String
)
