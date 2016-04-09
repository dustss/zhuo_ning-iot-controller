package zhuoning.youthlife.cn.zhuoning.vo.JsonResponse;

/**
 * Created by dusts on 2016/4/4.
 */
public class ActivateDevice {

    /**
     * status : true
     * info : {"DeviceID":"6"}
     */

    private boolean status;
    /**
     * DeviceID : 6
     */

    private InfoBean info;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        private String DeviceID;

        public String getDeviceID() {
            return DeviceID;
        }

        public void setDeviceID(String DeviceID) {
            this.DeviceID = DeviceID;
        }
    }
}
