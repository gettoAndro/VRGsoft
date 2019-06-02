package com.getto.vrgsoft.data.nyt.service

import io.reactivex.Single
import retrofit2.http.GET


interface MostPopularApi {

    @GET("emailed/30.json?api-key=lSkhKKoDYuumVqFDQUogvtyuhBVtDc28")
    fun getEmailed(): Single<Emailed>

}