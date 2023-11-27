package com.example.base.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.base.databinding.ItemLayoutBinding
import com.example.base.databinding.ItemLayoutBinding.*

class HomeListAdapter(private val list: MutableList<Item>): RecyclerView.Adapter<HomeListAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.itemView.setOnClickListener {
            holder.itemView.context.startActivity(Intent().let {
                it.action = list[position].action
                it.addCategory(Intent.CATEGORY_DEFAULT)
            })
        }
    }

    class HomeViewHolder(binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.tvTitle
    }
}