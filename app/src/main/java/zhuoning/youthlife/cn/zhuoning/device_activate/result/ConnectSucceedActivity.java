package zhuoning.youthlife.cn.zhuoning.device_activate.result;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import zhuoning.youthlife.cn.zhuoning.R;
import zhuoning.youthlife.cn.zhuoning.base.BaseActivity;

public class ConnectSucceedActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_succeed);
        findViewById(R.id.start_to_use_button).setOnClickListener(
                v -> startActivity(new Intent(ConnectSucceedActivity.this, BaseActivity.class))
        );
    }
}
