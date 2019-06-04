package com.getto.vrgsoft.ui.viewed

import com.getto.vrgsoft.data.nyt.service.Emailed
import com.getto.vrgsoft.ui.base.IPresenter
import com.getto.vrgsoft.ui.base.IView

interface ViewedContract {

    interface View : IView {
        fun onSuccess(emailed: Emailed)
    }

    interface Presenter<V : View> : IPresenter<V> {
        fun getViewed()
    }
}