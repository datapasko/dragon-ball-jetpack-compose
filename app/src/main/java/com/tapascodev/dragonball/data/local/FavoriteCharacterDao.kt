package com.tapascodev.dragonball.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.tapascodev.dragonball.data.local.entity.FavoriteCharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCharacterDao {

    @Query("SELECT * FROM favorite_character_table")
    fun getAllFavoriteImages(): PagingSource<Int, FavoriteCharacterEntity>

    @Upsert
    suspend fun insertFavoriteCharacter(character: FavoriteCharacterEntity)

    @Delete
    suspend fun deleteFavoriteCharacter(character: FavoriteCharacterEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_character_table WHERE id = :id)")
    suspend fun isCharacterFavorite(id: String): Boolean

    @Query("SELECT id FROM favorite_character_table")
    fun getFavoriteCharacterIds(): Flow<List<Int>>
}