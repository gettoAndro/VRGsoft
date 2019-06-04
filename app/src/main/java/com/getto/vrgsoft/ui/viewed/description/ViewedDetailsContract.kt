package com.getto.vrgsoft.ui.viewed.description

import com.getto.vrgsoft.data.nyt.service.Results
import com.getto.vrgsoft.ui.base.IPresenter
import com.getto.vrgsoft.ui.base.IView

interface ViewedDetailsContract {


    interface View : IView {
        fun onSuccess()
    }

    interface Presenter<V : View> : IPresenter<V> {
        fun insertViewed(results: Results)
    }


}