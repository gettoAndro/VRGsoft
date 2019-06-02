package com.getto.vrgsoft.ui.emailed

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getto.vrgsoft.R
import com.getto.vrgsoft.data.nyt.service.Emailed
import com.getto.vrgsoft.data.nyt.service.Results
import com.getto.vrgsoft.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_emailed.*
import javax.inject.Inject

class EmailedFragment : BaseFragment(), EmailedContract.View {

    private val results = ArrayList<Results>()
    private val adapter = EmailedAdapter(results)

    @Inject
    lateinit var presenter : EmailedContract.Presenter<EmailedContract.View>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponent.inject(this)
        presenter.bindView(this)
        rc_emailed.layoutManager = LinearLayoutManager(getActivity())
        rc_emailed.adapter = adapter
        presenter.getEmailed()
        showProgress(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_emailed, container, false)
    }

    override fun onSuccess(emailed: Emailed) {
        emailed.results?.let { results.addAll(it) }
        adapter.notifyDataSetChanged()
        showProgress(false)
    }
}

