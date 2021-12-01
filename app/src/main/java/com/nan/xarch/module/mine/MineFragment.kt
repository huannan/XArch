package com.nan.xarch.module.mine

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.nan.xarch.base.BaseFragment
import com.nan.xarch.constant.PageName
import com.nan.xarch.databinding.FragmentMineBinding

/**
 * 我的
 */
class MineFragment : BaseFragment<FragmentMineBinding>(FragmentMineBinding::inflate) {

    private val viewModel: MineViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        viewBinding.itemHistory.setOnClickListener {

        }
        viewBinding.itemCollection.setOnClickListener {

        }
        viewBinding.itemSubscribe.setOnClickListener {

        }
        viewBinding.itemSetting.setOnClickListener {

        }
    }

    @PageName
    override fun getPageName() = PageName.MINE

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        // 这里可以添加页面打点
    }
}