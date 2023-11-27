package com.example.otherlibrary.multilist

import android.util.Log
import android.util.SparseArray
import com.example.base.multilist.ItemRes
import com.example.otherlibrary.R
import com.example.otherlibrary.multilist.viewholder.ItemThreeViewHolder

object ItemResHelper {
    private var map: SparseArray<ItemRes> = SparseArray()

    init {
        map[0] = ItemRes(R.layout.item_one, "com.example.otherlibrary.multilist.viewholder.ItemOneViewHolder")
        map[1] = ItemRes(R.layout.item_two, "com.example.otherlibrary.multilist.viewholder.ItemTwoViewHolder")
        map[2] = ItemThreeViewHolder::class.qualifiedName?.let { ItemRes(R.layout.item_three, it) }
    }

    fun getItemRes(type: Int): ItemRes {
        return map[type]
    }
}