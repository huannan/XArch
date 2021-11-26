package com.nan.xarch.module.discovery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.nan.xarch.base.BaseFragment
import com.nan.xarch.constant.PageName
import com.nan.xarch.databinding.FragmentDiscoveryBinding

/**
 * 发现
 */
class DiscoveryFragment : BaseFragment<FragmentDiscoveryBinding>(FragmentDiscoveryBinding::inflate) {

    private val viewModel: DiscoveryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
    }

    @PageName
    override fun getPageName() = PageName.GOLD

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        // 这里可以添加页面打点
    }
}