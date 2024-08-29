package com.example.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login.MyPackage.ViewUtil;

import java.util.Random;


public class LoginMainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            Intent intent = o.getData();
            if (intent != null && o.getResultCode() == Activity.RESULT_OK) {
                String newPassword = intent.getStringExtra("newPassword");
                //更新密码
                password = newPassword;
                //Toast.makeText(LoginMainActivity.this, "成功更新密码，新密码生效", Toast.LENGTH_SHORT).show();
            }
        }
    });
    String password = "111111";
    String mVerifyCode;
    String phoneNum;
    SharedPreferences preferences;
    EditText et_password;
    EditText et_phone;
    CheckBox cb_remember;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        RadioGroup rg_login = findViewById(R.id.rg_login);
        Button btn_forget = findViewById(R.id.btn_verify_code);
        Button btn_login = findViewById(R.id.btn_login);
        TextView tv_password = findViewById(R.id.tv_password);
        cb_remember = findViewById(R.id.cb_remember);
        et_password = findViewById(R.id.et_password);
        et_phone = findViewById(R.id.et_phone);
        RadioButton rb_password = findViewById(R.id.rb_password);
        RadioButton rb_verify_code = findViewById(R.id.rb_verify_code);
        preferences = getSharedPreferences("config", Context.MODE_PRIVATE);

        reload();

        //给rg_login设置单选监听器
        rg_login.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_password) {
                tv_password.setText("登录密码:");
                et_password.setHint("请输入密码");
                btn_forget.setText("忘记密码");
                cb_remember.setVisibility(View.VISIBLE);
            } else {
                tv_password.setText(" 验证码:");
                et_password.setHint("获取验证码");
                btn_forget.setText("获取验证码");
                cb_remember.setVisibility(View.GONE);
            }
        });

        //给et_phone添加文本变更监听器
        et_phone.addTextChangedListener(new HideTextWatcher(et_phone, 11));
        //给密码框添加文本变更监听器
        et_password.addTextChangedListener(new HideTextWatcher(et_password, 6));
        //忘记密码设置事件监听
        btn_forget.setOnClickListener(v -> {
            //判断手机号码是否足够11位
            if (et_phone.getText().toString().length() < 11) {
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            } else {
                phoneNum = et_phone.getText().toString();
            }
            //选择密码方式登录
            if (rb_password.isChecked()) {
                Intent intent = new Intent(this, LoginForgetActivity.class);
                intent.putExtra("phone_code", phoneNum);
                register.launch(intent);
            } else if (rb_verify_code.isChecked()) {
                //生产6位随机验证码,生产0-999999的6位随机数，不足6位用0填充
                mVerifyCode = String.format("%06d", new Random().nextInt(999999));
                //弹出对话框，提示用户记住六位验证码
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("请记住验证码");
                builder.setMessage("手机号:\n" + phoneNum + "\n本次验证码是:\n" + mVerifyCode);
                builder.setPositiveButton("好的", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        //设置记住密码事件
        cb_remember.setOnCheckedChangeListener((l, isChecked) -> {
            if (isChecked) {
                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = preferences.edit();
                editor.putString("phoneNum", phoneNum);
                editor.putString("password", password);
                editor.putBoolean("isRemember", cb_remember.isChecked());
                editor.apply();
            }
        });

        //登录按钮设置监听事件
        btn_login.setOnClickListener(v -> {
            if (rb_password.isChecked()) {
                if (password.equals(et_password.getText().toString())) {
                    //提示登录成功
                    loginSuccess();
                } else {
                    Toast.makeText(this, "请输入正确的密码", Toast.LENGTH_SHORT).show();
                }
            } else if (rb_verify_code.isChecked()) {
                if (mVerifyCode.equals(et_password.getText().toString())) {
                    //提示登录成功
                    loginSuccess();
                } else {
                    Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void reload() {
        if (preferences.getBoolean("isRemember", false)) {
            String phoneNum = preferences.getString("phoneNum", "");
            String password = preferences.getString("password", "");
            et_phone.setText(phoneNum);
            et_password.setText(password);
            cb_remember.setChecked(preferences.getBoolean("isRemember", false));

        }
    }

    private void loginSuccess() {
        String desc = String.format("您的手机号是%s,恭喜通过登录验证，点击确认返回上个页面", phoneNum);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录成功");
        builder.setMessage(desc);
        builder.setPositiveButton("确认", ((dialog, which) -> {
            //结束当前活动页面
            finish();
        }));
        builder.setNegativeButton("我在看看", null);
        builder.create().show();

    }

    private class HideTextWatcher implements TextWatcher {
        private final EditText mView;
        private final int mMaxLength;

        public HideTextWatcher(EditText editText, int maxLength) {
            mView = editText;
            mMaxLength = maxLength;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (et_phone != null){
                phoneNum = et_phone.getText().toString();
            }
            if (s.length() == mMaxLength) {
                ViewUtil.hideOneInputMethod(LoginMainActivity.this, mView);
            }
        }
    }
}