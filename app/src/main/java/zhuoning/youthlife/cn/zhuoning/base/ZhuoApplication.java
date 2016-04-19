package zhuoning.youthlife.cn.zhuoning.base;

import android.app.Application;

import im.fir.sdk.FIR;

/**
 * Created by dusts on 2016/4/19. 14
 */
public class ZhuoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FIR.init(this);
    }
}
