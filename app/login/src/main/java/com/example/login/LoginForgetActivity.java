package com.example.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class LoginForgetActivity extends AppCompatActivity {
    String verifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_forget);

        EditText et_newPassword = findViewById(R.id.et_phone);
        EditText et_confirmPassword = findViewById(R.id.et_confirm_password);
        EditText et_verifyCode = findViewById(R.id.et_verify_code);

        String phone_code = getIntent().getStringExtra("phone_code");
        //设置”获取验证码“按钮点击事件
        findViewById(R.id.btn_verify_code).setOnClickListener(v -> {
            //生产6位随机验证码,生产0-999999的6位随机数，不足6位用0填充
            verifyCode = String.format("%06d", new Random().nextInt(999999));
            //弹出对话框，提示用户记住六位验证码
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("请记住验证码");
            builder.setMessage("手机号:\n" + phone_code + "\n本次验证码是:\n" + verifyCode);
            builder.setPositiveButton("好的", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        //设置”确定“按钮点击事件
        findViewById(R.id.btn_confirm).setOnClickListener(v -> {
            String newPassword = et_newPassword.getText().toString();
            String confirmPassword = et_confirmPassword.getText().toString();

            if (newPassword.length() < 6){
                Toast.makeText(this, "密码不足6位", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!confirmPassword.equals(newPassword)){
                Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!verifyCode.equals(et_verifyCode.getText().toString())){
                Toast.makeText(this, "验证码有误", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "密码修改成功", Toast.LENGTH_SHORT).show();
            //把修改好的密码返回给上个页面
            Intent intent = new Intent();
            intent.putExtra("newPassword", confirmPassword);
            setResult(Activity.RESULT_OK, intent);
            //结束当前页面（回到上个页面）
            finish();
        });
    }
}