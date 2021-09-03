package com.nan.xarch.module.smallvideo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nan.xarch.base.BaseViewModel
import com.nan.xarch.bean.VideoBean
import com.nan.xarch.constant.PageName
import com.nan.xarch.network.NetworkApi
import kotlinx.coroutines.launch

class SmallVideoViewModel : BaseViewModel() {

    val helloWorldLiveData = MutableLiveData<Result<VideoBean>>()

    fun requestVideoDetail(id: String) {
        viewModelScope.launch {
            val result = NetworkApi.requestVideoDetail(id)
            helloWorldLiveData.value = result
        }
    }

    @PageName
    override fun getPageName() = PageName.SMALL_VIDEO

}