package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.database.enity.User;
import com.example.database.myDatabase.UserDBHelper;
import com.example.database.util.ToastUtil;

import java.util.List;

public class SQLiteHelperActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_age;
    private EditText et_height;
    private EditText et_weight;
    private CheckBox cb_married;
    private UserDBHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_helper);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_height = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);
        cb_married = findViewById(R.id.cb_married);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_search).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHelper = UserDBHelper.getInstance(this);
        mHelper.openWriteLink();
        mHelper.openReadLink();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHelper.closeLink();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        String height = et_height.getText().toString();
        String weight = et_weight.getText().toString();
        User user = null;

        switch (v.getId()) {
            case R.id.btn_add:
                user = new User(name, Integer.parseInt(age), Long.parseLong(height), Float.parseFloat(weight), cb_married.isChecked());
                if ( mHelper.insert(user) > 0){
                    ToastUtil.show(this, "添加成功");
                }
                break;
            case R.id.btn_search:
                List<User> list = mHelper.queryALL();
                for (User u : list){
                    Log.d("ning", u.toString());
                }
                break;
            case R.id.btn_delete:
                if (mHelper.deleteByName(name) > 0L){
                    ToastUtil.show(this, "删除成功");
                }else ToastUtil.show(this, "删除失败");
                break;
            case R.id.btn_update:
                user = new User(name, Integer.parseInt(age), Long.parseLong(height), Float.parseFloat(weight), cb_married.isChecked());
                if (mHelper.update(user) > 0L){
                    ToastUtil.show(this, "修改成功");
                }else  ToastUtil.show(this, "修改失败");
                break;

        }
    }
}