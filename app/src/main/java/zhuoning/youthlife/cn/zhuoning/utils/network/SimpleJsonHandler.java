package zhuoning.youthlife.cn.zhuoning.utils.network;

import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import zhuoning.youthlife.cn.zhuoning.vo.ResponseKey;

/**
 * Created by dusts on 2016/4/7.
 * 
 */
public class SimpleJsonHandler extends JsonHttpResponseHandler {
    private Context mContext;
    public SimpleJsonHandler(Context context) {
        super();
        mContext = context;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        try {
            if (response.getBoolean(ResponseKey.STATUS)) {
                Toast.makeText(mContext, "恭喜您，操作成功", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(mContext, "对不起，操作失败", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        Toast.makeText(mContext, "网络请求失败，请检查网络", Toast.LENGTH_SHORT).show();
    }
}
