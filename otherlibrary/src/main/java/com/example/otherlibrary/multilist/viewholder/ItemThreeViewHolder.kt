package com.example.otherlibrary.multilist.viewholder

import android.view.View
import android.widget.ImageView
import com.example.base.imageloader.ImageLoader
import com.example.base.multilist.MultiListItemData
import com.example.base.multilist.MultiListViewHolder
import com.example.otherlibrary.R
import com.example.otherlibrary.multilist.data.ItemThreeData

class ItemThreeViewHolder(itemView: View) : MultiListViewHolder(itemView) {

    private var imageView: ImageView
    init {
        imageView = itemView.findViewById(R.id.item_three_iv)
    }

    override fun update(data: MultiListItemData) {
        data as ItemThreeData
        ImageLoader.loadImage(imageView, data.imageUrl)
    }
}