package com.nan.xarch.constant

import androidx.annotation.StringDef

@StringDef(Key.XXX)
@Retention(AnnotationRetention.SOURCE)
annotation class Key {
    companion object {
        const val XXX = "xxx"
    }
}
