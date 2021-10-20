package com.nan.xarch.module.gold

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.nan.xarch.base.BaseFragment
import com.nan.xarch.constant.EventName
import com.nan.xarch.constant.PageName
import com.nan.xarch.databinding.FragmentGoldBinding
import com.nan.xarch.eventbus.XEventBus

/**
 * 领现金
 */
class GoldFragment : BaseFragment<FragmentGoldBinding>(FragmentGoldBinding::inflate) {

    private val viewModel: GoldViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        viewBinding.tvGold.setOnClickListener {
            XEventBus.post(EventName.REFRESH_HOME_LIST, "领现金页面通知首页刷新数据")
        }
    }

    @PageName
    override fun getPageName() = PageName.GOLD

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        // 这里可以添加页面打点
    }
}