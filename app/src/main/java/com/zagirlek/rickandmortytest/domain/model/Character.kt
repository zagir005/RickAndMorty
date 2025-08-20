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
    val url: String,
    val episode: List<Int>,
){
    companion object{
        fun emptyCharacter() = Character(
            id = -1,
            name = "",
            status = CharacterStatus.UNKNOWN,
            species = "",
            gender = CharacterGender.UNKNOWN,
            origin = CharacterLocation(-1,""),
            location = CharacterLocation(-1,""),
            image = "",
            url = "",
            episode = emptyList()
        )
    }
}
