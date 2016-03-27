package zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import zhuoning.youthlife.cn.zhuoning.R;

/**
 * Created by dusts on 2016-3-6.
 */

public class MessagesAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    String[] mMessages;

    public MessagesAdapter(Context context,String[] messages) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMessages = messages;
    }

    @Override
    public int getCount() {
        return mMessages.length;
    }

    @Override
    public Object getItem(int position) {
        return mMessages[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = mLayoutInflater.inflate(R.layout.item_message,null);
        //在这里 填充 获取到的消息，头像，用户名
        TextView tv =(TextView)view.findViewById(R.id.message);
        tv.setText(mMessages[position]);

        return view;

    }

}
