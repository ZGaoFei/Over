package com.example.jetpack

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.base.BaseActivity
import com.example.base.home.HomeListAdapter
import com.example.base.home.Item
import com.example.jetpack.databinding.ActivityJectpackHomeBinding

class JectpackHomeActivity : BaseActivity() {

    private lateinit var homeBinding: ActivityJectpackHomeBinding
    private lateinit var list: MutableList<Item>

    override fun bindingView() {
        super.bindingView()

        homeBinding = ActivityJectpackHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)
    }

    override fun initData() {
        list = mutableListOf(
            Item("LiveDataTest", "live_data_test")
        )
    }

    override fun initView() {
        homeBinding.jectpackRecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        homeBinding.jectpackRecyclerView.adapter = HomeListAdapter(list)
    }
}