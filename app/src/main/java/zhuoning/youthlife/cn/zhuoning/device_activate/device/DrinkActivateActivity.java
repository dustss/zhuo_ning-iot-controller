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
public class DrinkActivateActivity extends Activity {

    @Bind(R.id.drink_yes)
    TextRing mDrinkYes;
    @Bind(R.id.drink_no)
    TextRing mDrinkNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate_drink);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.drink_yes, R.id.drink_no})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.drink_yes:
                startActivity(new Intent(this, ShareWifiActivity.class).putExtra("DeviceType", DeviceType.DRINK));
                break;
            case R.id.drink_no:
                finish();
                break;
        }
    }
}
