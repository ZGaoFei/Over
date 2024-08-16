package com.example.overall.multitab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base.base.BaseFragment
import com.example.overall.databinding.FragmentFourBinding

class FourFragment : BaseFragment() {
    private lateinit var binding: FragmentFourBinding

    companion object {
        @JvmStatic
        fun newInstance() = FourFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFourBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initData() {
        super.initData()

    }

    override fun initView() {
        super.initView()


    }

}