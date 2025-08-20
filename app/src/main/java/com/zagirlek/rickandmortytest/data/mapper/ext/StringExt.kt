package com.zagirlek.rickandmortytest.data.mapper.ext

fun String.getIdFromLocationUrl(): Int = if (isNotEmpty()) substringAfterLast('/').toInt() else -1

fun String.getPageNumberFromUrl(): Int? =
    Regex("page=(\\d+)")
        .find(this)
        ?.groupValues
        ?.getOrNull(1)
        ?.toIntOrNull()

fun String.getEpisodeId(): Int =
    Regex("episode/(\\d+)")
        .find(this)
        ?.groupValues
        ?.getOrNull(1)
        ?.toInt()!!