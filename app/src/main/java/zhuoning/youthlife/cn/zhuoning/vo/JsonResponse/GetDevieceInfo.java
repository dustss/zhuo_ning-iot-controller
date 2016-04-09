package zhuoning.youthlife.cn.zhuoning.vo.JsonResponse;

import java.util.List;

/**
 * Created by dusts on 2016/4/3.
 */
public class GetDevieceInfo {

    /**
     * status : true
     * info : [{"TaskID":"1","Func":"1","CmdType":"1","CmdMemo":"1","ExecDTFormat":"1970-01-01 08:00:01","ExecDT":"1"}]
     */

    private boolean status;
    private List<DeviceTask> info;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<DeviceTask> getInfo() {
        return info;
    }

    public void setInfo(List<DeviceTask> info) {
        this.info = info;
    }

}
