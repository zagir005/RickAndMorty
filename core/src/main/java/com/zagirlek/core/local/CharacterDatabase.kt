package com.zagirlek.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zagirlek.core.local.dao.CharacterDao
import com.zagirlek.core.local.dao.RemoteKeyDao
import com.zagirlek.core.local.entities.CharacterEntity
import com.zagirlek.core.local.entities.RemoteKeyEntity

@Database(
    entities = [CharacterEntity::class, RemoteKeyEntity::class],
    version = 1
)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    abstract fun remoteKeyDao(): RemoteKeyDao
}