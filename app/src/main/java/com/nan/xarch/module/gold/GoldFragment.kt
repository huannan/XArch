package com.nan.xarch.module.gold

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nan.xarch.base.BaseFragment
import com.nan.xarch.constant.PageName
import com.nan.xarch.databinding.FragmentGoldBinding
import com.nan.xarch.eventbus.XEventBus

/**
 * 领现金
 */
class GoldFragment : BaseFragment() {

    private val viewModel: GoldViewModel by viewModels()
    private lateinit var viewBinding: FragmentGoldBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = FragmentGoldBinding.inflate(inflater, container, false)
        initView()
        return viewBinding.root
    }

    private fun initView() {
        viewBinding.tvGold.setOnClickListener {
            XEventBus.post("event_refresh_list", "领现金页面通知首页刷新数据")
        }
    }

    @PageName
    override fun getPageName() = PageName.GOLD

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        // 这里可以添加页面打点
    }
}