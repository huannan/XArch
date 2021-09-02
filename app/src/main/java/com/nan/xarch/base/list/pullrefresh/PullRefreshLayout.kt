package com.nan.xarch.base.list.pullrefresh

import `in`.srain.cube.views.ptr.PtrDefaultHandler
import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrUIHandler
import `in`.srain.cube.views.ptr.indicator.PtrIndicator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.nan.xarch.R
import com.nan.xarch.databinding.ViewRecyclerHeaderBinding

class PullRefreshLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : PtrFrameLayout(context, attrs, defStyleAttr), PtrUIHandler {

    private val viewBinding = ViewRecyclerHeaderBinding.inflate(LayoutInflater.from(context), this, false)
    private var onPullRefreshListener: OnPullRefreshListener? = null

    init {
        disableWhenHorizontalMove(true)
        isKeepHeaderWhenRefresh = true
        headerView = viewBinding.root
        addPtrUIHandler(this)
        setPtrHandler(object : PtrDefaultHandler() {
            override fun onRefreshBegin(layout: PtrFrameLayout) {
                onPullRefreshListener?.onRefreshBegin()
            }
        })
    }

    override fun onUIReset(layout: PtrFrameLayout) {
        viewBinding.tvRefreshState.setText(R.string.refresh_pulldowntorefresh)
    }

    override fun onUIRefreshPrepare(layout: PtrFrameLayout) {
        viewBinding.tvRefreshState.setText(R.string.refresh_pulldowntorefresh)
    }

    override fun onUIRefreshBegin(layout: PtrFrameLayout) {
        viewBinding.tvRefreshState.setText(R.string.refresh_refreshing)
    }

    override fun onUIRefreshComplete(layout: PtrFrameLayout) {
        viewBinding.tvRefreshState.setText(R.string.refresh_pulldowntorefresh)
    }

    override fun onUIPositionChange(layout: PtrFrameLayout, isUnderTouch: Boolean, status: Byte, ptrIndicator: PtrIndicator) {
        // 这里拿到动画完成度
        val percent = ptrIndicator.currentPercent
        if (percent >= 1.0F) {
            viewBinding.tvRefreshState.setText(R.string.refresh_releasetorefresh)
        } else {
            viewBinding.tvRefreshState.setText(R.string.refresh_pulldowntorefresh)
        }
    }

    fun setOnPullRefreshListener(listener: OnPullRefreshListener) {
        this.onPullRefreshListener = listener
    }

    interface OnPullRefreshListener {
        fun onRefreshBegin()
    }
}