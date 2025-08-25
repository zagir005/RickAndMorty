package com.zagirlek.core.model

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
