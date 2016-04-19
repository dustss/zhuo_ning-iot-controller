package zhuoning.youthlife.cn.zhuoning.vo.JsonResponse;

import java.util.List;

/**
 * Created by dusts on 2016/4/19. 20
 */
public class GetDevicePort {

    /**
     * status : true
     * info : [
     * {"pid":"1","DeviceID":"4","name":"卧室","detail":"卧室啊！","func":"1"},
     * {"pid":"2","DeviceID":"4","name":"客厅","detail":"啊啊","func":"2"},
     * {"pid":"3","DeviceID":"4","name":"餐厅","detail":"试试","func":"3"},
     * {"pid":"4","DeviceID":"4","name":"浴室","detail":"大大大","func":"4"}
     * ]
     */

    private boolean status;
    /**
     * pid : 1
     * DeviceID : 4
     * name : 卧室
     * detail : 卧室啊！
     * func : 1
     */

    private List<PlugInfo> info;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<PlugInfo> getInfo() {
        return info;
    }

    public void setInfo(List<PlugInfo> info) {
        this.info = info;
    }

    public static class PlugInfo {
        private String pid;
        private String DeviceID;
        private String name;
        private String detail;
        private String func;

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getDeviceID() {
            return DeviceID;
        }

        public void setDeviceID(String DeviceID) {
            this.DeviceID = DeviceID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getFunc() {
            return func;
        }

        public void setFunc(String func) {
            this.func = func;
        }
    }
}
