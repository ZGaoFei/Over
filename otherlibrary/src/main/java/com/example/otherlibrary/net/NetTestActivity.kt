package com.example.otherlibrary.net

import android.util.Log
import com.example.base.base.BaseActivity
import com.example.otherlibrary.databinding.ActivityNetTestBinding

class NetTestActivity : BaseActivity() {
    private lateinit var binding: ActivityNetTestBinding

    private lateinit var viewModel: UserViewModel

    override fun bindingView() {
        super.bindingView()

        binding = ActivityNetTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initData() {
        super.initData()

        viewModel = defaultViewModelProviderFactory.create(UserViewModel::class.java)

        viewModel.user.observe(this) {
            Log.e("zgf", "============== $it")
            binding.tvData.text = "${it.id}"
        }
    }

    override fun initView() {
        super.initView()

        binding.btGetData.setOnClickListener {
//            viewModel.getUser()
            viewModel.getUserBody()
        }
    }
}