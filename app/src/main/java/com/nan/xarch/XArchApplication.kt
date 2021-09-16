package com.nan.xarch

import android.app.Application
import com.nan.xarch.persistence.XKeyValue

/**
 * Application
 */
class XArchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        XKeyValue.init(this)
    }

    companion object {
        lateinit var instance: Application
    }
}