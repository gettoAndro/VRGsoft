package com.getto.vrgsoft.ui.shared

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getto.vrgsoft.R
import com.getto.vrgsoft.data.nyt.service.Emailed
import com.getto.vrgsoft.data.nyt.service.Results
import com.getto.vrgsoft.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_shared.*
import javax.inject.Inject

class SharedFragment : BaseFragment(), SharedContract.View {


    private val results = ArrayList<Results>()
    private val adapter = SharedAdapter(results)

    @Inject
    lateinit var presenter : SharedContract.Presenter<SharedContract.View>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponent.inject(this)
        presenter.bindView(this)
        rc_shared.layoutManager = LinearLayoutManager(getActivity())
        rc_shared.adapter = adapter
        presenter.getShared()
        showProgress(true)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shared, container, false)
    }

    override fun onSuccess(emailed: Emailed) {
        emailed.results?.let { results.addAll(it) }
        adapter.notifyDataSetChanged()
        showProgress(false)
    }
}