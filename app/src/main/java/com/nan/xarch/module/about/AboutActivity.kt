package com.nan.xarch.module.about

import android.os.Bundle
import com.nan.xarch.base.BaseActivity
import com.nan.xarch.constant.EventName
import com.nan.xarch.constant.PageName
import com.nan.xarch.databinding.ActivityAboutBinding
import com.nan.xarch.eventbus.XEventBus

class AboutActivity : BaseActivity<ActivityAboutBinding>(ActivityAboutBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        viewBinding.tvAbout.setOnClickListener {
            XEventBus.post(EventName.TEST, "来自关于页面的消息")
        }
    }

    @PageName
    override fun getPageName() = PageName.ABOUT
}