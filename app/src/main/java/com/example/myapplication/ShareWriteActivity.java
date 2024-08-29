package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

public class ShareWriteActivity extends AppCompatActivity {

    SharedPreferences preferences;
    EditText et_name;
    EditText et_age;
    EditText et_height;
    EditText et_weight;
    CheckBox cb_married;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_write);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_height = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);
        cb_married = findViewById(R.id.cb_married);

        preferences = getSharedPreferences("config", Context.MODE_PRIVATE);

        findViewById(R.id.btn_save).setOnClickListener(v -> {
            String name = et_name.getText().toString();
            String age = et_age.getText().toString();
            String height = et_height.getText().toString();
            String weight = et_weight.getText().toString();

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("name", name);
            editor.putInt("age", Integer.parseInt(age));
            editor.putFloat("height", Float.parseFloat(height));
            editor.putFloat("weight", Float.parseFloat(weight));
            editor.putBoolean("married", cb_married.isChecked());
            editor.apply();
        });

        reload();
    }

    private void reload() {
        String name = preferences.getString("name", null);
        int age = preferences.getInt("age", 0);
        float height = preferences.getFloat("height", 0f);
        float weight = preferences.getFloat("weight", 0f);
        boolean married = preferences.getBoolean("married", false);

        if (name != null){
            et_name.setText(name);
        }
        if (age != 0){
            et_age.setText(String.valueOf(age));
        }
        if (height != 0f){
            et_height.setText(String.valueOf(height));
        }
        if(weight != 0f){
            et_weight.setText(String.valueOf(weight));
        }
        cb_married.setChecked(married);
    }
}