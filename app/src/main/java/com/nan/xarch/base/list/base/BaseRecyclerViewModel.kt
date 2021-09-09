package com.nan.xarch.base.list.base

import androidx.lifecycle.MutableLiveData
import com.nan.xarch.base.BaseViewModel
import com.nan.xarch.bean.LoadError
import com.nan.xarch.util.isNetworkConnect
import java.util.concurrent.atomic.AtomicInteger

abstract class BaseRecyclerViewModel : BaseViewModel() {

    /**
     * 首页/下拉刷新的数据
     */
    val firstViewDataLiveData = MutableLiveData<List<BaseViewData<*>>>()

    /**
     * 更多的数据
     */
    val moreViewDataLiveData = MutableLiveData<List<BaseViewData<*>>>()

    /**
     * 页码
     */
    private var currentPage = AtomicInteger(0)

    /**
     * 子类重写这个函数加载数据
     * 数据加载完成后通过postData提交数据
     * 数据加载完成后通过postError提交异常
     *
     * @param isLoadMore 当次是否为加载更多
     * @param isReLoad   当次是否为重新加载(此时page等参数不应该改变)
     * @param page       页码
     */
    abstract fun loadData(isLoadMore: Boolean, isReLoad: Boolean, page: Int)

    fun loadDataInternal(isLoadMore: Boolean, isReLoad: Boolean) {
        if (needNetwork() && !isNetworkConnect()) {
            postError(isLoadMore)
            return
        }
        if (!isLoadMore) {
            currentPage.set(0)
        } else if (!isReLoad) {
            currentPage.incrementAndGet()
        }
        loadData(isLoadMore, isReLoad, currentPage.get())
    }

    /**
     * 获取当前页码
     */
    fun getCurrentPage(): Int {
        return currentPage.get()
    }

    /**
     * 子类可以实现这个方法,返回加载数据是否需要网络
     */
    open fun needNetwork(): Boolean {
        return true
    }

    /**
     * 提交数据
     */
    protected fun postData(isLoadMore: Boolean, viewData: List<BaseViewData<*>>) {
        if (isLoadMore) {
            moreViewDataLiveData.postValue(viewData)
        } else {
            firstViewDataLiveData.postValue(viewData)
        }
    }

    /**
     * 提交加载异常
     */
    protected fun postError(isLoadMore: Boolean) {
        if (isLoadMore) {
            moreViewDataLiveData.postValue(LoadError)
        } else {
            firstViewDataLiveData.postValue(LoadError)
        }
    }
}