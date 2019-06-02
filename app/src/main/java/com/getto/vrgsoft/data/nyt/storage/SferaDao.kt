package com.getto.vrgsoft.data.nyt.storage

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import io.reactivex.Single




//TODO implement BaseDao https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1

@Dao
interface CountriesDao {

    @Transaction
    fun updateData(countries: List<StorageCountries>){
        deleteAll()
        insertAll(countries)
    }

    @Query("SELECT * FROM countries")
    fun getAll(): Single<List<StorageCountries>>

    @Insert(onConflict = REPLACE)
    fun insertAll(countries: List<StorageCountries>)

    @Query("DELETE from countries")
    fun deleteAll()
}
