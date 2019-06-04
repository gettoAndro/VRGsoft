package com.getto.vrgsoft.ui.shared.description

import com.getto.vrgsoft.data.nyt.service.Results
import com.getto.vrgsoft.ui.base.IPresenter
import com.getto.vrgsoft.ui.base.IView

interface SharedDetailsContract {

    interface View : IView {
    }

    interface Presenter<V : View> : IPresenter<V> {
        fun insertShared(results: Results)
    }


}