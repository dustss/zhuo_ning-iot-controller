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
public class CurtainActivateActivity extends Activity {


    @Bind(R.id.curtain_no)
    TextRing mCurtainNo;
    @Bind(R.id.curtain_yes)
    TextRing mCurtainYes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate_curtain);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.curtain_no, R.id.curtain_yes})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.curtain_no:
                finish();
                break;
            case R.id.curtain_yes:
                startActivity(new Intent(this, ShareWifiActivity.class).putExtra("DeviceType", DeviceType.CURTAIN));
                break;
        }
    }
}
