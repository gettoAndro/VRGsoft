package com.getto.vrgsoft.data.nyt

import com.catchman.data.DataLogger
import com.getto.vrgsoft.data.nyt.service.Emailed
import com.getto.vrgsoft.data.nyt.service.NyTypesService
import com.getto.vrgsoft.data.nyt.storage.CountryStorage
import com.getto.vrgsoft.domain.gateway.NytGateway
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject



class NytGatewayImpl @Inject constructor(val service: NyTypesService, val storage: CountryStorage, val logger: DataLogger) :
    NytGateway {

    override fun getEmailed(): Single<Emailed> =
        service.getEmailed()
                .subscribeOn(Schedulers.newThread())

}