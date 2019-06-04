package com.getto.vrgsoft.ui.viewed

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getto.vrgsoft.R
import com.getto.vrgsoft.data.nyt.service.Emailed
import com.getto.vrgsoft.data.nyt.service.Results
import com.getto.vrgsoft.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_viewed.*
import javax.inject.Inject

class ViewedFragment : BaseFragment(), ViewedContract.View {



    private val results = ArrayList<Results>()
    private val adapter = ViewedAdapter(results)

    @Inject
    lateinit var presenter : ViewedContract.Presenter<ViewedContract.View>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponent.inject(this)
        presenter.bindView(this)
        rc_viewed.layoutManager = LinearLayoutManager(getActivity())
        rc_viewed.adapter = adapter
        presenter.getViewed()
        showProgress(true)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_viewed, container, false)
    }

    override fun onSuccess(emailed: Emailed) {
        emailed.results?.let { results.addAll(it) }
        adapter.notifyDataSetChanged()
        showProgress(false)
    }


}