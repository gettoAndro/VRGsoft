package com.getto.vrgsoft.data.nyt.storage

import android.arch.persistence.room.*


@Dao
interface BaseDao<T> {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(entity: T)

        @Update
        fun update(entity: T)

        @Delete
       fun delete(entity: T)
}

