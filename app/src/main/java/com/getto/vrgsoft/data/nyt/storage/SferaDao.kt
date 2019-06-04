package com.getto.vrgsoft.data.nyt.storage

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import io.reactivex.Single




//TODO implement BaseDao https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1

@Dao
interface FavoriteDao : BaseDao<StorageFavorite> {

    @Transaction
    fun updateData(countries: List<StorageFavorite>){
        deleteAll()
        insertAll(countries)
    }

    @Query("SELECT * FROM favorite")
    fun getAll(): Single<List<StorageFavorite>>

    @Insert(onConflict = REPLACE)
    fun insertAll(countries: List<StorageFavorite>)

    @Insert(onConflict = REPLACE)
    override fun insert(entity: StorageFavorite)

    @Query("DELETE from favorite")
    fun deleteAll()
}
