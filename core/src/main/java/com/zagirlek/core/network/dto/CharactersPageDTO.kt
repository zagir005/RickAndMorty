package com.zagirlek.core.network.dto

import com.google.gson.annotations.SerializedName

data class CharactersPageDTO(
    @SerializedName("info") val info: Info,
    @SerializedName("results") val characterList: List<CharacterDTO>
){
    data class Info(
        @SerializedName("count") val count: Int,
        @SerializedName("pages") val pages: Int,
        @SerializedName("next") val next: String?,
        @SerializedName("prev") val previous: String?
    )
}