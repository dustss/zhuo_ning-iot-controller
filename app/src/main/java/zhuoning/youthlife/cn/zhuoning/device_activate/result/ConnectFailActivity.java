package zhuoning.youthlife.cn.zhuoning.device_activate.result;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import zhuoning.youthlife.cn.zhuoning.R;

public class ConnectFailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_fail);
        findViewById(R.id.try_again_button).setOnClickListener(v ->
                {
                    Toast.makeText(ConnectFailActivity.this, "再试一次", Toast.LENGTH_SHORT).show();
                    finish();
                }
        );
    }
}
