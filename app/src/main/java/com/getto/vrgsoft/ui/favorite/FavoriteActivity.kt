package com.getto.vrgsoft.ui.favorite

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.getto.vrgsoft.R
import com.getto.vrgsoft.data.nyt.service.Results
import com.getto.vrgsoft.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_favorite.*
import javax.inject.Inject

class FavoriteActivity : BaseActivity(), FavoriteContract.View {

    @Inject
    lateinit var presenter: FavoriteContract.Presenter<FavoriteContract.View>

    private val results = ArrayList<Results>()
    private val adapter = FavoriteAdapter(results)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        activityComponent.inject(this)
        presenter.bindView(this)
        rc_fav.layoutManager = LinearLayoutManager(this)
        rc_fav.adapter = adapter
        presenter.getFavorite()
        toolbar.setNavigationOnClickListener { v -> onBackPressed() }
    }

    override fun onSuccess(results: List<Results>) {

        this.results.addAll(results)
        adapter.notifyDataSetChanged()
    }
}