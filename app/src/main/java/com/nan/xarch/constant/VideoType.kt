package com.nan.xarch.constant

import androidx.annotation.IntDef

@IntDef(
    VideoType.NORMAL,
    VideoType.LARGE,
)
@Retention(AnnotationRetention.SOURCE)
annotation class VideoType {
    companion object {
        const val NORMAL = 1
        const val LARGE = 2
    }
}