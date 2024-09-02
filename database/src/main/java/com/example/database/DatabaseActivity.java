package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        TextView tv_context = findViewById(R.id.tv_context);
        Button btn_create = findViewById(R.id.btn_create);
        Button btn_delete = findViewById(R.id.btn_delete);
        String mDatabaseName = getFilesDir() + "/test.db";

        btn_create.setOnClickListener(v -> {
            //创建或打开数据库
            String desc;
            try (SQLiteDatabase db = openOrCreateDatabase(mDatabaseName, Context.MODE_PRIVATE, null)) {
                desc = String.format("数据库:%s创建成功", db.getPath());
            } catch (Exception e) {
                desc = String.format("数据库创建失败: %s", e.getMessage());
            }
            tv_context.setText(desc);
        });

        btn_delete.setOnClickListener(v -> {
            boolean result = deleteDatabase(mDatabaseName);
            tv_context.setText(String.format("数据库:%s删除%s", mDatabaseName, result ? "成功" : "失败"));
        });
    }
}