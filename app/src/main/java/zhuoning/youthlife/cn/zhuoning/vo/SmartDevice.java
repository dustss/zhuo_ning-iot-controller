package zhuoning.youthlife.cn.zhuoning.vo;

/**
 * Created by machenike on 2016/4/11.
 */
public class SmartDevice {

    private int Type;
    private String DName;

    public SmartDevice(String DName, int Type) {
        this.DName = DName;
        this.Type = Type;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getName() {
        return DName;
    }

    public void setName(String name) {
        DName = name;
    }
}