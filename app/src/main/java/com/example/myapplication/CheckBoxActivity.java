package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;

public class CheckBoxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);
        CheckBox ck_system = findViewById(R.id.ck_system);
        //监听复选框
        ck_system.setOnCheckedChangeListener((buttonView,isChecked) -> {
            String desc = String.format("您%s了这个复选框", isChecked ? "勾选" : "取消勾选");
            buttonView.setText(desc);
        });
    }
}