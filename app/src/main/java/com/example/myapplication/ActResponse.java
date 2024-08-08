package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MyPackage.DateUtil;

public class ActResponse extends AppCompatActivity {

    private static final String mResponse = "响应测试包";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_response);
        TextView tv_request = findViewById(R.id.tv_request);
        //从上一个页面传来的意图中获取快递包裹
        Bundle bundle = getIntent().getExtras();
        String request_time = bundle.getString("request_time");
        String request_context = bundle.getString("request_context");
        String desc = String.format("收到请求信息:\n请求时间为:%s\n请求内容为:%s",request_time, request_context);
        //将请求信息显示在文本视图上
        tv_request.setText(desc);
        //将响应信息显示在另一个文本视图上
        TextView tv_response = findViewById(R.id.tv_response);
        tv_response.setText(" 响应信息:" + mResponse);

        findViewById(R.id.btn_response).setOnClickListener(v -> {
            Intent intent = new Intent();
            Bundle bundle1 = new Bundle();
            bundle1.putString("response_time", DateUtil.getNowTime());
            bundle1.putString("response_context", mResponse);
            intent.putExtras(bundle1);
            //携带意图返回上一个页面，result_Ok表示处理成功
            setResult(Activity.RESULT_OK, intent);
            //结束当前页面
            finish();
        });
    }
}