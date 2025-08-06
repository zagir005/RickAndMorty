package com.zagirlek.rickandmortytest.domain.model

import com.google.gson.annotations.SerializedName

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
