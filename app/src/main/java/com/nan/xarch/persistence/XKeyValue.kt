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

    fun from(@Key key: String): MMKV {
        return if (key.startsWith("account_")) {
            getAccountMMKV()
        } else {
            getDefaultMMKV()
        }
    }

    private fun getDefaultMMKV(): MMKV {
        return MMKV.mmkvWithID(ID_DEFAULT, MMKV.SINGLE_PROCESS_MODE)
    }

    private fun getAccountMMKV(): MMKV {
        return MMKV.mmkvWithID(ID_ACCOUNT, MMKV.SINGLE_PROCESS_MODE)
    }

    fun clearAccountMMKV() {
        getAccountMMKV().clearAll()
    }

    fun putBoolean(@Key key: String, value: Boolean) {
        from(key).encode(key, value)
    }

    @JvmOverloads
    fun getBoolean(@Key key: String, defaultValue: Boolean = false): Boolean {
        return from(key).decodeBool(key, defaultValue)
    }

    fun putString(@Key key: String, value: String) {
        from(key).encode(key, value)
    }

    @JvmOverloads
    fun getString(@Key key: String, defaultValue: String = ""): String {
        return from(key).decodeString(key, defaultValue)!!
    }

    fun putInt(@Key key: String, value: Int) {
        from(key).encode(key, value)
    }

    @JvmOverloads
    fun getInt(@Key key: String, defaultValue: Int = 0): Int {
        return from(key).decodeInt(key, defaultValue)
    }

    fun putFloat(@Key key: String, value: Float) {
        from(key).encode(key, value)
    }

    @JvmOverloads
    fun getFloat(@Key key: String, defaultValue: Float = 0F): Float {
        return from(key).decodeFloat(key, defaultValue)
    }

    fun putLong(@Key key: String, value: Long) {
        from(key).encode(key, value)
    }

    @JvmOverloads
    fun getLong(@Key key: String, defaultValue: Long = 0L): Long {
        return from(key).decodeLong(key, defaultValue)
    }

    fun putDouble(@Key key: String, value: Double) {
        from(key).encode(key, value)
    }

    @JvmOverloads
    fun getDouble(@Key key: String, defaultValue: Double = 0.0): Double {
        return from(key).decodeDouble(key, defaultValue)
    }

    fun putByteArray(@Key key: String, value: ByteArray) {
        from(key).encode(key, value)
    }

    @JvmOverloads
    fun getByteArray(@Key key: String, defaultValue: ByteArray = ByteArray(0)): ByteArray {
        return from(key).decodeBytes(key, defaultValue)!!
    }

    fun putStringSet(@Key key: String, value: Set<String>) {
        from(key).encode(key, value)
    }

    @JvmOverloads
    fun getStringSet(@Key key: String, defaultValue: Set<String> = mutableSetOf()): Set<String> {
        return from(key).decodeStringSet(key, defaultValue)!!
    }

    fun putParcelable(@Key key: String, value: Parcelable) {
        from(key).encode(key, value)
    }

    inline fun <reified T : Parcelable> getParcelable(@Key key: String): T? {
        return from(key).decodeParcelable(key, T::class.java)
    }
}