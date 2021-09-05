package com.nan.xarch.constant

import androidx.annotation.StringDef

@StringDef(EventName.REFRESH_HOME_LIST)
@Retention(AnnotationRetention.SOURCE)
annotation class EventName {
    companion object {
        const val REFRESH_HOME_LIST = "refresh_home_list"
    }
}
