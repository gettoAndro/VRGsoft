package com.getto.vrgsoft.data.nyt

import com.getto.vrgsoft.data.nyt.service.CountryInfo
import com.getto.vrgsoft.data.nyt.service.Media
import com.getto.vrgsoft.data.nyt.service.Results
import com.getto.vrgsoft.data.nyt.storage.StorageFavorite
import com.google.gson.Gson

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function
import java.util.ArrayList


class CountryFromServiceForStorage : Function<List<CountryInfo>, Single<List<StorageFavorite>>> {
    override fun apply(t: List<CountryInfo>): Single<List<StorageFavorite>> =
        Observable.fromIterable(t)
            .map { StorageFavorite(0,it.alpha3Code, Gson().toJson(it.borders), it.nativeName) }
            .toList()
}

class CountryFromStorageToPresentation : Function<StorageFavorite, Results> {
    override fun apply(t: StorageFavorite): Results =
        Results(null, t.adx_keywords, t.subsection, null, t.count_type, null, null, t.id, null, null, null, null, t.title, t.abstract, t.published_date, t.source, null, ArrayList<Media>(), null)

}
