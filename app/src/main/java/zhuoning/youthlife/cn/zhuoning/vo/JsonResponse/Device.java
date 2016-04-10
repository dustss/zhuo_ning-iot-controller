package zhuoning.youthlife.cn.zhuoning.vo.JsonResponse;

/**
 * Created by dusts on 2016/4/3.
 */
public class Device {
    public static final String OFF_LINE = "0";
    public static final String ON_LINE = "1";
    /**
     * ID : 6
     * DName : littlekali
     * DType : 2
     * Stat : 0
     */
    private String ID;
    private String DName;
    private String DType;
    private String Stat;

    public Device(String ID, String DName, String DType, String stat) {
        this.ID = ID;
        this.DName = DName;
        this.DType = DType;
        Stat = stat;
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
