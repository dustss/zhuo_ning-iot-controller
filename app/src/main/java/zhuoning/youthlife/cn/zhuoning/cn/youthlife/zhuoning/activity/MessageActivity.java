package zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import zhuoning.youthlife.cn.zhuoning.R;
import zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.adapter.MessagesAdapter;

/**
 * Created by dusts on 2016-3-6.
 */
public class MessageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagelist);
        ListView mMessagesList = (ListView) findViewById(R.id.messages_list);

        String[] testMessages = {"哈哈你好", "say hello ~", "android"};
        mMessagesList.setAdapter(new MessagesAdapter(this,testMessages));

    }
}
