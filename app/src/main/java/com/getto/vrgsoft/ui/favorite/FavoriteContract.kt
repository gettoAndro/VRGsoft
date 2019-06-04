package com.getto.vrgsoft.ui.favorite

import com.getto.vrgsoft.data.nyt.service.Results
import com.getto.vrgsoft.ui.base.IPresenter
import com.getto.vrgsoft.ui.base.IView

interface FavoriteContract {

    interface View : IView {
        fun onSuccess(results: List<Results>)
    }

    interface Presenter<V : View> : IPresenter<V> {
        fun getFavorite()
    }
}