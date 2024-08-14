package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditSimpleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_simple);
        EditText et_phone = findViewById(R.id.ed_phone);
        Button btn_login = findViewById(R.id.btn_login);
        EditText et_username = findViewById(R.id.et_username);

        btn_login.setOnClickListener(v -> {
            String phone = et_phone.getText().toString();
            //如果手机号码不足11位
            if (TextUtils.isEmpty(phone) || phone.length() < 11) {
                //手机号编辑框请求焦点，将光标重定位至手机号编辑框
                et_phone.requestFocus();
                //弹出提示信息
                Toast.makeText(this, "请输入11位手机号码", Toast.LENGTH_SHORT).show();
            } else et_username.setText(phone);
        });
    }
}