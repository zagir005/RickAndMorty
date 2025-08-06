package com.zagirlek.rickandmortytest.data.network.utils

import retrofit2.http.Query

data class CharactersFilters(
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val type: String? = null,
    val gender: String? = null,
)