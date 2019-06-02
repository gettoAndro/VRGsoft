package com.getto.vrgsoft.data.nyt.service

import io.reactivex.Single
import javax.inject.Inject


class NytServiceImpl @Inject constructor(val mostPopularApi: MostPopularApi): NyTypesService {

    override fun getEmailed(): Single<Emailed> = this.mostPopularApi.getEmailed()
}