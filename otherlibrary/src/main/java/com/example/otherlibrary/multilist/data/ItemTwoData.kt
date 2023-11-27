package com.example.otherlibrary.multilist.data

import com.example.base.multilist.MultiListItemData

class ItemTwoData(
    override val type: Int,
    override val title: String,
    override val content: String,
) : MultiListItemData(
    type, title,
    content
) {
}