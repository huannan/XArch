package com.nan.xarch.module.acgn

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

class AcgnViewModel : BaseRecyclerViewModel() {

    override fun needNetwork(): Boolean {
        return false
    }

    override fun loadData(isLoadMore: Boolean, isReLoad: Boolean, page: Int) {
        viewModelScope.launch {
            // // 模拟插入一些数据
            // XDatabase.userDao().insert(
            //     listOf(
            //         User(1, "吴", "一", null),
            //         User(2, "黄", "二", null),
            //         User(3, "李", "三", null),
            //         User(4, "张", "四", null),
            //         User(5, "林", "五", null),
            //         User(6, "吴", "六", null),
            //         User(7, "黄", "七", null),
            //         User(8, "李", "八", null),
            //         User(9, "张", "九", null),
            //         User(10, "林", "十", null),
            //     )
            // )
            //
            // // 从数据库加载数据
            // val viewData = mutableListOf<Test3ViewData>()
            // XDatabase.userDao().getAll().forEach {
            //     viewData.add(Test3ViewData(it))
            // }

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
    override fun getPageName() = PageName.ACGN

}