package com.nan.xarch.util.reflect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class ReflectArgument<T> {
    private final Class<T> clazz;
    private final T value;

    private ReflectArgument(@NonNull Class<T> clazz,
                            @Nullable T value) {
        this.clazz = clazz;
        this.value = value;
    }

    public static <T> ReflectArgument<T> of(@NonNull String className,
                                            @Nullable T value) {
        Class<T> clazz = ReflectHelper.getClassForName(className);
        return new ReflectArgument<>(clazz, value);
    }

    public static <T> ReflectArgument<T> of(@NonNull Class<T> clazz,
                                            @Nullable T value) {
        return new ReflectArgument<>(clazz, value);
    }

    public static <T> ReflectArgument<T> of(@NonNull T value) {
        Class<T> clazz = (Class<T>) value.getClass();
        return new ReflectArgument<>(clazz, value);
    }

    static Class[] getClasses(@NonNull ReflectArgument[] args) {
        final int size = args.length;
        Class[] result = new Class[size];
        for (int i = 0; i < size; i++) {
            result[i] = args[i].clazz;
        }
        return result;
    }

    static Object[] getValues(@NonNull ReflectArgument[] args) {
        final int size = args.length;
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = args[i].value;
        }
        return result;
    }
}