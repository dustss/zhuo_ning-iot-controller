package zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.SwipeMenuCreator;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;

import zhuoning.youthlife.cn.zhuoning.R;

/**
 * Created by dusts on 2016-3-8.
 */
public class PlugSwipeMenu implements SwipeMenuCreator {

    private Context mContext;
    private SwipeMenuItem delayItem;
    private SwipeMenuItem setTimeItem;

    public PlugSwipeMenu(Context Context) {
        mContext = Context;
    }

    @Override
    public void create(SwipeMenu menu) {
        // create "定时" item
        setTimeItem = new SwipeMenuItem(
                mContext);
        // set item background
        setTimeItem.setBackground(new ColorDrawable(Color.parseColor("#9ac9f0")));
        // set item width
        setTimeItem.setWidth(dp2px(90));
        // set item title
        setTimeItem.setTitle("定时");
        // set item title fontsize
        setTimeItem.setTitleSize(18);
        // set item title font color
        setTimeItem.setTitleColor(Color.WHITE);
        // add to menu
        menu.addMenuItem(setTimeItem);

        // create "延时" item
        delayItem = new SwipeMenuItem(
                mContext);
        // set item background
        delayItem.setBackground(new ColorDrawable(Color.parseColor("#edc35e")));
        // set item width
        delayItem.setWidth(dp2px(90));
        // set item title
        delayItem.setTitle("延时");
        // set item title fontsize
        delayItem.setTitleSize(18);
        // set item title font color
        delayItem.setTitleColor(Color.WHITE);
        // add to menu
        menu.addMenuItem(delayItem);
    }

    public int dp2px(float dpVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, mContext.getResources().getDisplayMetrics());
    }

}
