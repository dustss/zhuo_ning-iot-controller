package zhuoning.youthlife.cn.zhuoning.vo.JsonResponse;

import java.util.List;

/**
 * Created by dusts on 2016/4/3.
 */
public class GetDeviceList {

    /**
     * status : true
     * info : [{"ID":"9","DName":"Haha2","DType":"2"},{"ID":"10","DName":"1494050031","DType":"1"},{"ID":"12","DName":"1778210494","DType":"4"}]
     */

    private boolean status;
    private List<DeviceInfo> info;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<DeviceInfo> getInfo() {
        return info;
    }

    public void setInfo(List<DeviceInfo> info) {
        this.info = info;
    }
}
