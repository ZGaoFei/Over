package com.example.otherlibrary.multitab

import android.os.Bundle
import android.util.SparseArray
import androidx.core.util.forEach
import com.example.base.base.BaseActivity
import com.example.base.base.BaseFragment
import com.example.otherlibrary.databinding.ActivityMultiTabBinding

class MultiTabActivity : BaseActivity() {
    private lateinit var binding: ActivityMultiTabBinding

    private var map: SparseArray<String> = SparseArray()

    init {
        map[0] = "com.example.otherlibrary.multitab.fragment.OneFragment"
        map[1] = "com.example.otherlibrary.multitab.fragment.TwoFragment"
        map[2] = "com.example.otherlibrary.multitab.fragment.ThreeFragment"
        map[3] = "com.example.overall.multitab.FourFragment"
    }

    override fun bindingView() {
        super.bindingView()
        binding = ActivityMultiTabBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initView() {
        super.initView()

        val list: MutableList<BaseFragment> = mutableListOf()
        map.forEach { _, value -> list.add(createFragment(value))}

        binding.viewPager2.adapter = MultiTabAdapter(this, list)
    }

    private fun createFragment(
        clazzName: String
    ): BaseFragment {
        try {
            val clazz = Class.forName(clazzName)
            if (BaseFragment::class.java.isAssignableFrom(clazz)) {
                val newInstance = clazz.getMethod("newInstance")
                val fragment = newInstance.invoke(null)
                val baseFragment = fragment as BaseFragment
                // 赋值操作
                val bundle = Bundle()
                baseFragment.arguments = bundle
                return baseFragment
            } else {
                throw IllegalArgumentException("Class $clazzName is not a subclass of BaseFragment")
            }
        } catch (e: Exception) {
            throw RuntimeException("Failed to create BaseFragment instance", e)
        }
    }
}