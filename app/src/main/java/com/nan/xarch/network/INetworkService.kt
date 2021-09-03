package com.nan.xarch.network

import com.nan.xarch.bean.VideoBean
import com.nan.xarch.network.base.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface INetworkService {

    @GET("videodetail")
    suspend fun requestVideoDetail(@Query("id") id: String): BaseResponse<VideoBean>

}