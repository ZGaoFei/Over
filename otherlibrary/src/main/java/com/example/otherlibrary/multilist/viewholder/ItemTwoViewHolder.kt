package com.example.otherlibrary.multilist.viewholder

import android.view.View
import android.widget.TextView
import com.example.otherlibrary.R
import com.example.base.multilist.MultiListItemData
import com.example.base.multilist.MultiListViewHolder
import com.example.otherlibrary.multilist.data.ItemTwoData

class ItemTwoViewHolder(itemView: View) : MultiListViewHolder(itemView) {

    private var title: TextView? = null
    private var content: TextView? = null
    private var index: TextView? = null

    init {
        title = itemView.findViewById(R.id.tv_two_item_title)
        content = itemView.findViewById(R.id.tv_two_item_content)
        index = itemView.findViewById(R.id.tv_two_item_index)
    }

    override fun update(data: MultiListItemData) {
        data as ItemTwoData
        title?.text = data.title
        content?.text = data.content
        index?.text = "${data.type}"
    }
}