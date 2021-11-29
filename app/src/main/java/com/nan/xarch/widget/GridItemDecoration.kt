package com.nan.xarch.widget

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nan.xarch.R
import com.nan.xarch.base.list.base.BaseAdapter
import com.nan.xarch.base.list.loadmore.LoadMoreAdapter

class GridItemDecoration(
    private val context: Context,
    // 列数
    private val spanCount: Int,
    // 列间距
    private val itemGap: Int = context.resources.getDimensionPixelOffset(R.dimen.item_padding),
    // 到屏幕左右的边距
    private val padding: Int = context.resources.getDimensionPixelOffset(R.dimen.item_padding),
    // 行间距
    private val linePadding: Int = context.resources.getDimensionPixelOffset(R.dimen.item_padding),
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)

        // 必须使用GridLayoutManager
        val gridLayoutManager = parent.layoutManager as GridLayoutManager

        // 不对上拉加载作处理
        val adapter = parent.adapter as BaseAdapter
        if (adapter is LoadMoreAdapter && adapter.isLoadMoreViewData(position)) {
            return
        }

        if (isGridItem(adapter, position)) {
            // 网格样式处理
            val column: Int = gridLayoutManager.spanSizeLookup.getSpanIndex(position, spanCount)
            outRect.left = if (column == 0) padding else column * itemGap / spanCount
            outRect.right = if (column == spanCount - 1) padding else itemGap - (column + 1) * itemGap / spanCount
        } else {
            // 通栏样式处理
            outRect.left = padding
            outRect.right = padding
        }

        // 第一条处理
        if (position == 0 || position < spanCount && isGridItem(adapter, 0)) {
            outRect.top = linePadding
        }

        // 底部处理
        outRect.bottom = linePadding
    }

    private fun isGridItem(adapter: RecyclerView.Adapter<*>, position: Int): Boolean {
        return if (adapter is BaseAdapter) {
            adapter.getViewData(position)?.isGridViewData() ?: false
        } else {
            false
        }
    }
}