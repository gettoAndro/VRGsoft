package com.getto.vrgsoft.ui.main

import com.getto.vrgsoft.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class MainPresenter<V : MainContract.View> @Inject constructor(compositeDisposable: CompositeDisposable) : BasePresenter<V>(compositeDisposable),
    MainContract.Presenter<V> {




}