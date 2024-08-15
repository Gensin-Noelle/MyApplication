package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.MyPackage.ViewUtil;

public class EditSimpleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_simple);
        EditText et_phone = findViewById(R.id.ed_phone);
        Button btn_login = findViewById(R.id.btn_login);
        EditText et_username = findViewById(R.id.et_username);
        //登录点击事件
        btn_login.setOnClickListener(v -> {
            String phone = et_phone.getText().toString();
            //如果手机号码不足11位
            if (TextUtils.isEmpty(phone) || phone.length() < 11) {
                //手机号编辑框请求焦点，将光标重定位至手机号编辑框
                et_phone.requestFocus();
                //弹出提示信息
                Toast.makeText(this, "请输入11位手机号码", Toast.LENGTH_SHORT).show();
            } else {
                //显示输入信息
                et_username.setText(phone);

                //对话框练习
                //创建对话框的构建器
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                //设置对话框标题
                builder.setTitle("尊敬的用户");
                //设置对话框内容文本
                builder.setMessage("真的要登录吗");
                //设置对话框肯定按钮文本及监听器
                builder.setPositiveButton("yes", (dialog, witch) ->{
                    //执行肯定逻辑
                    et_username.setText("hello");
                });
                //设置对话框否定按钮文本及监听器
                builder.setNegativeButton("cancel", (dialog, witch) ->{
                    et_username.setText("bye");
                });
                //根据构建器构建提醒对话框对象
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        //监听编辑框
        et_phone.addTextChangedListener(new HideTextWatcher(et_phone, 11));
    }

    //定义一个编辑框监听器，在输入文本达到指定长度自带隐藏输入法
    private class  HideTextWatcher implements TextWatcher{

        private  EditText mView;
        private int mMaxLength;

        public HideTextWatcher(EditText v, int maxLength){
            mMaxLength = maxLength;
            mView = v;
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        //在编辑框输入文本变化后触发
        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == mMaxLength){
                ViewUtil.hideOneInputMethod(EditSimpleActivity.this, mView);
            }
        }
    }
}