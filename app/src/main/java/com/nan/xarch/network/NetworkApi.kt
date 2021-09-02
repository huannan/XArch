package com.nan.xarch.network

import com.nan.xarch.network.base.BaseNetworkApi

/**
 * 网络请求具体实现
 */
object NetworkApi : BaseNetworkApi<INetworkService>("http://172.16.47.112:8080/TestServer/") {

    suspend fun requestHelloWorld(): Result<String> {
        return getResult {
            service.requestHelloWorld()
        }
    }
}