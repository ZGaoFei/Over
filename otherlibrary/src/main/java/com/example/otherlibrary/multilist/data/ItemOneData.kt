package com.example.otherlibrary.multilist.data

import com.example.base.multilist.MultiListItemData

class ItemOneData(
    override val type: Int,
    override val title: String,
    override val content: String,
    val index: Int
) : MultiListItemData(
    type, title,
    content
)
