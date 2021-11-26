package com.nan.xarch.util

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.nan.xarch.base.BaseActivity
import com.nan.xarch.module.main.MainActivity
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
                // 如果最后一个退出的不是首页，那么打开一个新的首页，适用于导流、PUSH打开等场景
                if (activityStack.size == 1 && activity.isFinishing && activity !is MainActivity) {
                    val intent = Intent(activity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    activity.startActivity(intent)
                }
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

    fun getTopAppCompatActivity(): AppCompatActivity? {
        return getTopActivity() as? AppCompatActivity
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
        return getTopAppCompatActivity()?.lifecycleScope
    }

    fun finishAll() {
        for (i in activityStack.size - 1 downTo 0) {
            activityStack[i].finish()
        }
    }
}