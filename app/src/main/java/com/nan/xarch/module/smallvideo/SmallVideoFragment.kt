package com.nan.xarch.module.smallvideo

import android.os.Bundle
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
class SmallVideoFragment : BaseFragment<FragmentSmallVideoBinding>() {

    private val viewModel: SmallVideoViewModel by viewModels()
    override val inflater: (LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentSmallVideoBinding
        get() = FragmentSmallVideoBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        viewModel.helloWorldLiveData.observe(viewLifecycleOwner) {
            val video = it.getOrNull()
            if (null != video) {
                viewBinding.tvHello.text = "${video.id}-${video.title}"
            } else {
                viewBinding.tvHello.text = resources.getString(R.string.page_state_network_error)
            }
        }
        viewModel.requestVideoDetail("100")
    }

    @PageName
    override fun getPageName() = PageName.SMALL_VIDEO

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        // 这里可以添加页面打点
    }
}