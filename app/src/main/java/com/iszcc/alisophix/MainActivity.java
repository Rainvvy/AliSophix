package com.iszcc.alisophix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String temp = "这是热修复之后的22222";
        Toast.makeText(this,temp ,Toast.LENGTH_LONG).show();
    }
}