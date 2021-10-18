package com.cf.formview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cf.lib.formview.EasyToast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EasyToast.show(this,"ceshi");
    }
}