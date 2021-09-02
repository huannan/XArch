package com.nan.xarch

import android.app.Application
import android.content.Context

/**
 * Application
 */
class XArchApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: Application
    }
}