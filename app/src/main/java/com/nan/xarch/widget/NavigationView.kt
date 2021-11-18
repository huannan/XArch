package com.nan.xarch.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.nan.xarch.R
import com.nan.xarch.databinding.ViewNavigationBinding
import com.nan.xarch.util.getActivity
import com.nan.xarch.util.toVisibility

/**
 * 顶部导航栏
 */
class NavigationView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs), View.OnClickListener {

    private var viewBinding: ViewNavigationBinding = ViewNavigationBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        viewBinding.run {
            ivBack.setOnClickListener(this@NavigationView)
        }

        // 读取配置
        val array = context.obtainStyledAttributes(attrs, R.styleable.NavigationView)
        val title = array.getString(R.styleable.NavigationView_nav_title) ?: ""
        val showBack = array.getBoolean(R.styleable.NavigationView_nav_show_back, true)
        array.recycle()

        setParameter(ParameterBuilder()
            .setTitle(title)
            .setShowBack(showBack))
    }

    override fun onClick(v: View?) {
        viewBinding.run {
            when (v?.id) {
                ivBack.id -> {
                    context.getActivity()?.finish()
                }
                else -> {
                }
            }
        }
    }

    fun setParameter(builder: ParameterBuilder) {
        val parameter = builder.build()
        viewBinding.run {
            ivBack.visibility = parameter.showBack.toVisibility()
            tvTitle.visibility = parameter.showTitle.toVisibility()
            tvTitle.text = parameter.title
        }
    }

    data class Parameter(
        var showBack: Boolean,
        var showTitle: Boolean,
        var title: String
    )

    class ParameterBuilder {

        private var showBack = false
        private var showTitle = true
        private var title = ""

        fun setShowBack(showBack: Boolean): ParameterBuilder {
            this.showBack = showBack
            return this
        }

        fun setShowTitle(showTitle: Boolean): ParameterBuilder {
            this.showTitle = showTitle
            return this
        }

        fun setTitle(title: String): ParameterBuilder {
            this.title = title
            return this
        }

        fun build(): Parameter {
            return Parameter(showBack, showTitle, title)
        }
    }
}