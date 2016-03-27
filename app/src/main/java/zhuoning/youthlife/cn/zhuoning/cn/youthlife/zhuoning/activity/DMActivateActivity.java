package zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zhuoning.youthlife.cn.zhuoning.R;
import zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.DAO.SmartDevice;

/**
 * 点击+号后弹出的界面
 * 界面上显示的是待激活设备的类型选项的listview
 * 重写 listview  item的点击事件  选择激活相应的设备
 *
 */

public class DMActivateActivity  extends Activity{
    private List<SmartDevice> mPreDeviceList;
    private PreDeviceAdapter mPDA;
    private ListView mPDLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dm_pre_activate);

        mPDLv = (ListView) findViewById(R.id.prelist);
        getDMListDemo();
    }

    class PreDeviceAdapter extends ArrayAdapter<SmartDevice> {
        private List<SmartDevice> list;
        private int resource;

        public PreDeviceAdapter(Context context, int resource, List<SmartDevice> list) {
            super(context, resource, list);
            this.list = list;
            this.resource = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SmartDevice smartDevice = list.get(position);

            if(convertView == null) {
                convertView = LayoutInflater.from(DMActivateActivity.this).inflate(resource, null);
            }

            TextView textView = (TextView) convertView.findViewById(R.id.dname);
            textView.setText(smartDevice.getName());

            ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
            switch (smartDevice.getType()) {
                case 0:
                    icon.setImageResource(R.drawable.light);
                    break;
                case 1:
                    icon.setImageResource(R.drawable.chazuo);
                    break;
                case 2:
                    icon.setImageResource(R.drawable.water);
                    break;
                case 3:
                    icon.setImageResource(R.drawable.chuanglian);
                    break;
            }

            return convertView;
        }
    }

    private void getDMListDemo() {
        mPreDeviceList = new ArrayList<SmartDevice>();

        for (int i=0; i < 4; i++) {
            SmartDevice smartDevice = new SmartDevice();
            smartDevice.setID(i+1);
            smartDevice.setName("测试设备" + i);
            smartDevice.setType(i);
            smartDevice.setStat(1);

            mPreDeviceList.add(smartDevice);
        }

        mPDA = new PreDeviceAdapter(this, R.layout.dm_pre_item, mPreDeviceList);
        mPDLv.setAdapter(mPDA);
        mPDLv.setOnItemClickListener(activate);
    }

    private AdapterView.OnItemClickListener activate = new AdapterView.OnItemClickListener() {


        //激活设备需要重写这个方法。

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(DMActivateActivity.this, "激活"+mPreDeviceList.get(position).getName(), Toast.LENGTH_SHORT).show();
        }
    };
}
