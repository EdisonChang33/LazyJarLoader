package com.hobby.lazyjar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hobby.lazyjar.base.IJarInvoker;
import com.hobby.lazyjar.plugin.TestCls1;
import com.hobby.lazyjar.plugin.TestJarInvoker;

/**
 * Created by edisonChang on 2016/9/14.
 */
public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;

    private TestJarInvoker invoker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (invoker == null) {
                    invoker = new TestJarInvoker(MainActivity.this);
                }

                invoker.setCallback(new TestCls1.Test1Callback() {
                    @Override
                    public void onSuccess(int result) {
                        Toast.makeText(MainActivity.this, "TestCls1 onSuccess = " + result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(int error, String errorMsg) {
                        Toast.makeText(MainActivity.this, "TestCls1 onError = " + errorMsg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (invoker == null) {
                    invoker = new TestJarInvoker(MainActivity.this);
                }
                invoker.showToast(MainActivity.this);
            }
        });
    }
}
