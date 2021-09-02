package com.nan.xarch.base.list.loadmore

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nan.xarch.base.list.base.BaseAdapter
import com.nan.xarch.base.list.base.BaseViewData
import com.nan.xarch.constant.LoadMoreState
import com.nan.xarch.item.LoadMoreViewData

class LoadMoreAdapter : BaseAdapter() {

    private val loadMoreViewData = LoadMoreViewData(LoadMoreState.LOADING)

    /**
     * 重写setViewData，添加加载更多条目
     */
    override fun setViewData(viewData: List<BaseViewData<*>>) {
        val mutableViewData = viewData.toMutableList()
        mutableViewData.add(loadMoreViewData)
        super.setViewData(mutableViewData)
    }

    override fun addViewData(viewData: BaseViewData<*>) {
        val position = itemCount - 1
        items.add(position, viewData)
        notifyItemInserted(position)
    }

    override fun addViewData(viewData: List<BaseViewData<*>>) {
        val position = itemCount - 1
        items.addAll(position, viewData)
        notifyItemRangeInserted(position, viewData.size)
    }

    override fun removeViewData(position: Int): BaseViewData<*>? {
        var removedViewData: BaseViewData<*>? = null
        if (position in 0 until itemCount - 1) {
            removedViewData = items.removeAt(position)
            notifyItemRemoved(position)
        }
        return removedViewData
    }

    override fun removeViewData(viewData: BaseViewData<*>): BaseViewData<*>? {
        val position = items.indexOf(viewData)
        return removeViewData(position)
    }

    override fun clearViewData() {
        super.clearViewData()
    }

    fun isLoadMoreViewData(position: Int): Boolean {
        return position == itemCount - 1 && items[position] is LoadMoreViewData
    }

    fun setLoadMoreState(@LoadMoreState loadMoreState: Int) {
        val position = itemCount - 1
        if (isLoadMoreViewData(position)) {
            loadMoreViewData.value = loadMoreState
            notifyItemChanged(position)
        }
    }

    @LoadMoreState
    fun getLoadMoreState(): Int {
        return loadMoreViewData.value
    }

    /**
     * 如果是GridLayoutManager，加载更多应该展示成通栏样式
     */
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val lm = recyclerView.layoutManager
        if (lm is GridLayoutManager) {
            val oldSpanSizeLookup = lm.spanSizeLookup
            lm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val viewData = getViewData(position)
                    return if (viewData is LoadMoreViewData) {
                        lm.spanCount
                    } else {
                        oldSpanSizeLookup.getSpanSize(position)
                    }
                }
            }
        }
    }
}