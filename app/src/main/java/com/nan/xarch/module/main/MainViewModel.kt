package com.nan.xarch.module.main

import com.nan.xarch.base.BaseViewModel
import com.nan.xarch.constant.PageName

class MainViewModel : BaseViewModel() {

    @PageName
    override fun getPageName() = PageName.HOME
}