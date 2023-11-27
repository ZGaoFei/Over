package com.example.otherlibrary

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.base.BaseActivity
import com.example.base.home.HomeListAdapter
import com.example.base.home.Item
import com.example.otherlibrary.databinding.ActivityOtherHomeBinding

class OtherHomeActivity : BaseActivity() {

    private lateinit var mainBinding: ActivityOtherHomeBinding

    private lateinit var list: MutableList<Item>

    override fun bindingView() {
        super.bindingView()

        mainBinding = ActivityOtherHomeBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
    }

    override fun initData() {
        list = mutableListOf(
            Item("other home", "multi_list"),
        )
    }

    override fun initView() {
        mainBinding.otherHomeRecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mainBinding.otherHomeRecyclerView.adapter = HomeListAdapter(list)
    }
}