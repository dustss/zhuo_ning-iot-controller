package zhuoning.youthlife.cn.zhuoning.service_JsonResponseHandler;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import zhuoning.youthlife.cn.zhuoning.vo.ResponseKey;

/**
 * Created by dusts on 2016/4/3.
 */
public class GetDeviceInfoHandle extends JsonHttpResponseHandler {
    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);
        try {
            if (response.getBoolean(ResponseKey.STATUS)) {
                Log.d("JsonHttpResponseHandler", this.getTag() + "成功");
            } else {
                Log.d("JsonHttpResponseHandler", this.getTag() + "失败");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
