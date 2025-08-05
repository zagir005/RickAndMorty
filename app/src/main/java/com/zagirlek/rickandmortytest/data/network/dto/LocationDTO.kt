package com.zagirlek.rickandmortytest.data.network.dto

import com.google.gson.annotations.SerializedName

data class LocationDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("dimension") val dimension: Int,
)
