package com.getto.vrgsoft.ui.base

import io.reactivex.disposables.CompositeDisposable



abstract class BasePresenter<V : IView> constructor(protected val compositeDisposable: CompositeDisposable) :
    IPresenter<V> {

    @Volatile
    var view: V? = null

    override fun bindView(view: V) {
        this.view = view
    }

    override fun unbindView() {
        this.view = null
    }

    override fun dispose() = compositeDisposable.dispose()

    fun isViewAttached() = this.view != null

}