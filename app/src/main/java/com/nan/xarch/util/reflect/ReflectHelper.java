package com.nan.xarch.util.reflect;

import androidx.annotation.NonNull;

import com.nan.xarch.util.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 反射调用帮助类
 */
public final class ReflectHelper {
    private static final String TAG = "ReflectHelper";

    private static final Object NOT_FOUND = new Object();

    private static final Map<String, Object> CACHE = new ConcurrentHashMap<>();

    private Object inst;
    private Class clazz;

    private ReflectHelper(Class clazz) {
        this.clazz = clazz;
    }

    public ReflectHelper setInstance(Object o) {
        this.inst = o;
        return this;
    }

    public Object getField(@NonNull String fieldName) {
        Field field = getClassField(this.clazz, fieldName);
        if (field != null) {
            try {
                return field.get(this.inst);
            } catch (Exception e) {
                Logger.e(TAG, "getField error=%s", e);
            }
        }
        return null;
    }

    public boolean setField(String fieldName, Object value) {
        Field field = getClassField(this.clazz, fieldName);
        if (field != null) {
            try {
                field.set(this.inst, value);
                return true;
            } catch (Exception e) {
                Logger.e(TAG, "setField error=%s", e);
            }
        }
        return false;
    }

    /**
     * 执行反射方法，并返回执行状态和函数返回值
     *
     * @param outResult  方法返回值
     * @param methodName 方法名称
     * @param args       参数列表，包含类型和值
     * @return 是否调用成功
     */
    public boolean invoke(Object[] outResult, String methodName, ReflectArgument... args) {
        Method method = getClassMethod(this.clazz, methodName, ReflectArgument.getClasses(args));
        if (method != null) {
            try {
                Object result = method.invoke(this.inst, ReflectArgument.getValues(args));
                if (outResult != null && outResult.length > 0) {
                    outResult[0] = result;
                }
                return true;
            } catch (Exception e) {
                Logger.e(TAG, "invoke error=%s", e);
            }
        }
        return false;
    }

    /**
     * 执行反射方法
     *
     * @param methodName 方法名称
     * @param args       参数列表，包含类型和值
     * @return 函数返回值
     */
    public Object invoke(String methodName, ReflectArgument... args) {
        Object[] result = new Object[1];
        if (invoke(result, methodName, args)) {
            return result[0];
        }
        return null;
    }

    /**
     * 新建对象实例
     *
     * @param args 构造方法参数
     * @return 新实例
     */
    public Object newInstance(ReflectArgument... args) {
        Constructor constructor = getClassConstructor(this.clazz, ReflectArgument.getClasses(args));
        if (constructor != null) {
            try {
                return constructor.newInstance(ReflectArgument.getValues(args));
            } catch (Exception e) {
                Logger.e(TAG, "newInstance error=%s", e);
            }
        }
        return null;
    }

    private static Field getClassField(Class<?> target, String fieldName) {
        if (target == null || target == Object.class) {
            return null;
        }
        String key = target.getName() + '#' + fieldName;
        Object value = CACHE.get(key);
        if (value == NOT_FOUND) {
            Logger.d(TAG, "getClassField NOT_FOUND %s", key);
            return null;
        }
        Field field = (Field) value;
        if (field == null) {
            try {
                field = target.getDeclaredField(fieldName);
                field.setAccessible(true);
            } catch (Exception e) {
                //中间过程不记录
            }
            if (field == null) {
                // 递归查找父类
                field = getClassField(target.getSuperclass(), fieldName);
            }
            putCache(key, field);
        }
        return field;
    }

    private static Constructor getClassConstructor(Class<?> target, Class[] paramTypes) {
        if (target == null) {
            return null;
        }
        String key = target.getName() + "#<init>(" + Arrays.toString(paramTypes) + ')';
        Object value = CACHE.get(key);
        if (value == NOT_FOUND) {
            Logger.d(TAG, "getClassConstructor NOT_FOUND %s", key);
            return null;
        }
        Constructor constructor = (Constructor) value;
        if (constructor == null) {
            try {
                constructor = target.getDeclaredConstructor(paramTypes);
                constructor.setAccessible(true);
            } catch (Exception e) {
                Logger.e(TAG, "getClassConstructor NOT_FOUND %s error=%s", key, e);
            }
            putCache(key, constructor);
        }
        return constructor;
    }

    private static Method getClassMethod(Class<?> target, String methodName, Class[] paramTypes) {
        if (target == null || target == Object.class) {
            return null;
        }
        String key = target.getName() + '#' + methodName + '(' + Arrays.toString(paramTypes) + ')';
        Object value = CACHE.get(key);
        if (value == NOT_FOUND) {
            Logger.d(TAG, "getClassMethod NOT_FOUND %s", key);
            return null;
        }
        Method method = (Method) value;
        if (method == null) {
            try {
                method = target.getDeclaredMethod(methodName, paramTypes);
                method.setAccessible(true);
            } catch (Exception e) {
                //中间过程不记录
            }
            if (method == null) {
                // 递归查找父类
                method = getClassMethod(target.getSuperclass(), methodName, paramTypes);
            }
            putCache(key, method);
        }
        return method;
    }

    static Class getClassForName(String className) {
        Object value = CACHE.get(className);
        if (value == NOT_FOUND) {
            Logger.d(TAG, "getClassForName NOT_FOUND %s", className);
            return null;
        }

        Class clazz = (Class) value;
        if (clazz == null) {
            try {
                clazz = Class.forName(className);
            } catch (Exception e) {
                Logger.e(TAG, "getClassForName NOT_FOUND %s error=%s", className, e);
            }
            putCache(className, clazz);
        }
        return clazz;
    }

    public static ReflectHelper of(Class clazz) {
        return new ReflectHelper(clazz);
    }

    public static ReflectHelper of(String className) {
        return new ReflectHelper(getClassForName(className));
    }

    public static ReflectHelper of(Object inst) {
        return new ReflectHelper(inst != null ? inst.getClass() : null)
                .setInstance(inst);
    }

    public static int getFieldCount(@NonNull Class<? extends Annotation> clazz) {
        return clazz.getFields().length;
    }

    private static void putCache(String key, Object value) {
        if (value == null || value == NOT_FOUND) {
            Logger.e(TAG, "NOT_FOUND %s", key);
            value = NOT_FOUND;
        }
        CACHE.put(key, value);
    }
}