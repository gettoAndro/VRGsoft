package com.getto.vrgsoft.data.nyt.storage

import com.getto.vrgsoft.data.AppDatabase
import io.reactivex.Single
import javax.inject.Inject



class CountryStorageImpl @Inject constructor(database: AppDatabase) : CountryStorage {

    private val countriesDao = database.getCountriesDao()



    override fun putCountries(countries: List<StorageCountries>): Single<String> =
        Single.fromCallable {
            countriesDao.updateData(countries)
            "OK"
        }

    override fun getAllCountries(): Single<List<StorageCountries>> = countriesDao.getAll()

}