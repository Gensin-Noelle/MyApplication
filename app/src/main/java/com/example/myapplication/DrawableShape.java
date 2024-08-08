package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class DrawableShape extends AppCompatActivity {
    private View v_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_shape);
        v_content = findViewById(R.id.v_content);
        //将蓝绿色矩形设置默认背景
        v_content.setBackgroundResource(R.drawable.shape_rect_teal);

        findViewById(R.id.btn_rect).setOnClickListener(v -> {
            v_content.setBackgroundResource(R.drawable.shape_rect_teal);
        });

        findViewById(R.id.btn_oval).setOnClickListener(v -> {
            v_content.setBackgroundResource(R.drawable.shape_oval_rose);
        });
    }
}