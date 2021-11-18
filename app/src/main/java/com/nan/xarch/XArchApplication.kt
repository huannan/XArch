package com.nan.xarch

import android.app.ActivityManager
import android.app.Application
import android.os.Process
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.nan.xarch.persistence.XKeyValue
import com.nan.xarch.util.GlobalActivityManager

/**
 * Application
 */
class XArchApplication : Application() {

    private var applicationStartTime = 0L

    override fun onCreate() {
        super.onCreate()
        initInMainProcess {
            instance = this
            XKeyValue.init(this)
            GlobalActivityManager.init(this)
            // Application生命周期监听
            ProcessLifecycleOwner.get().lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
                fun onCreate() {
                }

                @OnLifecycleEvent(Lifecycle.Event.ON_START)
                fun onStart() {
                    // 应用启动埋点
                    // reportAppStart(0)
                    // 应用返回前台，记录时间戳
                    applicationStartTime = System.currentTimeMillis()
                }

                @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
                fun onStop() {
                    // 应用进入后台埋点
                    // reportAppStayTime(applicationStartTime, System.currentTimeMillis())
                }
            })
        }
    }

    /**
     * 主进程初始化
     */
    private fun initInMainProcess(block: () -> Unit) {
        val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val myPId = Process.myPid()
        val mainProcessName = packageName
        activityManager.runningAppProcesses.forEach {
            if (it.pid == myPId && it.processName == mainProcessName) {
                block()
            }
        }
    }

    companion object {
        lateinit var instance: Application
    }
}