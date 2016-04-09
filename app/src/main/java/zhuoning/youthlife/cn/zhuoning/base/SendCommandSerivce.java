package zhuoning.youthlife.cn.zhuoning.base;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import zhuoning.youthlife.cn.zhuoning.vo.RequestKey;
import zhuoning.youthlife.cn.zhuoning.vo.URL;

/**
 * Created by dusts on 2016/4/7.
 */

public class SendCommandSerivce {
    private AsyncHttpClient mAsyncHttpClient;
    private RequestParams mParams;
    private Context mContext;
//    private boolean mResult;

    public SendCommandSerivce(Context context) {
        mContext = context;
        mAsyncHttpClient=new AsyncHttpClient();
        mParams = new RequestParams();
//        mResult = false;
    }

    public void sendCommand(String DeviceId,String Cmd,String Func,String CmdMemo,String timestamp ) {

        mParams.add(RequestKey.SendCmdToDevice.DEVICE_ID, DeviceId);
        mParams.add(RequestKey.SendCmdToDevice.CMD, Cmd);
        mParams.add(RequestKey.SendCmdToDevice.FUNC, Func);
        mParams.add(RequestKey.SendCmdToDevice.CMD_MEMO, CmdMemo);
        mParams.add(RequestKey.SendCmdToDevice.TIMESTAMP, timestamp);
        mAsyncHttpClient.post(URL.DEVICE_SEND_CMD, mParams, new SimpleJsonHandler(mContext));
    }

}
