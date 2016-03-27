package zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import zhuoning.youthlife.cn.zhuoning.R;

/**
 * Created by dusts on 2016-3-8.
 */
public class PlugAdapter extends BaseAdapter {


    private Context mContext;
    private String[] mPlugNames;
    private LayoutInflater mLayoutInflater;
    private boolean[] mPowerONOFF;

    public PlugAdapter(Context context, String[] plugNames, boolean[] PowerONOFF) {
        mContext = context;
        mPlugNames = plugNames;
        mPowerONOFF =PowerONOFF;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mPlugNames.length;
    }

    @Override
    public Object getItem(int i) {
        return mPlugNames[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = mLayoutInflater.inflate(R.layout.item_plug, null);
        TextView deviceName = (TextView) view.findViewById(R.id.device_name);
        deviceName.setText(mPlugNames[i]);
        ImageView power = (ImageView) view.findViewById(R.id.power);
        if(mPowerONOFF[i]) {
//            power.setBackgroundResource(R.drawable.power_on);
            power.setImageResource(R.drawable.power_on);
        }else{
//            power.setBackgroundResource(R.drawable.power_off);
            power.setImageResource(R.drawable.power_off);
        }

//        powerControl(view);

        return view;
    }

//    private void powerControl(View view) {
//        ImageView power = (ImageView) view.findViewById(R.id.power);
//
//        power.setFocusable(true);
//        power.setOnClickListener(v ->powerOnOff = !powerOnOff);
//
//        if (powerOnOff) {
//            power.setBackgroundResource(R.drawable.power_on);
//        } else if (!powerOnOff) {
//            power.setBackgroundResource(R.drawable.power_off);
//        }
//        power.invalidate();
//    }


}
