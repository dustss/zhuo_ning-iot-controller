package zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.nineoldandroids.view.ViewHelper;

import zhuoning.youthlife.cn.zhuoning.R;

/**
 * Created by dusts on 2016/3/16.
 */
public class DrinkControllerActivity extends Activity implements View.OnClickListener {


    private Button mColdButton;
    private Button mHotButton;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controller_drink);

        initViews();
        initEvents();

    }

    public void OpenRightMenu(View view) {
        mDrawerLayout.openDrawer(Gravity.RIGHT);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
                Gravity.RIGHT);
    }

    private void initEvents() {
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerStateChanged(int newState) {
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = mDrawerLayout.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                float rightScale = 0.8f + scale * 0.2f;


                ViewHelper.setTranslationX(mContent,
                        -mMenu.getMeasuredWidth() * slideOffset);
                ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
                ViewHelper.setPivotY(mContent,
                        mContent.getMeasuredHeight() / 2);
                mContent.invalidate();
                ViewHelper.setScaleX(mContent, rightScale);
                ViewHelper.setScaleY(mContent, rightScale);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mDrawerLayout.setDrawerLockMode(
                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
            }
        });
    }

    private void initViews() {
        mColdButton = (Button) findViewById(R.id.drink_cold_button);
        mHotButton = (Button) findViewById(R.id.drink_hot_button);
        mColdButton.setOnClickListener(this);
        mHotButton.setOnClickListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_drink);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.drink_cold_button:
                view.setBackgroundResource(R.drawable.cold_clicked);
                Toast.makeText(DrinkControllerActivity.this, "制冷Ing", Toast.LENGTH_SHORT).show();
                break;
            case R.id.drink_hot_button:
                view.setBackgroundResource(R.drawable.hot_clicked);
                Toast.makeText(DrinkControllerActivity.this, "加热Ing", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
