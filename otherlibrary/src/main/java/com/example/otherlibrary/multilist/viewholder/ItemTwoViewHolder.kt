package com.example.otherlibrary.multilist.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.otherlibrary.R
import com.example.base.multilist.MultiListViewHolder
import com.example.otherlibrary.multilist.data.ItemTwoData

class ItemTwoViewHolder(itemView: View) : MultiListViewHolder<ItemTwoData>(itemView) {

    private var title: TextView? = null
    private var content: TextView? = null
    private var index: TextView? = null

    init {
        title = itemView.findViewById(R.id.tv_two_item_title)
        content = itemView.findViewById(R.id.tv_two_item_content)
        index = itemView.findViewById(R.id.tv_two_item_index)
    }

    override fun update(data: ItemTwoData) {
        title?.text = data.title
        content?.text = data.content
        index?.text = "${data.type}"
    }

    companion object {
        @JvmStatic
        fun create(parent: ViewGroup): ItemTwoViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_two, parent, false)
            return ItemTwoViewHolder(view)
        }

    }
}