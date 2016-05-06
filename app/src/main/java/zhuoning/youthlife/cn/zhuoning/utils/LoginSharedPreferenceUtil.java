package zhuoning.youthlife.cn.zhuoning.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dusts on 2016/4/25. 10
 */

public class LoginSharedPreferenceUtil {
    private Context mContext;
    private SharedPreferences LoginSharedpreference;

    public LoginSharedPreferenceUtil(Context context) {
        mContext = context;
        LoginSharedpreference = mContext.getSharedPreferences("lolologin", Context.MODE_PRIVATE);
    }

    public void updateNamePass2SP(String name, String pass) {
        SharedPreferences.Editor editor = LoginSharedpreference.edit();
        editor.putString("username", name.trim());
        editor.putString("password", pass.trim());
        editor.apply();
    }

    public String getName() {
        return LoginSharedpreference.getString("username", "");
    }

    public String getPass() {
        return LoginSharedpreference.getString("password", "");
    }

}
