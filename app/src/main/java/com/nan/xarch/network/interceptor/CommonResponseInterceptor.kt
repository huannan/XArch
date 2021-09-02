package com.nan.xarch.network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class CommonResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val startTime = System.nanoTime()
        val request = chain.request()
        val response = chain.proceed(request)
        return response
    }
}