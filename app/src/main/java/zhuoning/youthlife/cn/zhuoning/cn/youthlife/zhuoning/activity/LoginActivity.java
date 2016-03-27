package zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import zhuoning.youthlife.cn.zhuoning.R;

/*
    登录的入口
*/

public class LoginActivity extends Activity{


    private EditText mLoginName;
    private EditText mLoginPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        //创建账户，跳转到注册页面。
        TextView createAccount =(TextView)findViewById(R.id.create_account_textview);
        createAccount.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this,RegActivity.class)));
        // 返回按钮
        TextView forgetPassTextView = (TextView) findViewById(R.id.forget_password_textview);
        forgetPassTextView.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this,ForgetPassActivity.class)));

        mLoginName = (EditText) findViewById(R.id.login_name);
        mLoginPass = (EditText) findViewById(R.id.login_pass);
        //登录按钮，  还没添加业务逻辑， 如果登录错误，则无法进入控制页面。
        // 这里先写好了跳转， 以后增加业务逻辑
        /*
        POST /service.asmx/Login HTTP/1.1
        Host: 121.42.171.234
        Content-Type: application/x-www-form-urlencoded
        Content-Length: length
        UserName=string&Password=string&SessionTimeout=string
        */
        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(view -> {
                    Toast.makeText(this,"正在登录...请稍等",Toast.LENGTH_SHORT).show();

                    AsyncHttpClient client = new AsyncHttpClient();
                    String url = "http://121.42.171.234:8089/service.asmx/Login";

                    RequestParams params = new RequestParams();
                    params.put("UserName",mLoginName.getText().toString().trim());
                    params.put("Password", mLoginPass.getText().toString().trim());
                    params.put("SessionTimeout", "5000");
                    RequestHandle requestHandle= client.post(url, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, BaseActivity.class));
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Toast.makeText(LoginActivity.this, "登录失败，请检查输入是否正确", Toast.LENGTH_SHORT).show();
                        }
                    });



                }

        );
    }
}
