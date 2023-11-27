package com.example.jetpack.livedatatest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.base.base.BaseFragment
import com.example.jetpack.databinding.FragmentLiveDataTestBinding

private const val ARG_PARAM = "param"

class LiveDataTestFragment : BaseFragment() {

    private lateinit var binding: FragmentLiveDataTestBinding

    private var param: String? = null

    companion object {
        @JvmStatic
        fun newInstance(string: String) = LiveDataTestFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM, string)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param = it.getString(ARG_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLiveDataTestBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun initData() {
        super.initData()

    }

    override fun initView() {
        super.initView()

        binding.tvFragment.text = param

        (activity as LiveDataTestActivity).getViewModel().testData.observe(this, Observer {
            Log.e("zgf", "========fragment=========== $it")
            binding.tvFragment.text = "${it.title} == ${it.content}"
        })
    }
}