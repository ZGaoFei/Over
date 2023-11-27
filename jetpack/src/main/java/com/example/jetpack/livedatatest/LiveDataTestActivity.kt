package com.example.jetpack.livedatatest

import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.base.base.BaseActivity
import com.example.jetpack.R
import com.example.jetpack.databinding.ActivityLiveDataTestBinding

class LiveDataTestActivity : BaseActivity() {

    private lateinit var binding: ActivityLiveDataTestBinding
    private lateinit var viewModel: TestDataViewModel

    override fun bindingView() {
        super.bindingView()

        binding = ActivityLiveDataTestBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }

    override fun initData() {
        super.initData()

        viewModel = defaultViewModelProviderFactory.create(TestDataViewModel::class.java)

        dataObserve()
    }

    override fun initView() {
        super.initView()

        clickListener()

        addFragment()
    }

    // 测试livedata监听
    private fun dataObserve() {
        viewModel.string.observe(this, Observer {
            Log.e("zgf", "=======observe===String===== $it")
            binding.tvData.text = it
        })

        viewModel.i.observe(this, Observer {
            Log.e("zgf", "=======observe===int===== $it")
            binding.tvData.text = "$it"
        })

        viewModel.testData.observe(this, Observer {
            Log.e("zgf", "=======observe===int===== ${it.content} ==== ${it.title}")
            binding.tvData.text = "${it.title} == ${it.content}"
        })

        // 测试页面未激活状态也能收到回调
        viewModel.string.observeForever(Observer {
            Log.e("zgf", "=======observeForever===String===== $it")
            binding.tvData.text = it
        })
    }

    private fun clickListener() {
        binding.btSetValue.setOnClickListener {
            Log.e("zgf", "======setValue=======")
//            viewModel.string.value = "hello world"
//            viewModel.i.value = 0
            viewModel.testData.value = TestData("title", "content")

//            postValue()
//            delayPostValue()
        }
    }

    // 测试postValue和setValue的区别
    private fun postValue() {
//        viewModel.string.value = "hello world"
//        viewModel.string.postValue("hello")

        Thread {
//            viewModel.string.value = "hello world"

            viewModel.string.postValue("hello")
        }.start()
    }

    // 延迟设置，测试livedata在Activity为激活状态更新数据
    private fun delayPostValue() {
        Thread {
            Log.e("zgf", "=======start======")
            Thread.sleep(5000)
            Log.e("zgf", "=======end======")

            viewModel.string.postValue("hello")
        }.start()
    }

    fun getViewModel(): TestDataViewModel {
        return viewModel
    }

    // 添加左右fragment
    // 测试新增fragment会不会收到之前的数据
    private fun addFragment() {
        binding.btAddLeftFragment.setOnClickListener {
            val fragment = createFragment("left")
            supportFragmentManager.beginTransaction().add(R.id.fl_fragment_left, fragment).commit()
        }

        binding.btAddRightFragment.setOnClickListener {
            val fragment = createFragment("right")
            supportFragmentManager.beginTransaction().add(R.id.fl_fragment_right, fragment).commit()
        }
    }

    private fun createFragment(string: String): Fragment {
        return LiveDataTestFragment.newInstance(string)
    }
}