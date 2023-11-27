package com.example.base.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initView()
    }

    open fun initData() {

    }

    open fun initView() {

    }
}