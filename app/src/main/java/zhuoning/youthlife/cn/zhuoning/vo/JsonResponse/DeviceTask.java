package zhuoning.youthlife.cn.zhuoning.vo.JsonResponse;

/**
 * Created by dusts on 2016/4/3.
 */
public class DeviceTask {

    /**
     * TaskID : 1
     * Func : 1
     * CmdType : 1
     * CmdMemo : 1
     * ExecDTFormat : 1970-01-01 08:00:01
     * ExecDT : 1
     */

    private String TaskID;
    private String Func;
    private String CmdType;
    private String CmdMemo;
    private String ExecDTFormat;
    private String ExecDT;

    public String getTaskID() {
        return TaskID;
    }

    public void setTaskID(String TaskID) {
        this.TaskID = TaskID;
    }

    public String getFunc() {
        return Func;
    }

    public void setFunc(String Func) {
        this.Func = Func;
    }

    public String getCmdType() {
        return CmdType;
    }

    public void setCmdType(String CmdType) {
        this.CmdType = CmdType;
    }

    public String getCmdMemo() {
        return CmdMemo;
    }

    public void setCmdMemo(String CmdMemo) {
        this.CmdMemo = CmdMemo;
    }

    public String getExecDTFormat() {
        return ExecDTFormat;
    }

    public void setExecDTFormat(String ExecDTFormat) {
        this.ExecDTFormat = ExecDTFormat;
    }

    public String getExecDT() {
        return ExecDT;
    }

    public void setExecDT(String ExecDT) {
        this.ExecDT = ExecDT;
    }

}
