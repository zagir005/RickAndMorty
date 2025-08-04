package com.zagirlek.rickandmortytest.data.network.dto

import com.google.gson.annotations.SerializedName

enum class StatusDTO {

    @SerializedName("Alive")
    ALIVE,

    @SerializedName("Dead")
    DEAD,

    @SerializedName("unknown")
    UNKNOWN,
}
