package com.tapascodev.dragonball.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tapascodev.dragonball.data.local.entity.FavoriteCharacterEntity

@Database(
    entities = [FavoriteCharacterEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DragonBallDatabase: RoomDatabase() {
    abstract fun favoriteCharacterDao(): FavoriteCharacterDao
}