package com.getto.vrgsoft.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.view.MenuItem
import com.getto.vrgsoft.R
import com.getto.vrgsoft.ui.base.BaseActivity
import com.getto.vrgsoft.ui.emailed.EmailedFragment
import com.getto.vrgsoft.ui.shared.SharedFragment
import com.getto.vrgsoft.ui.viewed.ViewedFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    @Inject
    lateinit var presenter: MainContract.Presenter<MainContract.View>
    private val adapter = FragmentsAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent.inject(this)
        presenter.bindView(this)
        adapter.addFragment(EmailedFragment())
        adapter.addFragment(SharedFragment())
        adapter.addFragment(ViewedFragment())
        pager.adapter = adapter
        pager.addOnPageChangeListener(this)
        navigation.setOnNavigationItemSelectedListener(this)

    //    replaceFragment(EmailedFragment(), R.id.fr_container, false, false)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.navigation_emailed -> pager.currentItem = 0
            R.id.navigation_shared -> pager.currentItem = 1
            R.id.navigation_viewed -> pager.currentItem = 2
        }
        return true
    }


    override fun onPageScrollStateChanged(p0: Int) {
    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
    }

    override fun onPageSelected(p0: Int) {
        when(p0){
            0 -> navigation.selectedItemId = R.id.navigation_emailed
            1 -> navigation.selectedItemId = R.id.navigation_shared
            2 -> navigation.selectedItemId = R.id.navigation_viewed
        }
    }


}
