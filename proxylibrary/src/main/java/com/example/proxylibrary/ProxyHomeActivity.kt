package com.example.proxylibrary

import android.content.Intent
import com.example.base.base.BaseActivity
import com.example.proxylibrary.databinding.ActivityProxyHomeBinding
import com.example.proxylibrary.proxy.ProxyUtil.*
import com.example.proxylibrary.proxy.TestApi
import com.example.proxylibrary.proxy.TestApiImpl

class ProxyHomeActivity : BaseActivity() {

    private lateinit var binding: ActivityProxyHomeBinding

    override fun bindingView() {
        super.bindingView()

        binding = ActivityProxyHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initData() {
        super.initData()


    }

    override fun initView() {
        super.initView()

        binding.btProxyCreate.setOnClickListener {
//            createTestApi()
            createTestApi2()
        }
        binding.btProxyCreate1.setOnClickListener {
            createTestApiImpl()
        }

        binding.btAnno.setOnClickListener {
            startActivity(Intent(this@ProxyHomeActivity, AnnotationTestActivity::class.java))
        }

        binding.btBindViewTest.setOnClickListener{
            startActivity(Intent(this@ProxyHomeActivity, BindViewTestActivity::class.java))
        }

        binding.btFactory.setOnClickListener{

        }
    }

    // 动态代理生成对象
    private fun createTestApi() {
        val testApi: TestApi = TestApiImpl()
        val instance: TestApi = create(testApi) as TestApi
        instance.test0()
        instance.test1(1)
        instance.test2(2)
    }

    // 动态代理生成对象
    private fun createTestApiImpl() {
        val testApiImpl = create1(TestApi::class.java, TestApiImpl()) as TestApi
        testApiImpl.test0()
        testApiImpl.test1(1)
        val test2 = testApiImpl.test2(2)
        binding.tvProxyShow1.text = test2
    }

    // 动态代理生成对象
    private fun createTestApi2() {
        val instance: TestApi = create2(TestApi::class.java)
        instance.test0()
        instance.test1(1)
        instance.test2(2)
    }

}