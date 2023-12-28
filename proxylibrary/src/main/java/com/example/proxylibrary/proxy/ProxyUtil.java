package com.example.proxylibrary.proxy;

import android.os.Build;
import android.util.Log;

import org.jetbrains.annotations.Nullable;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtil {

    public static Object create(final Object service) {
        Object instance = Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                Log.e("zgf", "========invoke=======");
                // invoke必须传对应的对象
                Object invoke = method.invoke(service, objects);
                return invoke;
            }
        });
        return instance;
    }

    public static Object create1(final Class service, Object target) {
        Object instance = Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service}, new InvocationHandler() {
            @Override
            public @Nullable Object invoke(Object proxy, Method method, @Nullable Object[] args) throws Throwable {
                Log.e("zgf", "========invoke=======");
                // invoke必须传对应的对象
                return method.invoke(target, args);
            }
        });
        return instance;
    }

    public static <T> T create2(final Class<T> service) {
        T instance = null;
        try {
            Constructor<T> constructor = service.getConstructor();
            instance = constructor.newInstance();
        } catch (Exception e) {

        }

        // todo: 根据类的Class来生成对应类的实例对象
        T finalInstance = instance;
        Log.e("zgf", "======finalInstance========" + finalInstance);
        Object proxyInstance = Proxy.newProxyInstance(service.getClassLoader(), service.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Log.e("zgf", "======invoke========");
                return method.invoke(finalInstance, args);
            }
        });
        return (T) proxyInstance;
    }
}
