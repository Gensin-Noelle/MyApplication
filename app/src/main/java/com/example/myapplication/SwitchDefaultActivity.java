package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

public class SwitchDefaultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_default);
        Switch sw_default = findViewById(R.id.sw_status);
        TextView tv_result = findViewById(R.id.tv_result3);
        sw_default.setOnCheckedChangeListener((view, isChecked) ->{
            String desc = String.format("开关的状态是%s", isChecked ? "开" : "关");
            tv_result.setText(desc);
        });
    }
}