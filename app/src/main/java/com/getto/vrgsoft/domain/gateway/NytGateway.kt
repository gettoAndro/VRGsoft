package com.getto.vrgsoft.domain.gateway

import com.getto.vrgsoft.data.nyt.service.Emailed
import com.getto.vrgsoft.data.nyt.service.Results
import io.reactivex.Single


interface NytGateway {

    fun getEmailed(): Single<Emailed>

    fun getShared(): Single<Emailed>

    fun getViewed(): Single<Emailed>

    fun getFavorite(): Single<List<Results>>

    fun insertFavorite(results: Results) : Single<String>


}