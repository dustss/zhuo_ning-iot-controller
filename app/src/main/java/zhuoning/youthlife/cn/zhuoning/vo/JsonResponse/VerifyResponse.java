package zhuoning.youthlife.cn.zhuoning.vo.JsonResponse;

/**
 * Created by dusts on 2016/4/4.
 */
public class VerifyResponse {

    /**
     * {"status":true,"result":"success"}
     * status : true
     * result : success
     */

    private boolean status;
    private String result;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
