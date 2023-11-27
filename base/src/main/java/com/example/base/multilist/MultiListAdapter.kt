package com.example.base.multilist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.Class.forName

abstract class MultiListAdapter(private val list: MutableList<MultiListItemData>) :
    RecyclerView.Adapter<MultiListViewHolder>() {

    abstract fun getItemRes(type: Int): ItemRes

    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiListViewHolder {
        val itemRes = getItemRes(viewType)
        val viewHolderClazz = itemRes.viewHolderClazz
        val resId = itemRes.resId
        val view = LayoutInflater.from(parent.context).inflate(resId, parent, false)

        val clazz = forName(viewHolderClazz)
        val constructor = clazz.getConstructor(View::class.java)
        return constructor.newInstance(view) as MultiListViewHolder
    }

    override fun onBindViewHolder(holder: MultiListViewHolder, position: Int) {
        holder.update(list[position])
    }

}