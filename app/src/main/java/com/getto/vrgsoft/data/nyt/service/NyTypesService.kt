package com.getto.vrgsoft.data.nyt.service

import io.reactivex.Single


interface NyTypesService {

    fun getEmailed(): Single<Emailed>

    fun getShared() : Single<Emailed>

    fun getViewed() : Single<Emailed>

}