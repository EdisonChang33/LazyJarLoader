package com.hobby.lazyjar.plugin;

import com.hobby.lazyjar.base.ClsProxy;
import com.hobby.lazyjar.base.IJarInvoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by edisonChang on 2016/9/14.
 */
public class TestCls1 extends ClsProxy {

    public static final String CLS_NAME = "com.hobby.testjar.TestCls1";

    public TestCls1(IJarInvoker invoker) {
        super(invoker);
    }

    @Override
    public String getClsName() {
        return CLS_NAME;
    }

    @Override
    public Object call(Object obj, String func, Class[] parameterTypes, Object[] objects) {

        if ("setCallback".equals(func)) {
            Class<?> argClazz = loadClass("com.hobby.testjar.TestCls1$Test1Callback");
            Method method = null;
            try {
                method = cls.getMethod(func, argClazz);
                method.invoke(obj, getProxyInstance(argClazz, (Test1Callback) objects[0]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return super.call(obj, func, parameterTypes, objects);
    }

    private Object getProxyInstance(Class<?> clazz, final Test1Callback callback) throws ClassNotFoundException {
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if (method.getName().equals("onSuccess")) {
                    callback.onSuccess((Integer) args[0]);
                    return null;
                } else if (method.getName().equals("onError")) {
                    callback.onError((Integer) args[0], ((String) args[1]));
                    return null;
                } else {
                    try {
                        return callback.getClass().getMethod(method.getName(), method.getParameterTypes()).invoke(callback, args);
                    } catch (Exception e) {
                    }
                    return method.invoke(proxy, args);
                }
            }
        });
    }

    public interface Test1Callback {
        void onSuccess(int result);

        void onError(int error, String errorMsg);
    }
}
