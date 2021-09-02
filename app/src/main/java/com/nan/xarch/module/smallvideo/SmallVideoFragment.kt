package com.nan.xarch.module.smallvideo

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nan.xarch.R
import com.nan.xarch.base.BaseFragment
import com.nan.xarch.constant.PageName
import com.nan.xarch.databinding.FragmentSmallVideoBinding

/**
 * 小视频
 */
class SmallVideoFragment : BaseFragment() {

    private val viewModel: SmallVideoViewModel by viewModels()
    private lateinit var viewBinding: FragmentSmallVideoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = FragmentSmallVideoBinding.inflate(inflater, container, false)
        initView()
        return viewBinding.root
    }

    private fun initView() {
        viewModel.helloWorldLiveData.observe(viewLifecycleOwner) {
            val helloWorld = it.getOrNull()
            if (!TextUtils.isEmpty(helloWorld)) {
                viewBinding.tvHello.text = helloWorld
            } else {
                viewBinding.tvHello.text = resources.getString(R.string.page_state_network_error)
            }
        }
        viewModel.requestHelloWorld()
    }

    @PageName
    override fun getPageName() = PageName.SMALL_VIDEO

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        // 这里可以添加页面打点
    }
}