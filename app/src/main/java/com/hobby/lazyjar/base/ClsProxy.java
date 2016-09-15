package com.hobby.lazyjar.base;

import java.lang.reflect.Method;

/**
 * Created by edisonChang on 2016/9/14.
 */
public abstract class ClsProxy {

    protected Class<?> cls;
    protected IJarInvoker invoker;

    public abstract String getClsName();

    public ClsProxy(IJarInvoker invoker) {
        this.invoker = invoker;
        try {
            cls = invoker.getJarClassLoader().loadClass(getClsName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Class<?> loadClass(String cls) {
        try {
            return invoker.getJarClassLoader().loadClass(cls);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Object call(Object obj, String func, Class[] parameterTypes, Object[] objects) {

        if (cls != null) {
            Method method;
            try {
                method = cls.getMethod(func, parameterTypes);
                return method.invoke(obj, objects);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
