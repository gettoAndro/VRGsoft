package com.getto.vrgsoft.data.nyt.storage

import io.reactivex.Single




interface CountryStorage {

    fun putCountries(countries: List<StorageCountries>): Single<String>
    fun getAllCountries(): Single<List<StorageCountries>>

}