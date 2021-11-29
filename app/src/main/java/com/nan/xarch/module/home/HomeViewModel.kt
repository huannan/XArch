package com.nan.xarch.module.home

import androidx.lifecycle.viewModelScope
import com.nan.xarch.base.list.base.BaseRecyclerViewModel
import com.nan.xarch.base.list.base.BaseViewData
import com.nan.xarch.bean.BannerBean
import com.nan.xarch.bean.VideoBean
import com.nan.xarch.constant.PageName
import com.nan.xarch.constant.VideoType
import com.nan.xarch.item.BannerViewData
import com.nan.xarch.item.LargeVideoViewData
import com.nan.xarch.item.VideoViewData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : BaseRecyclerViewModel() {

    override fun loadData(isLoadMore: Boolean, isReLoad: Boolean, page: Int) {
        viewModelScope.launch {
            delay(1000L)
            val viewDataList = mutableListOf<BaseViewData<*>>()
            if (!isLoadMore) {
                viewDataList.add(BannerViewData(BannerBean(listOf("https://img1.baidu.com/it/u=2148838167,3055147248&fm=26&fmt=auto", "https://img1.baidu.com/it/u=2758621636,2239499009&fm=26&fmt=auto", "https://img2.baidu.com/it/u=669799662,2628491047&fm=26&fmt=auto"))))
                for (i in 0..10) {
                    if (i != 0 && i % 6 == 0) {
                        viewDataList.add(LargeVideoViewData(VideoBean("aaa", "我是标题", "xxx", "aaa", "up", 10000L, VideoType.LARGE)))
                    } else {
                        viewDataList.add(VideoViewData(VideoBean("aaa", "我是标题", "xxx", "aaa", "up", 10000L, VideoType.NORMAL)))
                    }
                }
                postData(isLoadMore, viewDataList)
            } else {
                for (i in 0..10) {
                    if (i != 0 && i % 6 == 0) {
                        viewDataList.add(LargeVideoViewData(VideoBean("aaa", "我是标题", "xxx", "aaa", "up", 10000L, VideoType.LARGE)))
                    } else {
                        viewDataList.add(VideoViewData(VideoBean("aaa", "我是标题", "xxx", "aaa", "up", 10000L, VideoType.NORMAL)))
                    }
                }
                postData(isLoadMore, viewDataList)
            }
        }
    }

    @PageName
    override fun getPageName() = PageName.HOME
}