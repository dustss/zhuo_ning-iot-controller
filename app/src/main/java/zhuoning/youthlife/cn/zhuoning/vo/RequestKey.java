package zhuoning.youthlife.cn.zhuoning.vo;

/**
 * Created by dusts on 2016/4/3.
 */
public class RequestKey {

    public static final class Login{
        public static final String USER_NAME = "UserName";
        public static final String PASSWORD = "Password";
        public static final String SESSION_TIMEOUT = "SessionTimeout";
    }

    public static final class SendVerify{
        public static final String PHONE = "phone";

    }
    public static final class RegisterPhone{
        public static final String PHONE = "phone";
        public static final String PASSWORD = "Password";
        public static final String VERIFY = "verify";
    }
    public static final class FindPassword{
        public static final String PHONE = "phone";
        public static final String PASSWORD = "newPassword";
        public static final String VERIFY = "verify";
    }


    public static final class DeviceActivated {
        public static final String DEVICE_SN = "DeviceSN";
        public static final String DEVICE_NAME = "DeviceName";
        public static final String DEVICE_TYPE = "DeviceType";
    }

    public static final class GetDeviceInfo {
        public static final String DEVICE_ID = "DeviceID";
    }


    public static final class SendCmdToDevice {
        public static final String DEVICE_ID = "DeviceID";
        public static final String CMD = "Cmd";
        public static final String FUNC = "Func";
        public static final String CMD_MEMO = "CmdMemo";
        public static final String TIMESTAMP = "timestamp";
    }

    public static final class UnbindDevice {
        public static final String DEVICE_ID = "DeviceID";
    }

    public static final class UpdateDeviceInfo {
        public static final String DEVICE_ID = "DeviceID";
        public static final String DEVICE_NAME = "DeviceName";
    }

    public static final class Feedback {
        public static final String CONTENT = "content";
    }
}
