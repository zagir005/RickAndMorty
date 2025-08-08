package com.zagirlek.rickandmortytest.data.network.dto

import com.google.gson.annotations.SerializedName


data class CharacterDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: StatusDTO,
    @SerializedName("species") val species: String,
    @SerializedName("gender") val gender: GenderDTO,
    @SerializedName("origin") val origin: LocationDTO,
    @SerializedName("location") val location: LocationDTO,
    @SerializedName("image") val image: String,
    @SerializedName("url") val url: String
)
