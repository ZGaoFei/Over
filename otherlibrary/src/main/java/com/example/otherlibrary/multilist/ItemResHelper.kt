package com.example.otherlibrary.multilist

import android.os.Build
import android.util.SparseArray
import androidx.annotation.RequiresApi
import com.example.base.multilist.ItemRes
import com.example.otherlibrary.multilist.viewholder.ItemThreeViewHolder

object ItemResHelper {
    private var map: SparseArray<ItemRes> = SparseArray()

    init {
        map[0] = ItemRes("com.example.otherlibrary.multilist.viewholder.ItemOneViewHolder")
        map[1] = ItemRes("com.example.otherlibrary.multilist.viewholder.ItemTwoViewHolder")
        map[2] = ItemThreeViewHolder::class.qualifiedName?.let { ItemRes(it) }
        map[3] = ItemRes("com.example.overall.multiItem.ItemFourViewHolder")
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun getItemRes(type: Int): ItemRes {
        if (!map.contains(type)) {
            return map[0]
        }
        return map[type]
    }
}