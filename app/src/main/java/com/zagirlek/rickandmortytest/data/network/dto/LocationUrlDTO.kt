package com.zagirlek.rickandmortytest.data.network.dto

import com.google.gson.annotations.SerializedName

data class LocationUrlDTO(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
