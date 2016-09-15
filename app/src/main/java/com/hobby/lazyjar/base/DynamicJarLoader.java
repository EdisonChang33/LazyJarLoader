package com.hobby.lazyjar.base;

import dalvik.system.DexClassLoader;

/**
 * Created by edisonChang on 2016/9/14.
 */
public enum  DynamicJarLoader {

    INSTANCE;

    public DexClassLoader getDexClassLoader(String dexPath, String optimizedDirectory, String libraryPath, ClassLoader parent) {
        return new DexClassLoader(dexPath, optimizedDirectory, libraryPath, parent);
    }
}
