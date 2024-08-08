package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_input);
        Button btn = findViewById(R.id.login_successful);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginSuccessful.class);
            //设置启动标志，跳转到新页面时，栈中的原有实例都被清空，同时开辟新任务的活动栈
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }
}