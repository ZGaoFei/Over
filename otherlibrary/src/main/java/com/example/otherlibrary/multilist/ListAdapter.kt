package com.example.otherlibrary.multilist

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.base.multilist.ItemRes
import com.example.base.multilist.MultiListAdapter
import com.example.base.multilist.MultiListItemData

class ListAdapter(list: MutableList<MultiListItemData>) : MultiListAdapter(list) {

    private val helper: ItemResHelper = ItemResHelper

    @RequiresApi(Build.VERSION_CODES.R)
    override fun getItemRes(type: Int): ItemRes {
        return helper.getItemRes(type)
    }
}