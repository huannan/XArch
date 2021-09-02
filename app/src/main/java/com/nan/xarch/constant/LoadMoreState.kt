package com.nan.xarch.constant

import androidx.annotation.IntDef

@IntDef(LoadMoreState.GONE, LoadMoreState.LOADING, LoadMoreState.ERROR, LoadMoreState.NO_NETWORK, LoadMoreState.NO_MORE)
@Retention(AnnotationRetention.SOURCE)
annotation class LoadMoreState {
    companion object {
        /**
         * 隐藏
         */
        const val GONE = 0

        /**
         * 正在加载
         */
        const val LOADING = 1

        /**
         * 加载异常
         */
        const val ERROR = 2

        /**
         * 无网络
         */
        const val NO_NETWORK = 3

        /**
         * 没有更多
         */
        const val NO_MORE = 4
    }
}