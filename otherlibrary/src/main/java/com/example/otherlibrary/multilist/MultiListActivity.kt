package com.example.otherlibrary.multilist

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.base.BaseActivity
import com.example.base.multilist.MultiListAdapter
import com.example.base.multilist.MultiListItemData
import com.example.otherlibrary.databinding.ActivityMultiListBinding
import com.example.otherlibrary.multilist.data.ItemOneData
import com.example.otherlibrary.multilist.data.ItemThreeData
import com.example.otherlibrary.multilist.data.ItemTwoData

class MultiListActivity : BaseActivity() {

    private lateinit var binding: ActivityMultiListBinding

    private lateinit var list: MutableList<MultiListItemData>

    override fun bindingView() {
        super.bindingView()
        binding = ActivityMultiListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initData() {
        super.initData()

        list = mutableListOf(
            ItemOneData(0, "haha", "haha", 0),
            ItemTwoData(1, "haha", "haha"),
            ItemOneData(0, "haha", "haha", 0),
            ItemTwoData(1, "haha", "haha"),
            ItemTwoData(1, "haha", "haha"),
            ItemTwoData(1, "haha", "haha"),
            ItemThreeData(2, "", "", "https://pic4.zhimg.com/v2-570aad7dbef96e49ab01780ab7ebc98b_r.jpg"),
            ItemOneData(0, "haha", "haha", 0),
            ItemOneData(0, "haha", "haha", 0),
            ItemOneData(0, "haha", "haha", 0),
        )
    }

    override fun initView() {
        super.initView()

        binding.multiList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.multiList.adapter = ListAdapter(list)
    }
}