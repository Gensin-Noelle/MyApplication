package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        CheckBox ck_custom = findViewById(R.id.ck_custom);
        //监听复选框
        ck_custom.setOnCheckedChangeListener((buttonView,isChecked) -> {
            String desc = String.format("您%s了这个复选框", isChecked ? "勾选" : "取消勾选");
            buttonView.setText(desc);
            //打开一个新的界面
            Intent intent = new Intent();
            intent.setClass(this, SwitchDefaultActivity.class);
            startActivity(intent);
        });
    }
}