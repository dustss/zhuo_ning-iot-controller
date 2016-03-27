package zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import zhuoning.youthlife.cn.zhuoning.R;

/**
 * Created by dusts on 2016-3-5.
 */
public class RegActivity extends Activity  {

    private EditText createName;
    private EditText creatPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        TextView backButton = (TextView) findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> finish());

        createName = (EditText) findViewById(R.id.creat_account_name);
        creatPass = (EditText) findViewById(R.id.creat_account_pass);

    /*  POST /service.asmx/Register HTTP/1.1
        Host: 121.42.171.234
        Content-Type: application/x-www-form-urlencoded
        Content-Length: length
        UserName=string&Password=string
    */
        findViewById(R.id.creat_account_button).setOnClickListener(view -> creatNewAccount());

    }

    private void creatNewAccount( ) {
        AsyncHttpClient client = new AsyncHttpClient();

        String url = "http://121.42.171.234:8089/service.asmx/Register";

        RequestParams params = new RequestParams();
        params.put("UserName", createName.getText().toString().trim());
        params.put("Password",creatPass.getText().toString().trim());

        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(RegActivity.this, "注册成功", Toast.LENGTH_SHORT);
                startActivity(new Intent(RegActivity.this, BaseActivity.class));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(RegActivity.this, "注册失败", Toast.LENGTH_SHORT);
            }
        });
    }
}
