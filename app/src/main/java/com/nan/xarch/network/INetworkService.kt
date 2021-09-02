package com.nan.xarch.network

import com.nan.xarch.network.base.BaseResponse
import retrofit2.http.GET

interface INetworkService {

    @GET("helloworld")
    suspend fun requestHelloWorld(): BaseResponse<String>

}