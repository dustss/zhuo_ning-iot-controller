package zhuoning.youthlife.cn.zhuoning.device_activate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import zhuoning.youthlife.cn.zhuoning.R;

public class ConnectFailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_fail);
        findViewById(R.id.try_again_button).setOnClickListener(v ->
                Toast.makeText(ConnectFailActivity.this, "再试一次", Toast.LENGTH_SHORT).show()
        );
    }
}
