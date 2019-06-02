package com.getto.vrgsoft.data.nyt

import com.getto.vrgsoft.data.nyt.service.CountryInfo
import com.getto.vrgsoft.data.nyt.storage.StorageCountries
import com.google.gson.Gson

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function




class CountryFromServiceForStorage : Function<List<CountryInfo>, Single<List<StorageCountries>>> {
    override fun apply(t: List<CountryInfo>): Single<List<StorageCountries>> =
        Observable.fromIterable(t)
            .map { StorageCountries(0,it.alpha3Code, Gson().toJson(it.borders), it.nativeName) }
            .toList()
}

class CountryFromStorageToPresentation : Function<StorageCountries, com.example.domain.model.CountryInfo> {
    override fun apply(t: StorageCountries): com.example.domain.model.CountryInfo =
        com.example.domain.model.CountryInfo(t.alpha3Code, t.borders, t.nativeName)

}
