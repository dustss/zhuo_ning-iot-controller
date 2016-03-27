package zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import zhuoning.youthlife.cn.zhuoning.R;
import zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.SwipeMenuCreator.PlugSwipeMenu;
import zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.adapter.PlugAdapter;

/**
 * Created by dusts on 2016-3-8.
 * 智能插座的控制界面
 * 主要功能：
 *          控制：开关，定时开关，延时开关
 *          其他：重命名
 *
 */
public class PlugControllerActivity extends Activity implements SwipeMenuListView.OnMenuItemClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    //显示 当前在线的智能插座
    private SwipeMenuListView plugList;
    private boolean[] mPowerOnOff;
    private PlugAdapter mPlugAdapter;
    private String[] mDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controller_plug);
        initView();

        //这些信息以后都是在服务器上获取，留待 服务器接口
        mDevices = new String[]{"插座1", "插座2", "冰箱", "微波炉"};
        mPowerOnOff = new boolean[]{true, false, false, true};

        mPlugAdapter = new PlugAdapter(this, mDevices, mPowerOnOff);
        plugList.setAdapter(mPlugAdapter);
        plugList.setOnMenuItemClickListener(this);
        plugList.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        plugList.setMenuCreator(new PlugSwipeMenu(this));

        plugList.setOnItemClickListener(this);
        plugList.setOnItemLongClickListener(this);
    }

    private void initView() {
//        TextView title = (TextView) findViewById(R.id.title_txt);
//        title.setText(getIntent().getExtras().get("Title").toString());
//        title.setTextColor(Color.parseColor("#ffffff"));
        plugList = (SwipeMenuListView) findViewById(R.id.plug_list);
    }





    @Override
    public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
        switch (index) {
            case 0:
                Toast.makeText(this, "设置时间", Toast.LENGTH_SHORT).show();
                // setTime
                break;
            case 1:
                Toast.makeText(this, "设置延时", Toast.LENGTH_SHORT).show();
                // delay

                break;
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        mPowerOnOff[i] = !mPowerOnOff[i];
        plugList.setAdapter(mPlugAdapter);
        mPlugAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        EditText reNameText =new EditText(this);
        reNameText.setText(mDevices[position]);

        new AlertDialog.Builder(this).setTitle("您要重命名该插座吗？").setIcon(
                android.R.drawable.ic_dialog_info).setView(
                reNameText).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TextView deviceName = (TextView) view.findViewById(R.id.device_name);
                deviceName.setText(reNameText.getText());
                mDevices[position] =  reNameText.getText().toString();

                plugList.setAdapter(mPlugAdapter);
                mPlugAdapter.notifyDataSetChanged();
            }
        })
                .setNegativeButton("取消", null).show();


        return true;
    }
}
