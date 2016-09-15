package com.hobby.lazyjar.plugin;

import com.hobby.lazyjar.base.ClsProxy;
import com.hobby.lazyjar.base.IJarInvoker;

/**
 * Created by edisonChang on 2016/9/14.
 */
public class TestCls2 extends ClsProxy {

    public static final String CLS_NAME = "com.hobby.testjar.TestCls2";

    public TestCls2(IJarInvoker invoker) {
        super(invoker);
    }

    @Override
    public String getClsName() {
        return CLS_NAME;
    }
}
