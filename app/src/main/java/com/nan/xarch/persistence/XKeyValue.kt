package com.nan.xarch.persistence

import android.app.Application
import android.os.Parcelable
import com.nan.xarch.constant.Key
import com.tencent.mmkv.MMKV

/**
 * 本类为MMKV的封装类，防止代码入侵
 */
object XKeyValue {

    fun init(application: Application) {
        MMKV.initialize(application)
    }

    fun putBoolean(@Key key: String, value: Boolean) {
        MMKV.defaultMMKV().encode(key, value)
    }

    fun getBoolean(@Key key: String, defaultValue: Boolean = false): Boolean {
        return MMKV.defaultMMKV().decodeBool(key, defaultValue)
    }

    fun putString(@Key key: String, value: String) {
        MMKV.defaultMMKV().encode(key, value)
    }

    fun getString(@Key key: String, defaultValue: String = ""): String {
        return MMKV.defaultMMKV().decodeString(key, defaultValue)!!
    }

    fun putInt(@Key key: String, value: Int) {
        MMKV.defaultMMKV().encode(key, value)
    }

    fun getInt(@Key key: String, defaultValue: Int = 0): Int {
        return MMKV.defaultMMKV().decodeInt(key, defaultValue)
    }

    fun putFloat(@Key key: String, value: Float) {
        MMKV.defaultMMKV().encode(key, value)
    }

    fun getFloat(@Key key: String, defaultValue: Float = 0F): Float {
        return MMKV.defaultMMKV().decodeFloat(key, defaultValue)
    }

    fun putLong(@Key key: String, value: Long) {
        MMKV.defaultMMKV().encode(key, value)
    }

    fun getLong(@Key key: String, defaultValue: Long = 0L): Long {
        return MMKV.defaultMMKV().decodeLong(key, defaultValue)
    }

    fun putDouble(@Key key: String, value: Double) {
        MMKV.defaultMMKV().encode(key, value)
    }

    fun getDouble(@Key key: String, defaultValue: Double = 0.0): Double {
        return MMKV.defaultMMKV().decodeDouble(key, defaultValue)
    }

    fun putByteArray(@Key key: String, value: ByteArray) {
        MMKV.defaultMMKV().encode(key, value)
    }

    fun getByteArray(@Key key: String, defaultValue: ByteArray = ByteArray(0)): ByteArray {
        return MMKV.defaultMMKV().decodeBytes(key, defaultValue)!!
    }

    fun putStringSet(@Key key: String, value: Set<String>) {
        MMKV.defaultMMKV().encode(key, value)
    }

    fun getStringSet(@Key key: String, defaultValue: Set<String> = mutableSetOf()): Set<String> {
        return MMKV.defaultMMKV().decodeStringSet(key, defaultValue)!!
    }

    fun putParcelable(@Key key: String, value: Parcelable) {
        MMKV.defaultMMKV().encode(key, value)
    }

    inline fun <reified T : Parcelable> getParcelable(@Key key: String): T? {
        return MMKV.defaultMMKV().decodeParcelable(key, T::class.java)
    }
}