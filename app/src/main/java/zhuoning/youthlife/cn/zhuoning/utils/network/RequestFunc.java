package zhuoning.youthlife.cn.zhuoning.utils.network;

import com.loopj.android.http.RequestParams;

import zhuoning.youthlife.cn.zhuoning.vo.RequestKey;

/**
 * Created by dusts on 2016/4/7.
 */

public class RequestFunc {


    public static RequestParams pSendCommand(String DeviceId,String Cmd,String Func,String CmdMemo,String timestamp ) {
        RequestParams params = new RequestParams();
        params.put(RequestKey.SendCmdToDevice.DEVICE_ID, DeviceId);
        params.put(RequestKey.SendCmdToDevice.CMD, Cmd);
        params.put(RequestKey.SendCmdToDevice.FUNC, Func);
        params.put(RequestKey.SendCmdToDevice.CMD_MEMO, CmdMemo);
        params.put(RequestKey.SendCmdToDevice.TIMESTAMP, timestamp);
        return params;
    }

    public static RequestParams pActivateDevice(String SN, String DeviceName, int DeviceType) {
        RequestParams params = new RequestParams();
        params.put(RequestKey.DeviceActivated.DEVICE_SN, SN);          //  change to mac id
        params.put(RequestKey.DeviceActivated.DEVICE_NAME, DeviceName);
        params.put(RequestKey.DeviceActivated.DEVICE_TYPE, String.valueOf(DeviceType));
        return params;
    }
    public static RequestParams pGetDeviceInfo(String DeviceId){
        RequestParams params = new RequestParams();
        params.put(RequestKey.GetDeviceInfo.DEVICE_ID, DeviceId);
        return params;
    }

    public static RequestParams pLogin(String name, String pass) {
        RequestParams params = new RequestParams();
        params.put(RequestKey.Login.USER_NAME, name);
        params.put(RequestKey.Login.PASSWORD, pass);
        params.put(RequestKey.Login.SESSION_TIMEOUT, "3000");
        return params;
    }

    public static RequestParams pRegister(String phonenumber, String password, String verification) {
        RequestParams params = new RequestParams();
        params.put(RequestKey.RegisterPhone.PHONE, phonenumber);
        params.put(RequestKey.RegisterPhone.VERIFY, verification);
        params.put(RequestKey.RegisterPhone.PASSWORD, password);
        return params;
    }

    public static RequestParams pForgetPass(String phonenumber, String password, String verification){
        RequestParams params = new RequestParams();
        params.put(RequestKey.FindPassword.PHONE, phonenumber);
        params.put(RequestKey.FindPassword.VERIFY, verification);
        params.put(RequestKey.FindPassword.PASSWORD, password);
        return params;
    }

}
