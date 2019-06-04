package com.getto.vrgsoft.data.nyt.service

import io.reactivex.Single
import retrofit2.http.GET


interface MostPopularApi {

    @GET("emailed/30.json?api-key=lSkhKKoDYuumVqFDQUogvtyuhBVtDc28")
    fun getEmailed(): Single<Emailed>

    @GET("shared/30.json?api-key=lSkhKKoDYuumVqFDQUogvtyuhBVtDc28")
    fun getShared() : Single<Emailed>



    @GET("viewed/30.json?api-key=lSkhKKoDYuumVqFDQUogvtyuhBVtDc28")
    fun getViewed() : Single<Emailed>


}