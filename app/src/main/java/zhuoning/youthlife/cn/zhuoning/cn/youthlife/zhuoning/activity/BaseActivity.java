package zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import zhuoning.youthlife.cn.zhuoning.R;
import zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.fragment.DMFragment;
import zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.fragment.UserFragment;
/*
    控制界面的主界面。
    上面的TITLE 和 下面的 ACTIONBAR
    中间是一个fragment

*/


public class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    private FragmentManager fragmentManager;
    private Fragment mDMFragment;
    private Fragment mUserFragment;
    private int mFragmentIndex;
    private ImageView mFunctionBtn;
    private TextView mTitleTv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Light);
        setContentView(R.layout.activity_main);

        initView();

        setTabSelection(0);

    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintColor(Color.parseColor("#FFFFFF"));
        tintManager.setStatusBarTintResource(R.color.white);//通知栏所需颜色

        fragmentManager = getSupportFragmentManager();

        findViewById(R.id.ll_chat).setOnClickListener(this);
        findViewById(R.id.ll_find).setOnClickListener(this);
        findViewById(R.id.ll_me).setOnClickListener(this);

        mFunctionBtn = (ImageView)findViewById(R.id.add);
        mFunctionBtn.setOnClickListener(Function);
        mTitleTv = (TextView) findViewById(R.id.title_txt);
    }


    private void setTabSelection(int index) {
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        mFragmentIndex = index;
        showTitleBar(mFragmentIndex);

        switch (index) {
            case 0:
                if (mDMFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    mDMFragment = new DMFragment();
                    transaction.add(R.id.content, mDMFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(mDMFragment);
                }
                break;
            case 1:
                break;
            case 2:
                if (mUserFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    mUserFragment = new UserFragment();
                    transaction.add(R.id.content, mUserFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(mUserFragment);
                }
                break;
        }

        transaction.commit();
    }

    private void showTitleBar(int index) {
        switch (index) {
            case 0:
                mTitleTv.setText("设备列表");
                mFunctionBtn.setVisibility(View.VISIBLE);

                ((ImageView)findViewById(R.id.iv_chat)).setImageResource(R.drawable.chat);
                ((TextView)findViewById(R.id.tv_chat)).setTextColor(Color.parseColor("#008000"));
                break;
            case 1:
                mTitleTv.setText("发现设备");
                mFunctionBtn.setVisibility(View.INVISIBLE);

                ((ImageView)findViewById(R.id.iv_find)).setImageResource(R.drawable.find);
                ((TextView)findViewById(R.id.tv_find)).setTextColor(Color.parseColor("#008000"));
                break;
            case 2:
                mTitleTv.setText("用户");
                mFunctionBtn.setVisibility(View.INVISIBLE);

                ((ImageView)findViewById(R.id.iv_me)).setImageResource(R.drawable.me);
                ((TextView)findViewById(R.id.tv_me)).setTextColor(Color.parseColor("#008000"));
                break;
        }
    }

    private void hideFragments(FragmentTransaction transaction) {
        ((ImageView)findViewById(R.id.iv_chat)).setImageResource(R.drawable.chat1);
        ((TextView)findViewById(R.id.tv_chat)).setTextColor(Color.parseColor("#A6A6A6"));

        ((ImageView)findViewById(R.id.iv_find)).setImageResource(R.drawable.find1);
        ((TextView)findViewById(R.id.tv_find)).setTextColor(Color.parseColor("#A6A6A6"));

        ((ImageView)findViewById(R.id.iv_me)).setImageResource(R.drawable.me1);
        ((TextView)findViewById(R.id.tv_me)).setTextColor(Color.parseColor("#A6A6A6"));

        if(mDMFragment != null) {
            transaction.hide(mDMFragment);
        }

        if (mUserFragment != null) {
            transaction.hide(mUserFragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_chat:
                setTabSelection(0);
                break;
            case R.id.ll_find:
                setTabSelection(1);
                break;
            case R.id.ll_me:
                setTabSelection(2);
                break;
        }
    }

    private View.OnClickListener Function = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (mFragmentIndex) {
                case 0:
                    Intent intent = new Intent(BaseActivity.this, DMActivateActivity.class);
                    startActivity(intent);
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
        }
    };

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
