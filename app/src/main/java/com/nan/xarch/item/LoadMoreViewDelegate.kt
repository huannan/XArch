package com.nan.xarch.item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.nan.xarch.R
import com.nan.xarch.base.list.base.BaseItemViewDelegate
import com.nan.xarch.constant.LoadMoreState
import com.nan.xarch.databinding.ViewRecyclerFooterBinding

class LoadMoreViewDelegate : BaseItemViewDelegate<LoadMoreViewData, LoadMoreViewDelegate.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, context: Context, parent: ViewGroup): ViewHolder {
        return ViewHolder(ViewRecyclerFooterBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: LoadMoreViewData) {
        super.onBindViewHolder(holder, item)
        holder.viewBinding.run {
            when (item.value) {
                LoadMoreState.GONE -> {
                    footerRoot.visibility = View.GONE
                }
                LoadMoreState.LOADING -> {
                    footerRoot.visibility = View.VISIBLE
                    tvLoadMoreState.setText(R.string.loadmore_loading)
                }
                LoadMoreState.ERROR -> {
                    footerRoot.visibility = View.VISIBLE
                    tvLoadMoreState.setText(R.string.loadmore_network_error)
                }
                LoadMoreState.NO_NETWORK -> {
                    footerRoot.visibility = View.VISIBLE
                    tvLoadMoreState.setText(R.string.loadmore_no_network)
                }
                LoadMoreState.NO_MORE -> {
                    footerRoot.visibility = View.VISIBLE
                    tvLoadMoreState.setText(R.string.loadmore_no_more)
                }
            }
        }
    }

    /**
     * 如果是StaggeredGridLayoutManager，加载更多应该展示成通栏样式
     */
    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        val lp = holder.itemView.layoutParams
        if (lp is StaggeredGridLayoutManager.LayoutParams) {
            lp.isFullSpan = true
        }
    }

    class ViewHolder(val viewBinding: ViewRecyclerFooterBinding) : RecyclerView.ViewHolder(viewBinding.root) {

    }
}