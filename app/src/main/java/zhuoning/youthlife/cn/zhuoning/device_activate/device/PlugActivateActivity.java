package zhuoning.youthlife.cn.zhuoning.device_activate.device;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuoning.youthlife.cn.zhuoning.R;
import zhuoning.youthlife.cn.zhuoning.device_activate.ShareWifiActivity;
import zhuoning.youthlife.cn.zhuoning.vo.DeviceType;
import zhuoning.youthlife.cn.zhuoning.widget.TextRing;

/**
 * Created by dusts on 2016/4/2.
 */
public class PlugActivateActivity extends Activity {

    @Bind(R.id.plug_no)
    TextRing mPlugNo;
    @Bind(R.id.plug_yes)
    TextRing mPlugYes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate_plug);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.plug_no, R.id.plug_yes})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.plug_no:
                finish();
                break;
            case R.id.plug_yes:
                startActivity(new Intent(this, ShareWifiActivity.class).putExtra("DeviceType", DeviceType.PLUG));
                break;
        }
    }
}
