package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RadioHorizontalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_horizontal);

        RadioGroup rg_gender = findViewById(R.id.rg_gender);
        TextView tv_result = findViewById(R.id.tv_result4);

        rg_gender.setOnCheckedChangeListener((group, checkId) -> {
            String desc = checkId == R.id.rd_male ? "你是一个帅气的男孩" : "你是一个漂亮的女孩";
            tv_result.setText(desc);
        });
    }
}