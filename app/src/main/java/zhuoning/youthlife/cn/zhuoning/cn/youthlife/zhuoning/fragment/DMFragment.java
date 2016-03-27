package zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import zhuoning.youthlife.cn.zhuoning.R;
import zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.DAO.SmartDevice;
import zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.controller.Curtain2ControllerActivity;
import zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.controller.DrinkControllerActivity;
import zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.controller.LightControllerActivity;
import zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.controller.PlugControllerActivity;
import zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.utils.StringUtils;

/**
 * Created by Andy on 16/1/12.
 */
public class DMFragment extends Fragment{
    private List<SmartDevice> mDMList;
    private ListView mDMLv;
    private Activity mContext;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View DMView = inflater.inflate(R.layout.dm_fragment, container, false);
        mDMLv = (ListView)DMView.findViewById(R.id.dm_list);
        mDMLv.setOnItemClickListener(mDMClickListener);

        getDMListDemo();

        return DMView;
    }

    private AdapterView.OnItemClickListener mDMClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    };

    private void getDMListDemo() {
        mDMList = new ArrayList<SmartDevice>();

        for (int i=0; i < 4; i++) {
            SmartDevice smartDevice = new SmartDevice();
            smartDevice.setID(i+1);
            smartDevice.setName("测试设备" + i);
            smartDevice.setType(i);
            smartDevice.setStat(1);

            mDMList.add(smartDevice);
        }

        DMListAdapter adapter = new DMListAdapter(mContext, R.layout.dm_list_item, mDMList);
        mDMLv.setAdapter(adapter);
        mDMLv.setOnItemClickListener(dmcontrol);
    }

    private void getDMList() {
        mDMList = new ArrayList<SmartDevice>();

        AjaxParams params = new AjaxParams();
        params.put("UserID", "2");
        params.put("keyword","zning");

        FinalHttp finalHttp = new FinalHttp();
        finalHttp.post("http://121.42.171.234:8089/Service.asmx/GetDeviceList", params, new AjaxCallBack<String>() {
            //http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);

                s = StringUtils.formatString(s);
                if (s.isEmpty()) {
                    Toast.makeText(DMFragment.this.mContext, "亲，网络开小差了。。。", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    JSONObject resultJson = new JSONObject(s);
                    String ret_sn = resultJson.getString("Status");
                    if (null == ret_sn ||
                            "ERROR".equals(ret_sn)) {
                        Toast.makeText(DMFragment.this.mContext, resultJson.getString("Msg"), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    mDMList = new Gson().fromJson(resultJson.getString("Data"),
                            new TypeToken<ArrayList<SmartDevice>>() {
                            }.getType());

                    DMListAdapter adapter = new DMListAdapter(mContext, R.layout.dm_list_item, mDMList);
                    mDMLv.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                Toast.makeText(DMFragment.this.mContext, strMsg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean isProgress() {
                return super.isProgress();
            }
        });

        /*SmartDevice smartDevice = new SmartDevice();
        smartDevice.setID(1);
        smartDevice.setName("测试设备");
        smartDevice.setType(1);
        smartDevice.setStat(0);

        mDMList.add(smartDevice);

        DMListAdapter adapter = new DMListAdapter(mContext, R.layout.dm_list_item, mDMList);
        mDMLv.setAdapter(adapter);*/
    }

    class DMListAdapter extends ArrayAdapter<SmartDevice> {
        private List<SmartDevice> list;
        private int resource;

        public DMListAdapter(Context context, int resource, List<SmartDevice> list) {
            super(context, resource, list);
            this.resource = resource;
            this.list = list;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            SmartDevice smartDevice = list.get(position);

            if(view == null) {
                view = LayoutInflater.from(DMFragment.this.mContext).inflate(resource, null);
            }

            TextView textView = (TextView)view.findViewById(R.id.dname);
            textView.setText(smartDevice.getName());
            textView = (TextView)view.findViewById(R.id.dstat);
            switch (smartDevice.getStat()) {
                case 0:
                    textView.setText("离线");
                    break;
                case 1:
                default:
                    textView.setText(R.string.stat_online);
                    break;
            }

            ImageView icon = (ImageView) view.findViewById(R.id.icon);
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

            return view;
        }
    }

    private AdapterView.OnItemClickListener dmcontrol = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            //设备页面跳转到控制页面
            switch (position)
            {
                case 0:
                    startActivity(new Intent(mContext, LightControllerActivity.class));
                    break;
                case 1:
                    Intent i = new Intent(mContext, PlugControllerActivity.class);
                    i.putExtra("Title", mDMList.get(position).getName());
                    startActivity(i);
                    break;
                case 2:
                    startActivity(new Intent(mContext, DrinkControllerActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(mContext, Curtain2ControllerActivity.class));
                    break;
            }

        }
    };
}
