package com.nan.xarch.module.mine

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nan.xarch.base.BaseFragment
import com.nan.xarch.constant.PageName
import com.nan.xarch.databinding.FragmentMineBinding
import com.nan.xarch.module.about.AboutActivity

/**
 * 我的
 */
class MineFragment : BaseFragment() {

    private val viewModel: MineViewModel by viewModels()
    private lateinit var viewBinding: FragmentMineBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = FragmentMineBinding.inflate(inflater, container, false)
        initView()
        return viewBinding.root
    }

    private fun initView() {
        viewBinding.tvMine.setOnClickListener {
            startActivity(Intent(activity, AboutActivity::class.java))
        }
    }

    @PageName
    override fun getPageName() = PageName.MINE

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        // 这里可以添加页面打点
    }
}