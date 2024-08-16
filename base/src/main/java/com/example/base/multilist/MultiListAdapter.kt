package com.example.base.multilist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.Class.forName

/**
 * 解决了跨组件布局的问题
 * todo: 目前无法解决Bean类问题，只能将Bean放在与列表容器所在的组件中
 */
abstract class MultiListAdapter(private val list: MutableList<MultiListItemData>) :
    RecyclerView.Adapter<MultiListViewHolder<MultiListItemData>>() {

    abstract fun getItemRes(type: Int): ItemRes

    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MultiListViewHolder<MultiListItemData> {
        val itemRes = getItemRes(viewType)

        return createViewHolderConstructor(parent, itemRes.viewHolderClazz)
    }

    // 工厂方法
    private fun createViewHolder(
        parent: ViewGroup,
        clazzName: String,
    ): MultiListViewHolder<MultiListItemData> {
        try {
            // 尝试使用已知的ViewHolder工厂方法创建实例
            val clazz = forName(clazzName)
            if (MultiListViewHolder::class.java.isAssignableFrom(clazz)) {
                val factoryMethod = clazz.getMethod("create", ViewGroup::class.java)
                return factoryMethod.invoke(null, parent) as MultiListViewHolder<MultiListItemData>
            } else {
                throw IllegalArgumentException("Class $clazzName is not a subclass of MultiListViewHolder")
            }
        } catch (e: Exception) {
            // 处理可能出现的异常
            throw RuntimeException("Failed to create ViewHolder instance", e)
        }
    }

    // 反射方式
    private fun createViewHolderConstructor(
        parent: ViewGroup,
        clazzName: String,
    ): MultiListViewHolder<MultiListItemData> {
        try {
            val clazz = forName(clazzName)
            if (MultiListViewHolder::class.java.isAssignableFrom(clazz)) {
                val constructor = clazz.getConstructor(ViewGroup::class.java)
                return constructor.newInstance(parent) as MultiListViewHolder<MultiListItemData>
            } else {
                throw IllegalArgumentException("Class $clazzName is not a subclass of MultiListViewHolder")
            }
        } catch (e: Exception) {
            // 处理可能出现的异常
            throw RuntimeException("Failed to create ViewHolder instance", e)
        }
    }

    override fun onBindViewHolder(holder: MultiListViewHolder<MultiListItemData>, position: Int) {
        holder.update(list[position])
    }

}