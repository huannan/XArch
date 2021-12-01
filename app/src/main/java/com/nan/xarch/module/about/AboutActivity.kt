package com.nan.xarch.module.about

import android.os.Bundle
import androidx.activity.viewModels
import com.gyf.immersionbar.ktx.immersionBar
import com.nan.xarch.R
import com.nan.xarch.base.BaseActivity
import com.nan.xarch.constant.EventName
import com.nan.xarch.constant.PageName
import com.nan.xarch.databinding.ActivityAboutBinding
import com.nan.xarch.eventbus.XEventBus

class AboutActivity : BaseActivity<ActivityAboutBinding>(ActivityAboutBinding::inflate) {

    private val viewModel: AboutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        initSystemBar()

        viewBinding.tvAbout.setOnClickListener {
            XEventBus.post(EventName.TEST, "来自关于页面的消息")
        }
    }

    /**
     * 状态栏导航栏初始化
     */
    private fun initSystemBar() {
        immersionBar {
            transparentStatusBar()
            statusBarDarkFont(true)
            navigationBarColor(R.color.white)
            navigationBarDarkIcon(true)
        }
    }

    @PageName
    override fun getPageName() = PageName.ABOUT
}