package com.zagirlek.rickandmortytest.domain.model

data class CharactersFilters(
    val name: String? = null,
    val status: CharacterStatus? = null,
    val species: String? = null,
    val type: String? = null,
    val gender: CharacterGender? = null,
)