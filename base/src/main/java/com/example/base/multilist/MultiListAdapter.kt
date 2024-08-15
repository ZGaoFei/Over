package com.example.base.multilist

import android.view.View
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
                val createView = clazz.getMethod("createView", ViewGroup::class.java)
                val invokeView = createView.invoke(null, parent)
                val factoryMethod = clazz.getMethod("create", View::class.java)
                return factoryMethod.invoke(null, invokeView) as MultiListViewHolder<MultiListItemData>
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
                val createView = clazz.getMethod("createView", ViewGroup::class.java)
                val invokeView = createView.invoke(null, parent)
                val constructor = clazz.getConstructor(View::class.java)
                return constructor.newInstance(invokeView) as MultiListViewHolder<MultiListItemData>
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