package com.zagirlek.rickandmortytest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zagirlek.rickandmortytest.data.local.dao.CharacterDao
import com.zagirlek.rickandmortytest.data.local.dao.RemoteKeyDao
import com.zagirlek.rickandmortytest.data.local.entities.CharacterEntity
import com.zagirlek.rickandmortytest.data.local.entities.RemoteKeyEntity

@Database(
    entities = [CharacterEntity::class, RemoteKeyEntity::class],
    version = 1
)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    abstract fun remoteKeyDao(): RemoteKeyDao
}