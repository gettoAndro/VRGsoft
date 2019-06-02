package com.getto.vrgsoft.ui.viewed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getto.vrgsoft.R
import com.getto.vrgsoft.ui.base.BaseFragment

class ViewedFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_viewed, container, false)
    }
}