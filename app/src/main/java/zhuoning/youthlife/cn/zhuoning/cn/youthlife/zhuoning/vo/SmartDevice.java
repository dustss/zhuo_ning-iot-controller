package zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.vo;

/**
 * Created by Andy on 16/1/12.
 */
public class SmartDevice {
    private int ID;
    private int Type;
    private int Stat;
    private String DName;

    public int getStat() {
        return Stat;
    }

    public void setStat(int stat) {
        Stat = stat;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
