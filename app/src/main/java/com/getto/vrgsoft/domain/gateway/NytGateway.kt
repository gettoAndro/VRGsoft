package com.getto.vrgsoft.domain.gateway

import com.getto.vrgsoft.data.nyt.service.Emailed
import io.reactivex.Single


interface NytGateway {

    fun getEmailed(): Single<Emailed>

   // fun retrieveCountries(): Single<List<CountryInfo>>


}