package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MyPackage.DateUtil;

public class ActRequest extends AppCompatActivity {
    private static final String mRequest = "这是测试包";
    private ActivityResultLauncher<Intent> register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_request);
        TextView tv_request = findViewById(R.id.tv_request);
        TextView tv_response = findViewById(R.id.tv_response);
        //显示请求内容
        tv_request.setText("测试信息:" + mRequest);

        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), o -> {
            if(o != null){
                Intent intent = o.getData();
                if (intent != null && o.getResultCode() == Activity.RESULT_OK){
                    Bundle bundle = intent.getExtras();
                    String response_time = bundle.getString("response_time");
                    String response_context = bundle.getString("response_context");
                    String desc = String.format("收到响应信息:\n响应时间为：%s\n响应内容为：%s",response_time, response_context);
                    //显示响应内容
                    tv_response.setText(desc);
                }
            }
        });

        findViewById(R.id.btn_request).setOnClickListener(v -> {
            Intent intent = new Intent(this, ActResponse.class);
            //创建一个包裹，里面放入待发送信息
            Bundle bundle = new Bundle();
            bundle.putString("request_time", DateUtil.getNowTime());
            bundle.putString("request_context", mRequest);
            intent.putExtras(bundle);
            register.launch(intent);
        });
    }
}