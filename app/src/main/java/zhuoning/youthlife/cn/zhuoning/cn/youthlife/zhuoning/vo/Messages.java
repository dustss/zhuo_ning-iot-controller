package zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.vo;

/**
 * Created by dusts on 2016-3-6.
 */
public class Messages {
    int fromUserId;
    String message;
    String fromUserName;

    public Messages(int fromUserId, String message, String fromUserName) {
        this.fromUserId = fromUserId;
        this.message = message;
        this.fromUserName = fromUserName;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }
}
