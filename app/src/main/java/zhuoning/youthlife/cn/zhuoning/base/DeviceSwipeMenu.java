package zhuoning.youthlife.cn.zhuoning.base;

import android.content.Context;
import android.graphics.Color;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;

import zhuoning.youthlife.cn.zhuoning.R;

import static zhuoning.youthlife.cn.zhuoning.utils.LmcUtils.dp2px;

/**
 * Created by dusts on 2016/5/4. 17
 */
public class DeviceSwipeMenu implements SwipeMenuCreator {
    private Context mContext;

    public DeviceSwipeMenu(Context context) {
        mContext = context;
    }

    @Override
    public void create(SwipeMenu menu) {

        SwipeMenuItem setTimeItem = new SwipeMenuItem(mContext);
        // set item bacround
        setTimeItem.setBackground(R.color.red);
        // set item width
        setTimeItem.setWidth(dp2px(90, mContext));
        // set item title
        setTimeItem.setTitle("解绑");
        // set item title fontsize
        setTimeItem.setTitleSize(18);
        // set item title font color
        setTimeItem.setTitleColor(Color.WHITE);
        // add to menu
        menu.addMenuItem(setTimeItem);

    }
}
