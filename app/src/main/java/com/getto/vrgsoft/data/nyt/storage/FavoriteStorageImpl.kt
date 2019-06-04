package com.getto.vrgsoft.data.nyt.storage

import com.getto.vrgsoft.data.AppDatabase
import io.reactivex.Single
import javax.inject.Inject



class FavoriteStorageImpl @Inject constructor(database: AppDatabase) : FavoriteStorage {

    private val favoriteDao = database.getFavoriteDao()



    override fun putFavorite(storageFavorite: StorageFavorite): Single<String> =
        Single.fromCallable {
           // favoriteDao.updateData(favoriteStorage)
            favoriteDao.insert(storageFavorite)
            "OK"
        }

    override fun getAllFavorite(): Single<List<StorageFavorite>> = favoriteDao.getAll()

}