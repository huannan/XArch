package com.nan.xarch.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.nan.xarch.R
import com.nan.xarch.databinding.ViewPromptBinding
import com.nan.xarch.util.NetworkHelper

/**
 * 封装一个通用的空白页，用于显示:
 * 1. 无网络
 * 2. 空白页
 * 3. 网络异常重试
 * 4. 正在加载
 */
class PromptView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val viewBinding = ViewPromptBinding.inflate(LayoutInflater.from(context), this, true)

    fun showNoNetwork(@DrawableRes imageRes: Int = R.drawable.icon_no_network, message: String = resources.getString(R.string.page_state_no_network)) {
        viewBinding.run {
            ivError.setImageResource(imageRes)
            tvError.text = message
            btnAction.visibility = View.VISIBLE
            btnAction.text = resources.getString(R.string.action_set_network)
            btnAction.setOnClickListener {
                // 跳转到网络设置页面
                NetworkHelper.toNetworkSetting(context)
            }
            ivError.visibility = View.VISIBLE
            tvError.visibility = View.VISIBLE
            btnAction.visibility = View.VISIBLE
            viewLoading.visibility = View.GONE
            tvLoading.visibility = View.GONE
        }
        visibility = View.VISIBLE
    }

    fun showEmpty(@DrawableRes imageRes: Int = R.drawable.icon_empty, message: String = resources.getString(R.string.page_state_empty)) {
        viewBinding.run {
            ivError.setImageResource(imageRes)
            tvError.text = message
            btnAction.setOnClickListener(null)
            ivError.visibility = View.VISIBLE
            tvError.visibility = View.VISIBLE
            btnAction.visibility = View.GONE
            viewLoading.visibility = View.GONE
            tvLoading.visibility = View.GONE
        }
        visibility = View.VISIBLE
    }

    fun showNetworkError(retryOnClickListener: OnClickListener, @DrawableRes imageRes: Int = R.drawable.icon_network_error, message: String = resources.getString(R.string.page_state_network_error)) {
        viewBinding.run {
            ivError.setImageResource(imageRes)
            tvError.text = message
            btnAction.text = resources.getString(R.string.action_retry)
            btnAction.setOnClickListener(retryOnClickListener)
            ivError.visibility = View.VISIBLE
            tvError.visibility = View.VISIBLE
            btnAction.visibility = View.VISIBLE
            viewLoading.visibility = View.GONE
            tvLoading.visibility = View.GONE
        }
        visibility = View.VISIBLE
    }

    fun showLoading() {
        viewBinding.run {
            ivError.visibility = View.GONE
            tvError.visibility = View.GONE
            btnAction.visibility = View.GONE
            viewLoading.visibility = View.VISIBLE
            viewLoading.playAnimation()
            tvLoading.visibility = View.VISIBLE
        }
        visibility = View.VISIBLE
    }

    fun isShowing(): Boolean {
        return visibility == View.VISIBLE
    }

    fun hide() {
        visibility = View.GONE
    }
}