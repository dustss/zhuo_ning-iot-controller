package zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.nineoldandroids.view.ViewHelper;

import zhuoning.youthlife.cn.zhuoning.R;

/**
 * Created by dusts on 2016/3/20.
 */

public class Curtain1ControllerActivity extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {


    private Button mSetTimeButton;
    private Button mDelayTimeButton;
    private RadioButton mOpen;
    private RadioButton mClose;
    private RadioButton mPause;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controller_curtain_single);
        initView();
        initEvent();
    }

    private void initView() {
        mSetTimeButton = (Button)findViewById   (R.id.button_set_time   );
        mDelayTimeButton = (Button) findViewById(R.id.button_delay_time );
        mOpen = (RadioButton) findViewById        (R.id.imageView_open    );
        mClose = (RadioButton) findViewById       (R.id.imageView_close   );
        mPause = (RadioButton) findViewById       (R.id.imageView_pause   );
        mRadioGroup =  (RadioGroup)findViewById(R.id.single_radio_group);
    }


    private void initEvent() {
        mSetTimeButton.setOnClickListener(this);
        mDelayTimeButton.setOnClickListener(this);

        mRadioGroup.setOnCheckedChangeListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_set_time  :
                Toast.makeText(Curtain1ControllerActivity.this,"设置定时" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_delay_time:
                Toast.makeText(Curtain1ControllerActivity.this,"设置延时" , Toast.LENGTH_SHORT).show();
                break;


        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getId()){
            case R.id.imageView_open   :
                Toast.makeText(Curtain1ControllerActivity.this,"开ing" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_close  :
                Toast.makeText(Curtain1ControllerActivity.this,"关ing" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_pause  :
                Toast.makeText(Curtain1ControllerActivity.this,"暂停ing" , Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
