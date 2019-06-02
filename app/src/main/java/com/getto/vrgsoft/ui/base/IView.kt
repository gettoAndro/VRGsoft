package com.getto.vrgsoft.ui.base



interface IView {

    fun showProgress(show: Boolean)

    fun isNetworkConnected() : Boolean

    fun toast(message: String)

}