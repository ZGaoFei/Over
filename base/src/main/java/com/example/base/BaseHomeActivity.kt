package com.example.base

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.base.BaseActivity
import com.example.base.databinding.ActivityBaseHomeBinding
import com.example.base.home.HomeListAdapter
import com.example.base.home.Item

class BaseHomeActivity : BaseActivity() {

    private lateinit var homeBinding: ActivityBaseHomeBinding
    private lateinit var list: MutableList<Item>

    override fun bindingView() {
        super.bindingView()

        homeBinding = ActivityBaseHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)
    }

    override fun initData() {
        super.initData()

        list = mutableListOf(
            Item("base", "")
        )
    }

    override fun initView() {
        super.initView()

        homeBinding.baseRecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        homeBinding.baseRecyclerView.adapter = HomeListAdapter(list)
    }
}