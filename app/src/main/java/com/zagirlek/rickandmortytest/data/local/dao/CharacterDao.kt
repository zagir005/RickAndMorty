package com.zagirlek.rickandmortytest.data.local.dao

import android.annotation.SuppressLint
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.paging.LimitOffsetPagingSource
import com.zagirlek.rickandmortytest.data.local.converters.Converters
import com.zagirlek.rickandmortytest.data.local.entities.CharacterEntity
import com.zagirlek.rickandmortytest.domain.model.CharacterGender
import com.zagirlek.rickandmortytest.domain.model.CharacterStatus

@Dao
@TypeConverters(
    Converters::class
)
interface CharacterDao {

    @SuppressLint("RestrictedApi")
    @Query("""
        SELECT * FROM characters
        WHERE (:name IS NULL OR name LIKE '%' || :name || '%')
          AND (:status IS NULL OR status = :status)
          AND (:species IS NULL OR species LIKE '%' || :species || '%')
          AND (:gender IS NULL OR gender = :gender)
        ORDER BY id ASC
    """)
    fun getCharactersPagingSource(
        name: String? = null,
        status: CharacterStatus? = null,
        species: String? = null,
        gender: CharacterGender? = null,
    ): PagingSource<Int, CharacterEntity>

    @Query("""
       SELECT * FROM characters
        WHERE (:name IS NULL OR name LIKE '%' || :name || '%')
          AND (:status IS NULL OR status = :status)
          AND (:species IS NULL OR species LIKE '%' || :species || '%')
          AND (:gender IS NULL OR gender = :gender)
        ORDER BY id ASC
    """)
    fun getCharacters(
        name: String? = null,
        status: CharacterStatus? = null,
        species: String? = null,
        gender: CharacterGender? = null,
    ): List<CharacterEntity>

    @Query("""
        DELETE FROM characters
        WHERE (:name IS NULL OR name LIKE '%' || :name || '%')
          AND (:status IS NULL OR status = :status)
          AND (:species IS NULL OR species LIKE '%' || :species || '%')
          AND (:gender IS NULL OR gender = :gender)
    """)
    suspend fun deleteByFilters(
        name: String?,
        status: String?,
        species: String?,
        gender: String?
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CharacterEntity)

    @Query("DELETE FROM characters")
    suspend fun clearAll()

    @Query("SELECT * FROM characters WHERE id = :id LIMIT 1")
    suspend fun getCharacterById(id: Int): CharacterEntity?


}
