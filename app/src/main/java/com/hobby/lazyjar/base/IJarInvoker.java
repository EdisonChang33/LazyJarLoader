package com.hobby.lazyjar.base;


import android.content.Context;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import dalvik.system.DexClassLoader;

/**
 * Created by edisonChang on 2016/9/14.
 */
public abstract class IJarInvoker {

    protected static final String TAG = "IJarInvoker";

    protected static Map<String, DexClassLoader> jarClassLoaders = new HashMap<>();

    private Map<String, ClsProxy> clsProxyMap = new HashMap<>();

    public IJarInvoker(Context context) {
        init(context);
        applyProxyCls();
    }

    private DexClassLoader init(Context context) {
        DexClassLoader dexClassLoader = getJarClassLoader();
        if (dexClassLoader == null) {
            dexClassLoader = DynamicJarLoader.INSTANCE.getDexClassLoader(getFilePath(context),
                    context.getDir(Config.SDK_JAR_DIR, Context.MODE_PRIVATE).getAbsolutePath(),
                    null,
                    context.getClassLoader());

            jarClassLoaders.put(getJarName(), dexClassLoader);
        }

        return dexClassLoader;
    }

    public DexClassLoader getJarClassLoader() {
        return jarClassLoaders.get(getJarName());
    }

    public abstract String getJarName();

    public abstract String getFilePath(Context context);

    public Object call(String cls, Object obj, String func, Class[] parameterTypes, Object[] objects) {
        try {
            ClsProxy clsProxy = getClsProxy(cls);
            if (clsProxy != null) {
                return clsProxy.call(obj, func, parameterTypes, objects);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void applyProxyCls() {
        Class<? extends IJarInvoker> clazz = getClass();
        Patch patch = clazz.getAnnotation(Patch.class);
        if (patch != null) {
            Class<? extends ClsProxy>[] proxys = patch.value();
            for (Class<? extends ClsProxy> proxy : proxys) {
                applyCls(proxy);
            }
        }
    }

    private void applyCls(Class<? extends ClsProxy> cls) {
        try {
            Constructor<?> constructor = cls.getDeclaredConstructors()[0];
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            ClsProxy proxy;
            if (constructor.getParameterTypes().length == 0) {
                proxy = (ClsProxy) constructor.newInstance();
            } else {
                proxy = (ClsProxy) constructor.newInstance(this);
            }

            clsProxyMap.put(proxy.getClsName(), proxy);
        } catch (Exception e) {

        }
    }

    protected ClsProxy getClsProxy(String cls) {
        return clsProxyMap.get(cls);
    }
}
