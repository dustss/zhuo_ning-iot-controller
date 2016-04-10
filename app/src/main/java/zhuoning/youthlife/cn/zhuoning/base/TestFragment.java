package zhuoning.youthlife.cn.zhuoning.base;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import zhuoning.youthlife.cn.zhuoning.R;
import zhuoning.youthlife.cn.zhuoning.utils.network.SimpleJsonHandler;
import zhuoning.youthlife.cn.zhuoning.vo.DeviceType;
import zhuoning.youthlife.cn.zhuoning.vo.JsonResponse.GetDeviceList;
import zhuoning.youthlife.cn.zhuoning.vo.JsonResponse.GetDevieceInfo;
import zhuoning.youthlife.cn.zhuoning.vo.RequestKey;
import zhuoning.youthlife.cn.zhuoning.vo.URL;

import static zhuoning.youthlife.cn.zhuoning.utils.network.RequestFunc.pActivateDevice;
import static zhuoning.youthlife.cn.zhuoning.utils.network.RequestFunc.pGetDeviceInfo;

/**
 *
 * Created by dusts on 2016/4/3.
 *
 */

public class TestFragment extends Fragment {

    @Bind(R.id.Register)
    Button mRegister;
    @Bind(R.id.Login)
    Button mLogin;
    @Bind(R.id.DeviceActivated)
    Button mDeviceActivated;
    @Bind(R.id.GetDeviceList)
    Button mGetDeviceList;
    @Bind(R.id.GetDeviceInfo)
    Button mGetDeviceInfo;
    @Bind(R.id.SendCmdToDevice)
    Button mSendCmdToDevice;
    @Bind(R.id.SendTimerCmdToDevice)
    Button mSendTimerCmdToDevice;
    @Bind(R.id.UnbindDevice)
    Button mUnbindDevice;
    @Bind(R.id.UpdateDeviceInfo)
    Button mUpdateDeviceInfo;
    private View mRootView;
    private AsyncHttpClient mClient;
    private Gson mGson;
    private GetDeviceList mDeviceList;

/*
*  不过目前首要任务还是硬件连接上共享WIFI，先解决这个问题，然后再进一步与服务器一起调试******************优先
*
*  目前完成了登录，注册的逻辑
*  接着是 获取列表 并且 显示 到主页上面
*  任务拆分：   获取列表  就是 DeviceInfo list 拿到list以后   对应的adapter 中 增加list字段
*  根据list中的设备类型，刷新 listview视图 ,这里可能考虑使用pull to refresh list
*  然后为  list 中的item 设置 相应设备类型的点击事件  发送相应的请求  反馈操作结果即可
*  在智能插座上的操作，也是类似的。
*  getDeviceList
*
*  还有一些小问题，比如定时 延时的时间设置 弹出窗口，智能照明的颜色捕捉
*
*/


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_test, container, false);

        ButterKnife.bind(this, mRootView);

        //异步http请求框架
        mClient = new AsyncHttpClient();
        mClient.setCookieStore(new PersistentCookieStore(getActivity()));   //设置保存cookie
        mGson = new Gson();
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.Register, R.id.Login, R.id.DeviceActivated, R.id.GetDeviceList, R.id.GetDeviceInfo, R.id.SendCmdToDevice, R.id.SendTimerCmdToDevice, R.id.UnbindDevice, R.id.UpdateDeviceInfo})
    public void onClick(View view) {
        RequestParams params = null;
        switch (view.getId()) {
            case R.id.Register:
                break;
            case R.id.Login:
                break;
            case R.id.DeviceActivated:
                params = pActivateDevice("5ccf7f05e570", "littlekali", DeviceType.PLUG);
                mClient.post(URL.DEVICE_ACTIVATE, params, new SimpleJsonHandler(getActivity()));
                break;
            case R.id.GetDeviceList:

                mClient.get(URL.DEVICE_LIST_GET, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        mDeviceList = mGson.fromJson(response.toString(), GetDeviceList.class);

                        if (mDeviceList.isStatus()) {
                            Toast.makeText(getActivity(), "获取json对象成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                );
                break;

            case R.id.GetDeviceInfo:

                String deviceId = mDeviceList.getInfo().get(0).getID();
                if (deviceId.isEmpty()) {
                    Toast.makeText(getActivity(), "参数错误，id为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                params = pGetDeviceInfo(deviceId);
                mClient.post(URL.DEVICE_INFO_GET, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        GetDevieceInfo getDevieceInfo = mGson.fromJson(response.toString(), GetDevieceInfo.class);

                        if (getDevieceInfo.isStatus()) {
                            if (getDevieceInfo.getInfo().get(0)!=null)
                            Toast.makeText(getActivity(), "获取json对象成功" + getDevieceInfo.getInfo().get(0).getExecDTFormat(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                break;
            case R.id.SendCmdToDevice:
                params = new RequestParams();
                params.add(RequestKey.SendCmdToDevice.CMD,"");
                params.add(RequestKey.SendCmdToDevice.CMD_MEMO,"");
                params.add(RequestKey.SendCmdToDevice.DEVICE_ID,"");
                params.add(RequestKey.SendCmdToDevice.FUNC,"");
                params.add(RequestKey.SendCmdToDevice.TIMESTAMP, "");
                mClient.post(URL.DEVICE_SEND_CMD, params, new SimpleJsonHandler(getActivity()));

                break;
            case R.id.SendTimerCmdToDevice:
                params = new RequestParams();
//        throw ....
                break;
            case R.id.UnbindDevice:
                params = new RequestParams();
                params.add(RequestKey.UnbindDevice.DEVICE_ID,"");
                mClient.post(URL.DEVICE_UNBIND, params, new SimpleJsonHandler(getActivity()));
                break;
            case R.id.UpdateDeviceInfo:
                params = new RequestParams();
                params.add(RequestKey.UpdateDeviceInfo.DEVICE_ID,"");
                params.add(RequestKey.UpdateDeviceInfo.DEVICE_NAME,"");
                mClient.post(URL.DEVICE_INFO_UPDATE, params, new SimpleJsonHandler(getActivity()));
                break;
        }
    }
}
