package zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import zhuoning.youthlife.cn.zhuoning.R;

/**
 * Created by dusts on 2016-3-5.
 */
public class ForgetPassActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);

        //返回
        TextView backButton = (TextView) findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> finish());
    }
}
