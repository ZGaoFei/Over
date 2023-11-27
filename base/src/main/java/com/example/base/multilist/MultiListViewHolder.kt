package com.example.base.multilist

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class MultiListViewHolder<T : MultiListItemData>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun update(data: T)
}