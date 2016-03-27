package zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import zhuoning.youthlife.cn.zhuoning.R;

/**
 * Created by dusts on 2016/3/20.
 */

public class LightControllerActivity extends Activity implements View.OnClickListener {

    private ImageView mLightOnOff;
    private boolean mLightState=false;
    private TextView mLightInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controller_light);
        initView();
    }

    private void initView() {
        mLightOnOff = (ImageView) findViewById(R.id.light_on_off);
        mLightOnOff.setOnClickListener(this);
        mLightInfo = (TextView) findViewById(R.id.light_state_info_text_view);
        mLightInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.light_on_off:
                if (mLightState){
                    Toast.makeText(LightControllerActivity.this, "关灯咯~", Toast.LENGTH_SHORT).show();
                    mLightOnOff.setImageResource(R.drawable.light_toggle_off);
                }else{
                    Toast.makeText(LightControllerActivity.this, "开灯", Toast.LENGTH_SHORT).show();
                    mLightOnOff.setImageResource(R.drawable.light_toggle_on);
                }
                mLightState = !mLightState;
                break;
            case R.id.light_state_info_text_view:
                mLightInfo.setText("18:00关  22：00开");
                Toast.makeText(LightControllerActivity.this, "灯光参数信息", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
