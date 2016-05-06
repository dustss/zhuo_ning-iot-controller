package zhuoning.youthlife.cn.zhuoning.device_activate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import zhuoning.youthlife.cn.zhuoning.R;
import zhuoning.youthlife.cn.zhuoning.device_activate.result.ConnectFailActivity;
import zhuoning.youthlife.cn.zhuoning.device_activate.result.ConnectSucceedActivity;
import zhuoning.youthlife.cn.zhuoning.esptouch_wifi.EsptouchTask;
import zhuoning.youthlife.cn.zhuoning.esptouch_wifi.IEsptouchListener;
import zhuoning.youthlife.cn.zhuoning.esptouch_wifi.IEsptouchResult;
import zhuoning.youthlife.cn.zhuoning.esptouch_wifi.IEsptouchTask;
import zhuoning.youthlife.cn.zhuoning.esptouch_wifi.task.__IEsptouchTask;
import zhuoning.youthlife.cn.zhuoning.esptouch_wifi.wifi_admin.EspWifiAdminSimple;
import zhuoning.youthlife.cn.zhuoning.vo.DeviceType;
import zhuoning.youthlife.cn.zhuoning.vo.ResponseKey;
import zhuoning.youthlife.cn.zhuoning.vo.URL;

import static zhuoning.youthlife.cn.zhuoning.utils.network.RequestParam.pActivateDevice;

/**
 *  share wifi
 *  ESP touch Task
 */
public class ShareWifiActivity extends Activity {


    public static final String WIFI_SHARE = "wifi_share";
    private static final String TAG = "ShareWifiActivity";
    @Bind(R.id.wifi_ssid_text_view)
    TextView mWifiSSIDTextView;
    @Bind(R.id.wifi_password_edit_text)
    EditText mWifiPasswordEditText;
    @Bind(R.id.share_wifi_button)
    Button mShareWifiButton;
    private EspWifiAdminSimple mWifiAdmin;
    private String mBSSID;
    private SharedPreferences mWifiSharedPreference;
    private String mAP_ssid;
    private String mAP_pass;
    private IEsptouchListener myListener = this::onEsptoucResultAddedPerform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);

        setContentView(R.layout.activity_wifi_share);
        ButterKnife.bind(this);

        mWifiAdmin = new EspWifiAdminSimple(this);
        mWifiSharedPreference = getSharedPreferences(WIFI_SHARE, MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // display the connected ap's ssid
        mAP_ssid = mWifiAdmin.getWifiConnectedSsid();
        if (mAP_ssid != null) {
            mWifiSSIDTextView.setText("您连接的wifi为:" + mAP_ssid);
        } else {
            mWifiSSIDTextView.setText("请您先连接wifi");
        }
        // check whether the wifi is connected
        mShareWifiButton.setEnabled(!TextUtils.isEmpty(mAP_ssid));
        mAP_pass = mWifiSharedPreference.getString(mAP_ssid, "");
        mWifiPasswordEditText.setText(mAP_pass);
    }

    @OnClick(R.id.share_wifi_button)
    public void onClick() {
        mAP_pass = mWifiPasswordEditText.getText().toString().trim();
        String apBssid = mWifiAdmin.getWifiConnectedBssid();
        store2SharedPreference();
        new AsyncEsptouchTask().execute(mAP_ssid, apBssid, mAP_pass);
    }

    private void store2SharedPreference() {
        SharedPreferences.Editor editor = mWifiSharedPreference.edit();
        editor.putString(mAP_ssid, mAP_pass);
        editor.apply();
    }

    private void activate() {
        //start a client
        AsyncHttpClient client = new AsyncHttpClient();
        //set cookie
        client.setCookieStore(new PersistentCookieStore(ShareWifiActivity.this));
        //set params
        int deviceType = getIntent().getIntExtra("DeviceType", DeviceType.DEFAULT);
        if (deviceType == DeviceType.DEFAULT) {
            Toast.makeText(ShareWifiActivity.this, "设备类型获取错误", Toast.LENGTH_SHORT).show();
            finish();
        }
        RequestParams params = pActivateDevice(mBSSID, "我的设备...", deviceType);
        //post
        client.post(URL.DEVICE_ACTIVATE, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.getBoolean(ResponseKey.STATUS)) {
                        Toast.makeText(ShareWifiActivity.this, "激活成功", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ShareWifiActivity.this, ConnectSucceedActivity.class));
                    } else {
                        Toast.makeText(ShareWifiActivity.this, "激活失败，请检查激活的设备类型", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ShareWifiActivity.this, ConnectFailActivity.class));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(ShareWifiActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ShareWifiActivity.this, ConnectFailActivity.class));
            }
        });
    }

    private void onEsptoucResultAddedPerform(final IEsptouchResult result) {
        runOnUiThread(() -> {
            //获取了硬件的mac 然后 弹出toast 提示连接成功
            String text = result.getBssid() + " is connected to the wifi";
            Toast.makeText(ShareWifiActivity.this, text, Toast.LENGTH_LONG).show();
        });
    }

        private class AsyncEsptouchTask extends AsyncTask<String, Void, IEsptouchResult> {

            // without the lock, if the user tap confirm and cancel quickly enough,
            // the bug will arise. the reason is follows:
            // 0. task is starting created, but not finished
            // 1. the task is cancel for the task hasn't been created, it do nothing
            // 2. task is created
            // 3. Oops, the task should be cancelled, but it is running
            private final Object mLock = new Object();
            //a dialog to show progress
            private ProgressDialog mProgressDialog;
            //basic Esptouch Task
            private IEsptouchTask mEsptouchTask;

        @Override
        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(ShareWifiActivity.this);
            mProgressDialog
                    .setMessage("Esptouch is configuring, please wait for a moment...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setOnCancelListener(dialog -> {
                synchronized (mLock) {
                    if (__IEsptouchTask.DEBUG) {
                        Log.i(TAG, "progress dialog is canceled");
                    }
                    if (mEsptouchTask != null) {
                        mEsptouchTask.interrupt();
                    }
                }
            });
            mProgressDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                    "Waiting...", (dialog, which) -> {
                    });
            mProgressDialog.show();
            mProgressDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                    .setEnabled(false);
        }

        @Override
        protected IEsptouchResult doInBackground(String... params) {

            synchronized (mLock) {
                String apSsid = params[0];
                String apBssid = params[1];
                String apPassword = params[2];

                // EsptouchTask to send wifi ssid & password
                // the info of ap ssid,mac,pass,   isSSIDHidden,  the number of task
                mEsptouchTask = new EsptouchTask(apSsid, apBssid, apPassword,false, ShareWifiActivity.this);
                mEsptouchTask.setEsptouchListener(myListener);
            }
            return mEsptouchTask.executeForResult();
        }

        @Override
        protected void onPostExecute(IEsptouchResult result) {
            Button confirm = mProgressDialog.getButton(DialogInterface.BUTTON_POSITIVE);
            confirm.setEnabled(true);
            confirm.setText("确认");
            // check whether the task is cancelled and no results received
            if (!result.isCancelled()) {

                if (result.isSuc()) {
                    mBSSID = result.getBssid();
                    mProgressDialog.setMessage("Esptouch success, bssid = "+ mBSSID);
                    activate();
                } else {
                    mProgressDialog.setMessage("请重置设备状态后，重新分享wifi");
                }
            }
        }
    }
}
