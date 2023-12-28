package com.example.overall

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.base.BaseActivity
import com.example.base.home.HomeListAdapter
import com.example.base.home.Item
import com.example.overall.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    private lateinit var list: MutableList<Item>

    override fun bindingView() {
        super.bindingView()

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
    }

    override fun initData() {
        list = mutableListOf(
            Item("base home", "base_home"),
            Item("jetpack home", "jectpack_home"),
            Item("other home", "other_home"),
            Item("proxy home", "proxy_home"),
        )
    }

    override fun initView() {
        mainBinding.recyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mainBinding.recyclerView.adapter = HomeListAdapter(list)
    }
}