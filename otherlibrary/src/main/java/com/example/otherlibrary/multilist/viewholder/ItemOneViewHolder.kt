package com.example.otherlibrary.multilist.viewholder

import android.view.View
import android.widget.TextView
import com.example.otherlibrary.R
import com.example.base.multilist.MultiListItemData
import com.example.base.multilist.MultiListViewHolder
import com.example.otherlibrary.multilist.data.ItemOneData

class ItemOneViewHolder(itemView: View) : MultiListViewHolder(itemView) {

    private var title: TextView? = null

    init {
        title = itemView.findViewById(R.id.tv_one_item)
    }

    override fun update(data: MultiListItemData) {
        data as ItemOneData
        title?.text = "${data.index}"
    }

}