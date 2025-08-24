package com.zagirlek.core.model

data class CharactersPage(
    val count: Int,
    val pages: Int,
    val next: Int?,
    val previous: Int?,
    val characterList: List<Character>
)
