package com.getto.vrgsoft.data.nyt

import com.catchman.data.DataLogger
import com.getto.vrgsoft.data.nyt.service.Emailed
import com.getto.vrgsoft.data.nyt.service.NyTypesService
import com.getto.vrgsoft.data.nyt.service.Results
import com.getto.vrgsoft.data.nyt.storage.FavoriteStorage
import com.getto.vrgsoft.data.nyt.storage.StorageFavorite
import com.getto.vrgsoft.domain.gateway.NytGateway
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject



class NytGatewayImpl @Inject constructor(val service: NyTypesService, val storage: FavoriteStorage, val logger: DataLogger) :
    NytGateway {



    override fun getShared(): Single<Emailed> =
            service.getShared()
                    .subscribeOn(Schedulers.newThread())


    override fun getEmailed(): Single<Emailed> =
        service.getEmailed()
                .subscribeOn(Schedulers.newThread())

    override fun getViewed(): Single<Emailed> =
        service.getViewed()
                .subscribeOn(Schedulers.newThread())


    override fun getFavorite(): Single<List<Results>> =
            storage.getAllFavorite()
                    .flatMapObservable { f -> Observable.fromIterable(f) }
                    .map(CountryFromStorageToPresentation())
                    .toList()


    override fun insertFavorite(results: Results) : Single<String> =
            storage.putFavorite(StorageFavorite(results.id , results.adx_keywords, null, null, null, null, null, null, results.title, results.abstract, results.published_date, null, null))
                .subscribeOn(Schedulers.newThread())





}