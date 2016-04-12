package zhuoning.youthlife.cn.zhuoning.vo.JsonResponse;

import java.util.List;

/**
 * Created by dusts on 2016/4/3.
 */
public class GetDeviceList {


    private boolean status;
    private List<Device> info;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Device> getInfo() {
        return info;
    }

    public void setInfo(List<Device> info) {
        this.info = info;
    }
}
