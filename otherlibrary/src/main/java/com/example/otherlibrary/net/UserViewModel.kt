package com.example.otherlibrary.net

import androidx.lifecycle.MutableLiveData
import com.example.base.net.normal.RetrofitHelper
import com.example.base.net.rxjava.RxJavaRetrofit
import com.example.base.net.RxJavaViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : RxJavaViewModel() {

    val user: MutableLiveData<User> = MutableLiveData()

    fun getUser() {
        RxJavaRetrofit
            .getRetrofit(getHost())
            .create(UserApi::class.java)
            .getUser("")
            .subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<User?> {

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }

                override fun onNext(t: User) {
                    user.value = t
                }

            })
    }

    fun getUserBody() {
        val body: RequestBody = RequestBody.create(MediaType.parse("application/octet-stream"), "")
        RxJavaRetrofit
            .getRetrofit(getHost())
            .create(UserApi::class.java)
            .getUser(body)
            .subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<User?> {

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }

                override fun onNext(t: User) {
                    user.value = t
                }

            })
    }

    fun getUserNormal() {
        RetrofitHelper
            .getRetrofit(getHost())
            .create(UserApi::class.java)
            .getUserNormal("")
            .enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    user.value = response.body()
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                }
            })
    }

    fun getUserBodyNormal() {
        val body: RequestBody = RequestBody.create(MediaType.parse("application/octet-stream"), "")

        RetrofitHelper
            .getRetrofit(getHost())
            .create(UserApi::class.java)
            .getUserNormal(body)
            .enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    user.value = response.body()
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                }

            })
    }

    // 只创建一次UserApi
    fun getUserTest() {
        val body: RequestBody = RequestBody.create(MediaType.parse("application/octet-stream"), "")

        UserApiClient
            .userApi
            .getUserNormal(body)
            .enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    user.value = response.body()
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                }

            })
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getUserSuspend() {
        GlobalScope.launch {
            UserApiClient
                .userApi
                .getUserSuspend("")
                .enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                    }

                })
        }
    }

    fun getUserBodySuspend() {

    }

}