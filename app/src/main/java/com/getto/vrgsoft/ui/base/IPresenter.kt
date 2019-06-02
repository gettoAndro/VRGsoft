package com.getto.vrgsoft.ui.base


interface IPresenter<V : IView> {

    fun bindView(view: V)

    fun unbindView()

    fun dispose()
}