package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MyPackage.DateUtil;

public class ButtonStyle extends AppCompatActivity {

    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_style);
        tv_result = findViewById(R.id.tv_result);
        TextView tv_result2 = findViewById(R.id.tv_result2);
        Button button1 = findViewById(R.id.button);
        Button click = findViewById(R.id.button2);

        click.setOnClickListener(v ->{
            String result = String.format("%s点击事件二%s", DateUtil.getNowTime(),"");
            tv_result2.setText(result);

            //禁用按钮一
            button1.setEnabled(false);
        });
        click.setOnLongClickListener(l -> {
            String result = String.format("%s长按点击事件%s", DateUtil.getNowTime(),"");
            tv_result2.setText(result);

            //启用按钮一
            button1.setEnabled(true);
            return true; //长按后不可以继续触发其他事件
        });
    }

    public void doClick(View view){
        tv_result.setText(DateUtil.getNowTime());
    }
}