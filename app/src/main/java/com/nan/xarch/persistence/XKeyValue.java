package com.nan.xarch.persistence;

import android.content.Context;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.nan.xarch.constant.MMKVKey;
import com.tencent.mmkv.MMKV;

import java.util.Map;
import java.util.Set;

/**
 * 本类为MMKV的封装类，防止代码入侵
 */
public final class XKeyValue {

    public static final String TAG = "XMMKV";
    private static final String ID_DEFAULT = "id_default";

    private XKeyValue() {

    }

    public static void init(Context context) {
        MMKV.initialize(context);
    }

    public static MMKV getDefaultMMKV() {
        return MMKV.mmkvWithID(ID_DEFAULT, MMKV.SINGLE_PROCESS_MODE);
    }

    public static <T> void put(@MMKVKey @NonNull String key, @NonNull T value) {
        put(getDefaultMMKV(), key, value);
    }

    public static <T> void put(MMKV mmkv, @MMKVKey @NonNull String key, @NonNull T value) {
        if (value instanceof String || value instanceof Integer || value instanceof Boolean ||
                value instanceof Float || value instanceof Long || value instanceof Double) {
            mmkv.encode(key, String.valueOf(value));
        } else {
            mmkv.encode(key, JSON.toJSONString(value));
        }
    }

    public static void put(@MMKVKey @NonNull String key, @NonNull byte[] value) {
        put(getDefaultMMKV(), key, value);
    }

    public static void put(MMKV mmkv, @MMKVKey @NonNull String key, @NonNull byte[] value) {
        mmkv.encode(key, value);
    }

    public static void put(@MMKVKey @NonNull String key, @NonNull Set<String> value) {
        put(getDefaultMMKV(), key, value);
    }

    public static void put(MMKV mmkv, @MMKVKey @NonNull String key, @NonNull Set<String> value) {
        mmkv.encode(key, value);
    }

    public static void put(@MMKVKey @NonNull String key, @NonNull Parcelable value) {
        put(getDefaultMMKV(), key, value);
    }

    public static void put(MMKV mmkv, @MMKVKey @NonNull String key, @NonNull Parcelable value) {
        mmkv.encode(key, value);
    }

    public static <T> T get(@MMKVKey @NonNull String key, @NonNull T defaultValue) {
        return get(getDefaultMMKV(), key, defaultValue);
    }

    public static <T> T get(MMKV mmkv, @MMKVKey @NonNull String key, @NonNull T defaultValue) {
        String value = mmkv.decodeString(key, String.valueOf(defaultValue));
        if (defaultValue instanceof String) {
            return (T) value;
        } else if (defaultValue instanceof Integer) {
            return (T) Integer.valueOf(value);
        } else if (defaultValue instanceof Boolean) {
            return (T) Boolean.valueOf(value);
        } else if (defaultValue instanceof Float) {
            return (T) Float.valueOf(value);
        } else if (defaultValue instanceof Long) {
            return (T) Long.valueOf(value);
        } else if (defaultValue instanceof Double) {
            return (T) Double.valueOf(value);
        } else {
            return (T) JSON.parseObject(value, defaultValue.getClass());
        }
    }

    public static byte[] get(@MMKVKey @NonNull String key, @NonNull byte[] defaultValue) {
        return get(getDefaultMMKV(), key, defaultValue);
    }

    public static byte[] get(MMKV mmkv, @MMKVKey @NonNull String key, @NonNull byte[] defaultValue) {
        return mmkv.decodeBytes(key, defaultValue);
    }

    public static Set<String> get(@MMKVKey @NonNull String key, @NonNull Set<String> defaultValue) {
        return get(getDefaultMMKV(), key, defaultValue);
    }

    public static Set<String> get(MMKV mmkv, @MMKVKey @NonNull String key, @NonNull Set<String> defaultValue) {
        return mmkv.decodeStringSet(key, defaultValue);
    }

    public static Parcelable get(@MMKVKey @NonNull String key, @NonNull Parcelable defaultValue) {
        return get(getDefaultMMKV(), key, defaultValue);
    }

    public static Parcelable get(MMKV mmkv, @MMKVKey @NonNull String key, @NonNull Parcelable defaultValue) {
        return mmkv.decodeParcelable(key, defaultValue.getClass());
    }

    public static void remove(@MMKVKey @NonNull String key) {
        remove(getDefaultMMKV(), key);
    }

    public static void remove(MMKV mmkv, @MMKVKey @NonNull String key) {
        mmkv.remove(key);
    }

    public static void clear() {
        clear(getDefaultMMKV());
    }

    public static void clear(MMKV mmkv) {
        mmkv.clearAll();
    }

    public static boolean contains(@MMKVKey @NonNull String key) {
        return contains(getDefaultMMKV(), key);
    }

    public static boolean contains(MMKV mmkv, @MMKVKey @NonNull String key) {
        return mmkv.contains(key);
    }

    public static Map<String, ?> getAll() {
        return getAll(getDefaultMMKV());
    }

    public static Map<String, ?> getAll(MMKV mmkv) {
        return mmkv.getAll();
    }

}
