package com.hobby.testjar;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by edisonChang on 2016/9/14.
 */
public class TestCls2 {

    private static final String TAG = "TestCls2";

    private static final TestCls2 INSTANCE = new TestCls2();

    public static TestCls2 getInstance() {
        return INSTANCE;
    }

    public void showToast(Context context) {
        Toast.makeText(context.getApplicationContext(), "TestCls2 showToast", Toast.LENGTH_SHORT).show();
    }
}
