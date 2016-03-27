package zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zhuoning.youthlife.cn.zhuoning.R;
import zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.activity.ChangePassActivity;
import zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.activity.FeedbackActivity;
import zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.activity.LoginActivity;
import zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.activity.MessageActivity;

/**
 * Created by Andy on 16/2/27.
 */
public class UserFragment extends Fragment{
    private Context mContext;
    private TextView mNameView;
    private ListView mFunctionLv;
    private static final int MESSAGE = 0;
    private static final int FEEDBACK = 1;
    private static final int CHANGEPASS = 2;
    private static final int DELETESTATE = 3;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.usercenter, null);


        view.findViewById(R.id.slogo).setOnClickListener(v->startActivity(new Intent(mContext,LoginActivity.class)));

        mFunctionLv = (ListView) view.findViewById(R.id.functionlist);
        getFunctionList();

        mFunctionLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i)
                {
                    case MESSAGE:
                        startActivity(new Intent(mContext, MessageActivity.class));
                        break;
                    case FEEDBACK:
                        startActivity(new Intent(mContext, FeedbackActivity.class));
                        break;
                    case CHANGEPASS:
                        startActivity(new Intent(mContext, ChangePassActivity.class));
                        break;
                    case DELETESTATE:
                        Toast.makeText(mContext, "注销登录成功", Toast.LENGTH_LONG).show();
                        break;

                }
            }
        });
        return view;
    }

    class FunctionItem {
        private int id;
        private int resource;
        private String desc;
    }

    class FunctionItemAdapter extends ArrayAdapter<FunctionItem> {
        private int resource;
        private List<FunctionItem> list;

        public FunctionItemAdapter(Context context, int resource, List<FunctionItem> list) {
            super(context, resource, list);
            this.resource = resource;
            this.list = list;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final FunctionItem item = list.get(position);

            if(convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.user_func_item, null);
            }

            ImageView imageView = (ImageView) convertView.findViewById(R.id.icon);
            imageView.setImageResource(item.resource);

            TextView textView = (TextView) convertView.findViewById(R.id.func_name);
            textView.setText(item.desc);

            return convertView;
        }
    }

    private void getFunctionList() {
        if(mFunctionLv == null) {
            Toast.makeText(mContext, "未初始化列表", Toast.LENGTH_SHORT).show();
            return;
        }

        List<FunctionItem> list = new ArrayList<FunctionItem>();

        FunctionItem item = new FunctionItem();
        item.desc = "消息";
        item.id = 0;
        item.resource = R.drawable.msg;
        list.add(item);

        item = new FunctionItem();
        item.desc = "反馈";
        item.id = 1;
        item.resource = R.drawable.feedback;
        list.add(item);

        item = new FunctionItem();
        item.desc = "更改密码";
        item.id = 2;
        item.resource = R.drawable.changepwd;
        list.add(item);

        item = new FunctionItem();
        item.id = 3;
        item.desc = "注销";
        item.resource = R.drawable.logout;
        list.add(item);

        FunctionItemAdapter adapter = new FunctionItemAdapter(mContext, R.layout.user_func_item, list);

        mFunctionLv.setAdapter(adapter);
    }
}
