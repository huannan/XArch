package com.nan.xarch.module.home

import android.text.format.DateFormat
import androidx.lifecycle.viewModelScope
import com.nan.xarch.base.list.base.BaseRecyclerViewModel
import com.nan.xarch.base.list.base.BaseViewData
import com.nan.xarch.constant.PageName
import com.nan.xarch.item.Test1ViewData
import com.nan.xarch.item.Test2ViewData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : BaseRecyclerViewModel() {

    override fun loadData(isLoadMore: Boolean, isReLoad: Boolean, page: Int) {
        viewModelScope.launch {
            // 模拟网络数据加载
            delay(1000L)

            val time = DateFormat.format("MM-dd HH:mm:ss", System.currentTimeMillis())

            val viewDataList: List<BaseViewData<*>>
            if (!isLoadMore) {
                viewDataList = listOf<BaseViewData<*>>(
                    Test1ViewData("a-$time"),
                    Test2ViewData("b-$time"),
                    Test1ViewData("c-$time"),
                    Test2ViewData("d-$time"),
                    Test1ViewData("e-$time"),
                    Test2ViewData("f-$time"),
                    Test1ViewData("g-$time"),
                    Test2ViewData("h-$time"),
                )
            } else {
                // 在第5页模拟网络异常
                if (page == 5) {
                    postError(isLoadMore)
                    return@launch
                }
                viewDataList = listOf<BaseViewData<*>>(
                    Test1ViewData("a-$time"),
                    Test2ViewData("b-$time"),
                    Test1ViewData("c-$time"),
                    Test2ViewData("d-$time"),
                    Test1ViewData("e-$time"),
                    Test2ViewData("f-$time"),
                    Test1ViewData("g-$time"),
                    Test2ViewData("h-$time"),
                )
            }
            postData(isLoadMore, viewDataList)
            // postError(isLoadMore)
        }
    }

    @PageName
    override fun getPageName() = PageName.HOME
}