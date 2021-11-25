package com.nan.xarch.persistence

import android.app.Application
import android.os.Parcelable
import com.nan.xarch.constant.Key
import com.tencent.mmkv.MMKV

/**
 * 本类为MMKV的封装类，防止代码入侵
 */
object XKeyValue {

    private const val ID_DEFAULT = "id_default"
    private const val ID_ACCOUNT = "id_account"

    fun init(application: Application) {
        MMKV.initialize(application)
    }

    fun getDefaultMMKV(): MMKV {
        return MMKV.mmkvWithID(ID_DEFAULT, MMKV.SINGLE_PROCESS_MODE)
    }

    fun getAccountMMKV(): MMKV {
        return MMKV.mmkvWithID(ID_ACCOUNT, MMKV.SINGLE_PROCESS_MODE)
    }

    fun clear() {
        val defaultMMKV = getDefaultMMKV()
        defaultMMKV.clearMemoryCache()
        defaultMMKV.clearAll()
    }

    fun putBoolean(@Key key: String, value: Boolean) {
        putBoolean(getDefaultMMKV(), key, value)
    }

    fun putBoolean(mmkv: MMKV, @Key key: String, value: Boolean) {
        mmkv.encode(key, value)
    }

    @JvmOverloads
    fun getBoolean(@Key key: String, defaultValue: Boolean = false): Boolean {
        return getBoolean(getDefaultMMKV(), key, defaultValue)
    }

    @JvmOverloads
    fun getBoolean(mmkv: MMKV, @Key key: String, defaultValue: Boolean = false): Boolean {
        return mmkv.decodeBool(key, defaultValue)
    }

    fun putString(@Key key: String, value: String) {
        putString(getDefaultMMKV(), key, value)
    }

    fun putString(mmkv: MMKV, @Key key: String, value: String) {
        mmkv.encode(key, value)
    }

    @JvmOverloads
    fun getString(@Key key: String, defaultValue: String = ""): String {
        return getString(getDefaultMMKV(), key, defaultValue)
    }

    @JvmOverloads
    fun getString(mmkv: MMKV, @Key key: String, defaultValue: String = ""): String {
        return mmkv.decodeString(key, defaultValue)!!
    }

    fun putInt(@Key key: String, value: Int) {
        putInt(getDefaultMMKV(), key, value)
    }

    fun putInt(mmkv: MMKV, @Key key: String, value: Int) {
        mmkv.encode(key, value)
    }

    @JvmOverloads
    fun getInt(@Key key: String, defaultValue: Int = 0): Int {
        return getInt(getDefaultMMKV(), key, defaultValue)
    }

    @JvmOverloads
    fun getInt(mmkv: MMKV, @Key key: String, defaultValue: Int = 0): Int {
        return mmkv.decodeInt(key, defaultValue)
    }

    fun putFloat(@Key key: String, value: Float) {
        putFloat(getDefaultMMKV(), key, value)
    }

    fun putFloat(mmkv: MMKV, @Key key: String, value: Float) {
        mmkv.encode(key, value)
    }

    @JvmOverloads
    fun getFloat(@Key key: String, defaultValue: Float = 0F): Float {
        return getFloat(getDefaultMMKV(), key, defaultValue)
    }

    @JvmOverloads
    fun getFloat(mmkv: MMKV, @Key key: String, defaultValue: Float = 0F): Float {
        return mmkv.decodeFloat(key, defaultValue)
    }

    fun putLong(@Key key: String, value: Long) {
        putLong(getDefaultMMKV(), key, value)
    }

    fun putLong(mmkv: MMKV, @Key key: String, value: Long) {
        mmkv.encode(key, value)
    }

    @JvmOverloads
    fun getLong(@Key key: String, defaultValue: Long = 0L): Long {
        return getLong(getDefaultMMKV(), key, defaultValue)
    }

    @JvmOverloads
    fun getLong(mmkv: MMKV, @Key key: String, defaultValue: Long = 0L): Long {
        return mmkv.decodeLong(key, defaultValue)
    }

    fun putDouble(@Key key: String, value: Double) {
        putDouble(getDefaultMMKV(), key, value)
    }

    fun putDouble(mmkv: MMKV, @Key key: String, value: Double) {
        mmkv.encode(key, value)
    }

    @JvmOverloads
    fun getDouble(@Key key: String, defaultValue: Double = 0.0): Double {
        return getDouble(getDefaultMMKV(), key, defaultValue)
    }

    @JvmOverloads
    fun getDouble(mmkv: MMKV, @Key key: String, defaultValue: Double = 0.0): Double {
        return mmkv.decodeDouble(key, defaultValue)
    }

    fun putByteArray(@Key key: String, value: ByteArray) {
        putByteArray(getDefaultMMKV(), key, value)
    }

    fun putByteArray(mmkv: MMKV, @Key key: String, value: ByteArray) {
        mmkv.encode(key, value)
    }

    @JvmOverloads
    fun getByteArray(@Key key: String, defaultValue: ByteArray = ByteArray(0)): ByteArray {
        return getByteArray(getDefaultMMKV(), key, defaultValue)
    }

    @JvmOverloads
    fun getByteArray(mmkv: MMKV, @Key key: String, defaultValue: ByteArray = ByteArray(0)): ByteArray {
        return mmkv.decodeBytes(key, defaultValue)!!
    }

    fun putStringSet(@Key key: String, value: Set<String>) {
        putStringSet(getDefaultMMKV(), key, value)
    }

    fun putStringSet(mmkv: MMKV, @Key key: String, value: Set<String>) {
        mmkv.encode(key, value)
    }

    @JvmOverloads
    fun getStringSet(@Key key: String, defaultValue: Set<String> = mutableSetOf()): Set<String> {
        return getStringSet(getDefaultMMKV(), key, defaultValue)
    }

    @JvmOverloads
    fun getStringSet(mmkv: MMKV, @Key key: String, defaultValue: Set<String> = mutableSetOf()): Set<String> {
        return mmkv.decodeStringSet(key, defaultValue)!!
    }

    fun putParcelable(@Key key: String, value: Parcelable) {
        putParcelable(getDefaultMMKV(), key, value)
    }

    fun putParcelable(mmkv: MMKV, @Key key: String, value: Parcelable) {
        mmkv.encode(key, value)
    }

    inline fun <reified T : Parcelable> getParcelable(@Key key: String): T? {
        return getParcelable(getDefaultMMKV(), key)
    }

    inline fun <reified T : Parcelable> getParcelable(mmkv: MMKV, @Key key: String): T? {
        return mmkv.decodeParcelable(key, T::class.java)
    }
}