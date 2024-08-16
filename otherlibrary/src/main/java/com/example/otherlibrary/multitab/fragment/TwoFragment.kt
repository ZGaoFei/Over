package com.example.otherlibrary.multitab.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base.base.BaseFragment
import com.example.otherlibrary.databinding.FragmentTwoBinding

class TwoFragment : BaseFragment() {
    private lateinit var binding: FragmentTwoBinding

    companion object {
        @JvmStatic
        fun newInstance() = TwoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTwoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initData() {
        super.initData()

    }

    override fun initView() {
        super.initView()


    }

}