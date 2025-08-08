package com.zagirlek.rickandmortytest.domain.model

enum class CharacterGender{
    FEMALE, MALE, GENDERLESS, UNKNOWN;

    override fun toString(): String {
        return when(this){
            FEMALE -> "Female"
            MALE -> "Male"
            GENDERLESS -> "Genderless"
            UNKNOWN -> "Unknown"
        }
    }
}
