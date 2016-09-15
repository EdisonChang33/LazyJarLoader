package com.hobby.testjar;

/**
 * Created by edisonChang on 2016/9/14.
 */
public class TestCls1 {

    private static final TestCls1 INSTANCE = new TestCls1();

    public static TestCls1 getInstance() {
        return INSTANCE;
    }

    public void setCallback(Test1Callback callback) {

        if (callback != null) {
            callback.onSuccess(100);
            callback.onError(-1, "TestCls1 callback error");
        }
    }


    public interface Test1Callback {
        void onSuccess(int result);

        void onError(int error, String errorMsg);
    }

}
