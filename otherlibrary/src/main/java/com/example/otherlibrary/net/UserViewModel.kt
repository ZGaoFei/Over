package com.example.otherlibrary.net

import androidx.lifecycle.MutableLiveData
import com.example.base.net.rxjava.RxJavaRetrofit
import com.example.base.net.rxjava.RxJavaViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody

class UserViewModel: RxJavaViewModel() {

    val user: MutableLiveData<User> = MutableLiveData()

    fun getUser() {
        RxJavaRetrofit
            .getRetrofit(getHost())
            .create(UserApi::class.java)
            .getUser("")
            .subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object: Observer<User?>{

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
        val body: RequestBody =
            RequestBody.create(MediaType.parse("application/octet-stream"), "")
        RxJavaRetrofit
            .getRetrofit(getHost())
            .create(UserApi::class.java)
            .getUser(body)
            .subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object: Observer<User?>{

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

}