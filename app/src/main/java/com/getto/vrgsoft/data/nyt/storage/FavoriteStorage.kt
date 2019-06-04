package com.getto.vrgsoft.data.nyt.storage

import io.reactivex.Single




interface FavoriteStorage {

    fun putFavorite(storageFavorite: StorageFavorite) : Single<String>
    fun getAllFavorite(): Single<List<StorageFavorite>>

}