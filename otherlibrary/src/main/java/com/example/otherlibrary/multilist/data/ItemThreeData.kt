package com.example.otherlibrary.multilist.data

import com.example.base.multilist.MultiListItemData

data class ItemThreeData(
    override val type: Int,
    override val title: String,
    override val content: String,
    val imageUrl: String
) : MultiListItemData(type, title, content)
