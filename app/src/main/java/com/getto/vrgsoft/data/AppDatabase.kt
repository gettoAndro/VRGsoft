package com.getto.vrgsoft.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.getto.vrgsoft.data.nyt.storage.CountriesDao
import com.getto.vrgsoft.data.nyt.storage.StorageCountries

@Database(entities = [(StorageCountries::class)], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCountriesDao(): CountriesDao
}