package com.nan.xarch.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nan.xarch.base.list.loadmore.LoadMoreAdapter

class GridItemDecoration(
    // 列数
    private val spanCount: Int,
    // 列间距
    private val itemGap: Int,
    // 到屏幕左右的边距
    private val padding: Int,
    // 行间距
    private val linePadding: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        // 不对上拉加载作处理
        val adapter = parent.adapter
        if (adapter is LoadMoreAdapter && adapter.isLoadMoreViewData(position)) {
            return
        }

        val column: Int = position % spanCount
        outRect.left = if (column == 0) padding else column * itemGap / spanCount
        outRect.right = if (column == spanCount - 1) padding else itemGap - (column + 1) * itemGap / spanCount
        outRect.bottom = linePadding

        if (position == 0 || position == 1) {
            outRect.top = linePadding
        }
    }
}