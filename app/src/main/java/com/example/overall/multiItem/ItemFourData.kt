package com.example.overall.multiItem

import com.example.base.multilist.MultiListItemData

class ItemFourData(
    override val type: Int,
    override val title: String,
    override val content: String,
    val index: Int
) : MultiListItemData(
    type, title,
    content
)
