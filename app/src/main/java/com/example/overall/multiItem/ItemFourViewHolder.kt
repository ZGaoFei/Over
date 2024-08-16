package com.example.overall.multiItem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.base.multilist.MultiListViewHolder
import com.example.otherlibrary.multilist.data.ItemOneData
import com.example.overall.R

class ItemFourViewHolder(itemView: View) : MultiListViewHolder<ItemOneData>(itemView) {

    private var title: TextView? = null

    init {
        title = itemView.findViewById(R.id.tv_item_four)
    }

    override fun update(data: ItemOneData) {
        title?.text = data.title
    }

    companion object {
        @JvmStatic
        fun create(parent: ViewGroup): ItemFourViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_four_view, parent, false)
            return ItemFourViewHolder(view)
        }
    }

}