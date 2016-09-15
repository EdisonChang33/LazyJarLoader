package com.hobby.lazyjar.plugin;

import android.content.Context;

import com.hobby.lazyjar.base.Config;
import com.hobby.lazyjar.base.IJarInvoker;
import com.hobby.lazyjar.base.Patch;
import com.hobby.lazyjar.utils.FileUtils;

import java.io.File;

/**
 * Created by edisonChang on 2016/9/14.
 */

@Patch({TestCls1.class, TestCls2.class})
public class TestJarInvoker extends IJarInvoker {

    public TestJarInvoker(Context context) {
        super(context);
    }

    @Override
    public String getJarName() {
        return Config.SDK_JAR_NAME;
    }

    @Override
    public String getFilePath(Context context) {
        //you can run task in thread
        File file = FileUtils.getPrivateFile(context, Config.SDK_JAR_NAME);
        if (!FileUtils.isLegal(file)) {
            FileUtils.copyFromAssets(context, Config.SDK_JAR_NAME, file);
        }

        if (file != null && FileUtils.isLegal(file)) {
            return file.getAbsolutePath();
        } else {
            throw new RuntimeException("TestJarInvoker load jar file failed");
        }
    }

    public void showToast(Context context) {
        Object instance = call(TestCls2.CLS_NAME, null, "getInstance", null, null);
        if (instance != null) {
            call(TestCls2.CLS_NAME, instance, "showToast", new Class[]{Context.class}, new Object[]{context});
        }
    }

    public void setCallback(TestCls1.Test1Callback callback) {
        Object instance = call(TestCls1.CLS_NAME, null, "getInstance", null, null);

        if (instance != null) {
            call(TestCls1.CLS_NAME, instance, "setCallback", null, new Object[]{callback});
        }
    }

}
