package zhuoning.youthlife.cn.zhuoning.vo.JsonResponse;

/**
 * ID : 6
 * DName : littlekali
 * DType : 2   0-3
 * Stat : 0 离线  1在线
 */
public class Device {
    public static final int OFF_LINE = 0;
    public static final int ON_LINE = 1;
    public static Device DEVICE_DEFAULT = new Device("8888", "default", "8888", "8888");

    private String ID;
    private String DName;
    private String DType;
    private String Stat;

    public Device(String ID, String DName, String DType, String Stat) {
        this.ID = ID;
        this.DName = DName;
        this.DType = DType;
        this.Stat = Stat;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDName() {
        return DName;
    }

    public void setDName(String DName) {
        this.DName = DName;
    }

    public String getDType() {
        return DType;
    }

    public void setDType(String DType) {
        this.DType = DType;
    }

    public String getStat() {
        return Stat;
    }

    public void setStat(String Stat) {
        this.Stat = Stat;
    }
}
