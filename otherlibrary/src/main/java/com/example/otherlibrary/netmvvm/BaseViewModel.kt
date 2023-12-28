package com.example.otherlibrary.netmvvm

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class BaseViewModel: ViewModel(), LifecycleObserver {

    //运行在UI线程的协程
    fun launchUI(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch {
        try {
            withTimeout(15 * 1000) {
                block()
            }
        } catch (e: Exception) {
            //此处接收到BaseRepository里的request抛出的异常
            //根据业务逻辑自行处理代码...

        }
    }
}

// todo: 明天补齐
// https://blog.csdn.net/u010368726/article/details/115225787?spm=1001.2101.3001.6650.3&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-3-115225787-blog-129952323.235%5Ev38%5Epc_relevant_sort_base1&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-3-115225787-blog-129952323.235%5Ev38%5Epc_relevant_sort_base1&utm_relevant_index=6