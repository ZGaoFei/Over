package com.example.otherlibrary.multitab.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base.base.BaseFragment
import com.example.otherlibrary.databinding.FragmentOneBinding

class OneFragment : BaseFragment() {
    private lateinit var binding: FragmentOneBinding

    companion object {
        @JvmStatic
        fun newInstance() = OneFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initData() {
        super.initData()

    }

    override fun initView() {
        super.initView()


    }

}