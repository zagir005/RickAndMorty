package com.zagirlek.rickandmortytest.domain.model

import com.google.gson.annotations.SerializedName


enum class CharacterStatus{
    ALIVE, DEAD, UNKNOWN;

    override fun toString(): String {
        return when(this){
            ALIVE -> "Alive"
            DEAD -> "Dead"
            UNKNOWN -> "Unknown"
        }
    }
}
