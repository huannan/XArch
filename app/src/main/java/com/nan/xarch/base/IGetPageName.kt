package com.nan.xarch.base

import com.nan.xarch.constant.PageName

/**
 * 获取页面名称通用接口
 */
interface IGetPageName {

    @PageName
    fun getPageName(): String

}