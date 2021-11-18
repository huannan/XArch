package com.nan.xarch.util

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.nan.xarch.base.BaseActivity
import java.util.*

object GlobalActivityManager {

    private const val TAG = "GlobalActivityManager"
    private val activityStack = LinkedList<Activity>()

    /**
     * 初始化
     */
    fun init(application: Application) {
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                activityStack.add(activity)
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {
                activityStack.remove(activity)
                if (activityStack.isEmpty()) {
                    // ...
                }
            }
        })
    }

    fun getTopActivity(): Activity? {
        return if (activityStack.isNotEmpty()) activityStack[activityStack.size - 1] else null
    }

    fun getTopComponentActivity(): ComponentActivity? {
        return getTopActivity() as? ComponentActivity
    }

    fun getTopSelfActivity(): BaseActivity<*>? {
        for (i in activityStack.size - 1 downTo 0) {
            val activity = activityStack[i]
            if (activity is BaseActivity<*>) {
                return activity
            }
        }
        return null
    }

    fun getTopActivityLifecycleScope(): LifecycleCoroutineScope? {
        return getTopComponentActivity()?.lifecycleScope
    }

    fun getTopSelfActivityLifecycleScope(): LifecycleCoroutineScope? {
        return getTopSelfActivity()?.lifecycleScope
    }

    fun finishAll() {
        for (i in activityStack.size - 1 downTo 0) {
            activityStack[i].finish()
        }
    }
}