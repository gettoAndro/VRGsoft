package com.getto.vrgsoft.ui.base

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.Toast
import com.getto.vrgsoft.VrgApp
import com.getto.vrgsoft.di.component.ActivityComponent
import com.getto.vrgsoft.di.component.DaggerActivityComponent
import com.getto.vrgsoft.di.modul.ActivityModule
import com.getto.vrgsoft.ui.LoadingDialog
import com.getto.vrgsoft.ui.LoadingView
import com.getto.vrgsoft.util.loge
import com.getto.vrgsoft.util.logi
import java.io.BufferedInputStream
import java.io.File
import java.io.FileNotFoundException

abstract class BaseActivity : AppCompatActivity(), IView, BaseFragment.Callback {

    lateinit var activityComponent: ActivityComponent

    private var mLoadingView: LoadingView? = null

    protected val fm = supportFragmentManager

    private var background: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent((application as VrgApp).appComponent)
                .build()
        mLoadingView = LoadingDialog.view(supportFragmentManager)
    }


    override fun onDestroy() {
        super.onDestroy()
        releaseBackground()
    }


    override fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            connectivityManager.activeNetworkInfo?.isConnected ?: false
        } else false
    }

    override fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    protected fun drawBackground(background: ImageView, fileName: String) {
        this.background = background
        val dir = "drawable"
        val assets = assets
        try {
            BufferedInputStream(assets.open(dir + File.separator + fileName)).use {
                background.setImageDrawable(BitmapDrawable(resources, BitmapFactory.decodeStream(it)))
            }
        } catch (e: FileNotFoundException) {
            "Can't get bitmap from assets: $dir/$fileName".loge(e)
        }
    }

    private fun releaseBackground() {
        if (this.background?.drawable is BitmapDrawable) {
            (this.background?.drawable as BitmapDrawable).bitmap?.recycle()
        }
    }

    fun replaceFragment(fragment: Fragment, containerId: Int, addToBackStack: Boolean, animate: Boolean) {
        val fragmentName = fragment.javaClass.simpleName
        replaceFragment(fragment, fragmentName, containerId, addToBackStack, animate)
    }

    fun replaceFragment(fragment: Fragment, fragmentName: String, containerId: Int, addToBackStack: Boolean, animate: Boolean) {
        if (!isFinishing) {
            if (fm.findFragmentByTag(fragmentName) == null) {
                fm.beginTransaction().apply {
                   // if (animate) setCustomAnimations(R.anim.fade_in, 0)
                    replace(containerId, fragment, fragmentName)
                    if (addToBackStack) addToBackStack(fragmentName)
                }.commitAllowingStateLoss()
            }
        }
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            mLoadingView?.showLoadingIndicator("Пожайлуйста, подождите")
        } else {
            mLoadingView?.hideLoadingIndicator()
        }
    }

    protected fun showDialog(title: String, message: String){
        AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok) { dialog, which ->  message.logi()}
                .show()
    }
}