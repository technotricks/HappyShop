package com.sephora.happyshop.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sephora.happyshop.R;
/**
 * Created by Kishore on 21/8/2017.
 */
public class SplashActivity extends AppCompatActivity {


    private Context context;
    protected int _splashTime = 3000;

    protected boolean _active = true;

    private  Thread splashTread ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context=this;
        intializeUI();
    }
    private void intializeUI()
    {

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)) {
                        sleep(100);
                        if (_active) {
                            waited += 100;
                        }
                    }
                } catch (InterruptedException e) {
                } finally {

                    Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        splashTread.start();
    }
}
