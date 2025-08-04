package com.zagirlek.rickandmortytest.data.network.dto

import com.google.gson.annotations.SerializedName

enum class GenderDTO {
    @SerializedName("Female")
    FEMALE,

    @SerializedName("Male")
    MALE,

    @SerializedName("Genderless")
    GENDERLESS,

    @SerializedName("unknown")
    UNKNOWN
}
