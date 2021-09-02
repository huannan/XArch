package com.nan.xarch.util;

import android.util.Log;

import com.nan.xarch.BuildConfig;

import java.util.Locale;

public final class Logger {

    private static final String TAG = "Logger";

    private Logger() {
        throw new RuntimeException("No instances!");
    }

    private static boolean isLoggable() {
        return BuildConfig.DEBUG;
    }

    public static void i(String tag, String fmt, Object... args) {
        if (isLoggable()) {
            Log.i(tag, format(fmt, args));
        }
    }

    public static void d(String tag, String fmt, Object... args) {
        if (isLoggable()) {
            Log.d(tag, format(fmt, args));
        }
    }

    public static void w(String tag, String fmt, Object... args) {
        if (isLoggable()) {
            Log.w(tag, format(fmt, args));
        }
    }

    public static void e(String tag, String fmt, Object... args) {
        if (isLoggable()) {
            Log.e(tag, format(fmt, args));
        }
    }

    public static void e(String tag, Throwable t, String fmt, Object... args) {
        if (isLoggable()) {
            Log.e(tag, format(fmt, args), t);
        }
    }

    private static String format(String fmt, Object... args) {
        if (args.length == 0) {
            return fmt;
        } else {
            return String.format(Locale.getDefault(), fmt, args);
        }
    }
}
