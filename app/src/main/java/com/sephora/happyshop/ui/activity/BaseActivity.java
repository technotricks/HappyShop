package com.sephora.happyshop.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by simtech on 21/8/2017.
 */

public class BaseActivity extends AppCompatActivity {


    public Context context;

    public Intent intent;

    public  ProgressDialog pDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);



    }

    public void baseInitialization(){
        ButterKnife.bind(this);
        context = this;

        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Just a moment...");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);

    }



}
