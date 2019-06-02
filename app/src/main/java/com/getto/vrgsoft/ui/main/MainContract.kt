package com.getto.vrgsoft.ui.main

import com.getto.vrgsoft.ui.base.IPresenter
import com.getto.vrgsoft.ui.base.IView

interface MainContract {

    interface View : IView {


    }

    interface Presenter<V : View> : IPresenter<V> {

    }

}