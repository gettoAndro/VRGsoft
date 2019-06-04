package com.getto.vrgsoft.ui.emailed.description

import com.getto.vrgsoft.data.nyt.service.Emailed
import com.getto.vrgsoft.data.nyt.service.Results
import com.getto.vrgsoft.ui.base.IPresenter
import com.getto.vrgsoft.ui.base.IView

interface EmailedDetailsContract {


    interface View : IView {
        fun onSuccess()
    }

    interface Presenter<V : View> : IPresenter<V> {
        fun insertEmailed(results: Results)
    }

}