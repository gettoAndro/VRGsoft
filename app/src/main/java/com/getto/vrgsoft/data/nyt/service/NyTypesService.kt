package com.getto.vrgsoft.data.nyt.service

import io.reactivex.Single


/**
 * Created by ujujzk on 21.06.2018
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

interface NyTypesService {

    fun getEmailed(): Single<Emailed>

}