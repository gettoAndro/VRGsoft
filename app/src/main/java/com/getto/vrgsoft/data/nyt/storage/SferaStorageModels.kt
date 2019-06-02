package com.getto.vrgsoft.data.nyt.storage

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey



@Entity(tableName = "countries")
data class StorageCountries(
        @PrimaryKey(autoGenerate = true) val id: Long,
    var alpha3Code: String,
//    @TypeConverters(BorderConverters::class)
    var borders: String,
    var nativeName: String
)
