package com.nan.xarch.constant

import androidx.annotation.IntDef

@IntDef(PageState.NORMAL, PageState.LOADING, PageState.LOAD_ERROR, PageState.NO_NETWORK, PageState.EMPTY)
@Retention(AnnotationRetention.SOURCE)
annotation class PageState {
    companion object {
        const val NORMAL = 0
        const val LOADING = 1
        const val LOAD_ERROR = 2
        const val NO_NETWORK = 3
        const val EMPTY = 4
    }
}