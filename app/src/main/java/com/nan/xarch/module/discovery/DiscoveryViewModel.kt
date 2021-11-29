package com.nan.xarch.module.discovery

import androidx.lifecycle.viewModelScope
import com.nan.xarch.R
import com.nan.xarch.base.list.base.BaseRecyclerViewModel
import com.nan.xarch.base.list.base.BaseViewData
import com.nan.xarch.bean.CatagoryBean
import com.nan.xarch.bean.GoodsBean
import com.nan.xarch.constant.PageName
import com.nan.xarch.item.CatagoryListViewData
import com.nan.xarch.item.CatagoryViewData
import com.nan.xarch.item.GoodsViewData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DiscoveryViewModel : BaseRecyclerViewModel() {

    override fun loadData(isLoadMore: Boolean, isReLoad: Boolean, page: Int) {
        viewModelScope.launch {
            delay(1000L)
            val viewDataList = mutableListOf<BaseViewData<*>>()
            if (!isLoadMore) {
                val categoryList = mutableListOf<CatagoryViewData>()
                categoryList.add(CatagoryViewData(CatagoryBean(R.drawable.icon_girl, "萌妹")))
                categoryList.add(CatagoryViewData(CatagoryBean(R.drawable.icon_cat, "撸猫")))
                categoryList.add(CatagoryViewData(CatagoryBean(R.drawable.icon_bodybuilding, "健身")))
                categoryList.add(CatagoryViewData(CatagoryBean(R.drawable.icon_movie, "电影")))
                categoryList.add(CatagoryViewData(CatagoryBean(R.drawable.icon_music, "音乐")))
                categoryList.add(CatagoryViewData(CatagoryBean(R.drawable.icon_game, "游戏")))
                categoryList.add(CatagoryViewData(CatagoryBean(R.drawable.icon_photography, "摄影")))
                categoryList.add(CatagoryViewData(CatagoryBean(R.drawable.icon_learn, "学习")))
                viewDataList.add(CatagoryListViewData(categoryList))

                viewDataList.add(GoodsViewData(GoodsBean("", "", 100, 50000L)))
                viewDataList.add(GoodsViewData(GoodsBean("", "", 100, 50000L)))
                viewDataList.add(GoodsViewData(GoodsBean("", "", 100, 50000L)))
                viewDataList.add(GoodsViewData(GoodsBean("", "", 100, 50000L)))
                viewDataList.add(GoodsViewData(GoodsBean("", "", 100, 50000L)))
                viewDataList.add(GoodsViewData(GoodsBean("", "", 100, 50000L)))
                viewDataList.add(GoodsViewData(GoodsBean("", "", 100, 50000L)))
                viewDataList.add(GoodsViewData(GoodsBean("", "", 100, 50000L)))

                postData(isLoadMore, viewDataList)
            } else {
                viewDataList.add(GoodsViewData(GoodsBean("", "", 100, 50000L)))
                viewDataList.add(GoodsViewData(GoodsBean("", "", 100, 50000L)))
                viewDataList.add(GoodsViewData(GoodsBean("", "", 100, 50000L)))
                viewDataList.add(GoodsViewData(GoodsBean("", "", 100, 50000L)))
                viewDataList.add(GoodsViewData(GoodsBean("", "", 100, 50000L)))
                viewDataList.add(GoodsViewData(GoodsBean("", "", 100, 50000L)))
                viewDataList.add(GoodsViewData(GoodsBean("", "", 100, 50000L)))
                viewDataList.add(GoodsViewData(GoodsBean("", "", 100, 50000L)))
                postData(isLoadMore, viewDataList)
            }
        }
    }

    @PageName
    override fun getPageName() = PageName.DISCOVERY
}