package com.nan.xarch.base.list.pullrefresh

import `in`.srain.cube.views.ptr.PtrDefaultHandler
import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrUIHandler
import `in`.srain.cube.views.ptr.indicator.PtrIndicator
import android.content.Context
import android.util.AttributeSet
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import com.nan.xarch.R
import com.nan.xarch.databinding.ViewRecyclerHeaderBinding
import com.nan.xarch.util.Logger

class PullRefreshLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : PtrFrameLayout(context, attrs, defStyleAttr), PtrUIHandler {

    private val viewBinding = ViewRecyclerHeaderBinding.inflate(LayoutInflater.from(context), this, false)
    private var onPullRefreshListener: OnPullRefreshListener? = null

    companion object {
        private const val TAG = "PullRefreshLayout"
    }

    init {
        disableWhenHorizontalMove(true)
        isKeepHeaderWhenRefresh = true
        setRatioOfHeaderHeightToRefresh(1F)
        headerView = viewBinding.root
        addPtrUIHandler(this)
        setPtrHandler(
            object : PtrDefaultHandler() {
                override fun onRefreshBegin(layout: PtrFrameLayout) {
                    onPullRefreshListener?.onRefreshBegin()
                }
            })
    }

    override fun onUIReset(layout: PtrFrameLayout) {
        Logger.d(TAG, "onUIReset")
    }

    override fun onUIRefreshPrepare(layout: PtrFrameLayout) {
        Logger.d(TAG, "onUIRefreshPrepare")
        viewBinding.tvRefreshState.setText(R.string.refresh_pull_down_to_refresh)
    }

    override fun onUIRefreshBegin(layout: PtrFrameLayout) {
        Logger.d(TAG, "onUIRefreshBegin")
        viewBinding.tvRefreshState.setText(R.string.refresh_refreshing)
    }

    override fun onUIRefreshComplete(layout: PtrFrameLayout) {
        Logger.d(TAG, "onUIRefreshComplete")
        viewBinding.tvRefreshState.setText(R.string.refresh_refresh_complete)
    }

    override fun onUIPositionChange(layout: PtrFrameLayout, isUnderTouch: Boolean, status: Byte, ptrIndicator: PtrIndicator) {
        val offsetToRefresh: Int = layout.offsetToRefresh
        val currentPos = ptrIndicator.currentPosY
        val lastPos = ptrIndicator.lastPosY

        if (currentPos < offsetToRefresh && lastPos >= offsetToRefresh) {
            if (isUnderTouch && status == PTR_STATUS_PREPARE) {
                viewBinding.tvRefreshState.setText(R.string.refresh_pull_down_to_refresh)
            }
        } else if (currentPos > offsetToRefresh && lastPos <= offsetToRefresh) {
            if (isUnderTouch && status == PTR_STATUS_PREPARE) {
                viewBinding.tvRefreshState.setText(R.string.refresh_release_to_refresh)
                performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
            }
        }
    }

    fun setOnPullRefreshListener(listener: OnPullRefreshListener) {
        this.onPullRefreshListener = listener
    }

    interface OnPullRefreshListener {
        fun onRefreshBegin()
    }
}