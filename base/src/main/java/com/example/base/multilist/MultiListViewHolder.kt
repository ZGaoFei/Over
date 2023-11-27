package com.example.base.multilist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.base.multilist.MultiListItemData

abstract class MultiListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun update(data: MultiListItemData)
}