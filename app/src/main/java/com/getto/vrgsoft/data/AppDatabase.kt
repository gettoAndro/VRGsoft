package com.getto.vrgsoft.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.getto.vrgsoft.data.nyt.storage.FavoriteDao
import com.getto.vrgsoft.data.nyt.storage.StorageFavorite

@Database(entities = [(StorageFavorite::class)], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getFavoriteDao(): FavoriteDao
}