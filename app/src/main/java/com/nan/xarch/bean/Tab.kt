package com.nan.xarch.bean

import com.nan.xarch.base.BaseFragment
import com.nan.xarch.constant.TabId
import kotlin.reflect.KClass

data class Tab(
    @TabId
    val id: String,
    val title: String,
    val icon: Int,
    val fragmentClz: KClass<out BaseFragment<*>>
)