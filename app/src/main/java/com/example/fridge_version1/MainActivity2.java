package com.example.fridge_version1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        actionBar = this.getSupportActionBar();
        actionBar.setTitle("장보기 메모 작성");
        actionBar.setDisplayHomeAsUpEnabled(false);
    }
}