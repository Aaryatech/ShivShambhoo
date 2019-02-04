package com.ats.shivshambhoo.activity;

import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ats.shivshambhoo.R;

import java.io.File;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try {
            File dir = new File(Environment.getExternalStorageDirectory() + File.separator, "ShivShambhu" + File.separator + "Quotation");
            if (dir.exists()) {
                if (dir.isDirectory()) {
                    String[] children = dir.list();
                    if (children != null) {
                        for (int i = 0; i < children.length; i++) {
                            new File(dir, children[i]).delete();
                        }
                    }
                }
            }
        } catch (Exception e) {
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            }
        }, 3000);

    }
}
