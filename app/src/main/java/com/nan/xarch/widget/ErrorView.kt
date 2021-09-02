package com.nan.xarch.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.nan.xarch.R
import com.nan.xarch.databinding.ViewErrorBinding
import com.nan.xarch.util.toNetworkSetting

/**
 * 封装一个通用的空白页，用于显示:
 * 1. 无网络
 * 2. 空白页
 * 3. 网络异常重试
 */
class ErrorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val viewBinding = ViewErrorBinding.inflate(LayoutInflater.from(context), this, true)

    fun showNoNetwork(@DrawableRes imageRes: Int = R.drawable.icon_no_network, message: String = resources.getString(R.string.page_state_no_network)) {
        viewBinding.run {
            ivEmpty.setImageResource(imageRes)
            tvEmpty.text = message
            btnAction.visibility = View.VISIBLE
            btnAction.text = resources.getString(R.string.action_set_network)
            btnAction.setOnClickListener {
                // 跳转到网络设置页面
                toNetworkSetting(context)
            }
        }
        visibility = View.VISIBLE
    }

    fun showEmpty(@DrawableRes imageRes: Int = R.drawable.icon_empty, message: String = resources.getString(R.string.page_state_empty)) {
        viewBinding.run {
            ivEmpty.setImageResource(imageRes)
            tvEmpty.text = message
            btnAction.visibility = View.GONE
            btnAction.setOnClickListener(null)
        }
        visibility = View.VISIBLE
    }

    fun showNetworkError(retryOnClickListener: OnClickListener, @DrawableRes imageRes: Int = R.drawable.icon_network_error, message: String = resources.getString(R.string.page_state_network_error)) {
        viewBinding.run {
            ivEmpty.setImageResource(imageRes)
            tvEmpty.text = message
            btnAction.visibility = View.VISIBLE
            btnAction.text = resources.getString(R.string.action_retry)
            btnAction.setOnClickListener(retryOnClickListener)
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