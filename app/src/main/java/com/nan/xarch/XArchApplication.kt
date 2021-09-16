package com.nan.xarch

import android.app.ActivityManager
import android.app.Application
import android.os.Process
import com.nan.xarch.persistence.XKeyValue

/**
 * Application
 */
class XArchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initInMainProcess {
            instance = this
            XKeyValue.init(this)
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