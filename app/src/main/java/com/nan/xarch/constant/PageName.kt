package com.nan.xarch.constant

import androidx.annotation.StringDef

@StringDef(PageName.MAIN, PageName.HOME, PageName.ACGN, PageName.SMALL_VIDEO, PageName.GOLD, PageName.MINE)
@Retention(AnnotationRetention.SOURCE)
annotation class PageName {
    companion object {
        const val MAIN = "main"
        const val HOME = "home"
        const val ACGN = "acgn"
        const val SMALL_VIDEO = "small_video"
        const val GOLD = "gold"
        const val MINE = "mine"
    }
}