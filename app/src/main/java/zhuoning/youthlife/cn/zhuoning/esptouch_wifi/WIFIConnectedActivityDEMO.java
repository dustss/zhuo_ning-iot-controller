package zhuoning.youthlife.cn.zhuoning.esptouch_wifi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import zhuoning.youthlife.cn.zhuoning.R;
import zhuoning.youthlife.cn.zhuoning.device_activate.result.ConnectFailActivity;
import zhuoning.youthlife.cn.zhuoning.device_activate.result.ConnectSucceedActivity;
import zhuoning.youthlife.cn.zhuoning.esptouch_wifi.task.__IEsptouchTask;
import zhuoning.youthlife.cn.zhuoning.esptouch_wifi.wifi_admin.EspWifiAdminSimple;
import zhuoning.youthlife.cn.zhuoning.utils.network.SimpleJsonHandler;
import zhuoning.youthlife.cn.zhuoning.vo.RequestKey;
import zhuoning.youthlife.cn.zhuoning.vo.URL;

public class WIFIConnectedActivityDEMO extends Activity implements OnClickListener {

	private static final String TAG = "WIFIConnected...";
//text view to show  ssid
	private TextView mWifiSSIDTextView;
//edit text to input password
	private EditText mWifiPasswordEditText;

	private Button mShareWifiButton;

	private Switch mSwitchIsSsidHidden;

	private EspWifiAdminSimple mWifiAdmin;

	private Spinner mSpinnerTaskCount;
/*
* 		The BSSID is a 48bit identity used to identify a particular BSS (Basic Service Set)
* within an area.
* 		In Infrastructure BSS networks, the BSSID is the MAC (Medium Access Control)
* address of the AP (Access Point)
*
* */
	private String mBSSID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share_wifi);

		mWifiAdmin = new EspWifiAdminSimple(this);
		mWifiSSIDTextView = (TextView) findViewById(R.id.tvApSssidConnected);
		mWifiPasswordEditText = (EditText) findViewById(R.id.edtApPassword);
		mShareWifiButton = (Button) findViewById(R.id.btnConfirm);
		mSwitchIsSsidHidden = (Switch) findViewById(R.id.switchIsSsidHidden);
		mShareWifiButton.setOnClickListener(this);
		initSpinner();
	}
	
	private void initSpinner()
	{
		mSpinnerTaskCount = (Spinner) findViewById(R.id.spinnerTaskResultCount);
		int[] spinnerItemsInt = getResources().getIntArray(R.array.taskResultCount);
		int length = spinnerItemsInt.length;
		Integer[] spinnerItemsInteger = new Integer[length];
		for(int i=0;i<length;i++)
		{
			spinnerItemsInteger[i] = spinnerItemsInt[i];
		}
		ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
				android.R.layout.simple_list_item_1, spinnerItemsInteger);
		mSpinnerTaskCount.setAdapter(adapter);
		mSpinnerTaskCount.setSelection(1);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// display the connected ap's ssid
		String apSsid = mWifiAdmin.getWifiConnectedSsid();
		if (apSsid != null) {
			mWifiSSIDTextView.setText(apSsid);
		} else {
			mWifiSSIDTextView.setText("");
		}
		// check whether the wifi is connected
		boolean isApSsidEmpty = TextUtils.isEmpty(apSsid);
		mShareWifiButton.setEnabled(!isApSsidEmpty);
	}

	@Override
	public void onClick(View v) {

		if (v == mShareWifiButton) {
			String apSsid = mWifiSSIDTextView.getText().toString();
			String apPassword = mWifiPasswordEditText.getText().toString();
			String apBssid = mWifiAdmin.getWifiConnectedBssid();
			Boolean isSsidHidden = mSwitchIsSsidHidden.isChecked();
			String isSsidHiddenStr = "NO";
			String taskResultCountStr = Integer.toString(mSpinnerTaskCount
					.getSelectedItemPosition());
			if (isSsidHidden) 
			{
				isSsidHiddenStr = "YES";
			}
			if (__IEsptouchTask.DEBUG) {
				Log.d(TAG, "ShareWifi Button is clicked, SSID = " + apSsid
						+ ", " + " PASSWORD = " + apPassword);
			}
			new EsptouchAsyncTask3().execute(apSsid, apBssid, apPassword,
					isSsidHiddenStr, taskResultCountStr);
		}
	}
	
	private class EsptouchAsyncTask2 extends AsyncTask<String, Void, IEsptouchResult> {

		private ProgressDialog mProgressDialog;

		private IEsptouchTask mEsptouchTask;
		// without the lock, if the user tap confirm and cancel quickly enough,
		// the bug will arise. the reason is follows:
		// 0. task is starting created, but not finished
		// 1. the task is cancel for the task hasn't been created, it do nothing
		// 2. task is created
		// 3. Oops, the task should be cancelled, but it is running
		private final Object mLock = new Object();

		@Override
		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(WIFIConnectedActivityDEMO.this);
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
				String isSsidHiddenStr = params[3];
				boolean isSsidHidden = false;
				if (isSsidHiddenStr.equals("YES")) {
					isSsidHidden = true;
				}
				mEsptouchTask = new EsptouchTask(apSsid, apBssid, apPassword,
						isSsidHidden, WIFIConnectedActivityDEMO.this);
			}
			return mEsptouchTask.executeForResult();
		}

		@Override
		protected void onPostExecute(IEsptouchResult result) {
			mProgressDialog.getButton(DialogInterface.BUTTON_POSITIVE)
					.setEnabled(true);
			mProgressDialog.setButton(DialogInterface.BUTTON_POSITIVE,"确定激活",(dialog, which) -> {
				//发送激活设备的指令
				sendActivateDeviceCmd();
			});
			// it is unnecessary at the moment, add here just to show how to use isCancelled()
			if (!result.isCancelled()) {
				if (result.isSuc()) {
					mBSSID = result.getBssid();
					mProgressDialog.setMessage("Esptouch success, bssid = "
							+ mBSSID + ",InetAddress = "
							+ result.getInetAddress().getHostAddress());
				} else {
					mProgressDialog.setMessage("Esptouch fail");
				}
			}
		}
	}

	private void sendActivateDeviceCmd() {
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.add(RequestKey.DeviceActivated.DEVICE_SN, mBSSID);          //  change to mac id
		params.add(RequestKey.DeviceActivated.DEVICE_NAME, "我卓的设备");
		params.add(RequestKey.DeviceActivated.DEVICE_TYPE, "2");
		client.post(URL.DEVICE_ACTIVATE, params, new SimpleJsonHandler(WIFIConnectedActivityDEMO.this){
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				super.onSuccess(statusCode, headers, response);
				startActivity(new Intent(WIFIConnectedActivityDEMO.this,ConnectSucceedActivity.class));
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				super.onFailure(statusCode, headers, responseString, throwable);
				startActivity(new Intent(WIFIConnectedActivityDEMO.this,ConnectFailActivity.class));
			}
		});
	}

	private void onEsptoucResultAddedPerform(final IEsptouchResult result) {
		runOnUiThread(() -> {
            String text = result.getBssid() + " is connected to the wifi";
            Toast.makeText(WIFIConnectedActivityDEMO.this, text,
                    Toast.LENGTH_LONG).show();
        });
	}

	private IEsptouchListener myListener = this::onEsptoucResultAddedPerform;
	
	private class EsptouchAsyncTask3 extends AsyncTask<String, Void, List<IEsptouchResult>> {

		private ProgressDialog mProgressDialog;

		private IEsptouchTask mEsptouchTask;
		// without the lock, if the user tap confirm and cancel quickly enough,
		// the bug will arise. the reason is follows:
		// 0. task is starting created, but not finished
		// 1. the task is cancel for the task hasn't been created, it do nothing
		// 2. task is created
		// 3. Oops, the task should be cancelled, but it is running
		private final Object mLock = new Object();

		@Override
		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(WIFIConnectedActivityDEMO.this);
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
		protected List<IEsptouchResult> doInBackground(String... params) {
			int taskResultCount = -1;
			synchronized (mLock) {
				String apSsid = params[0];
				String apBssid = params[1];
				String apPassword = params[2];
				String isSsidHiddenStr = params[3];
				String taskResultCountStr = params[4];
				boolean isSsidHidden = false;
				if (isSsidHiddenStr.equals("YES")) {
					isSsidHidden = true;
				}
				taskResultCount = Integer.parseInt(taskResultCountStr);
				mEsptouchTask = new EsptouchTask(apSsid, apBssid, apPassword,
						isSsidHidden, WIFIConnectedActivityDEMO.this);
				mEsptouchTask.setEsptouchListener(myListener);
			}
			List<IEsptouchResult> resultList = mEsptouchTask.executeForResults(taskResultCount);
			return resultList;
		}

		@Override
		protected void onPostExecute(List<IEsptouchResult> result) {
			mProgressDialog.getButton(DialogInterface.BUTTON_POSITIVE)
					.setEnabled(true);
			mProgressDialog.getButton(DialogInterface.BUTTON_POSITIVE).setText(
					"Confirm");
			IEsptouchResult firstResult = result.get(0);
			// check whether the task is cancelled and no results received
			if (!firstResult.isCancelled()) {
				int count = 0;
				// max results to be displayed, if it is more than maxDisplayCount,
				// just show the count of redundant ones
				final int maxDisplayCount = 5;
				// the task received some results including cancelled while
				// executing before receiving enough results
				if (firstResult.isSuc()) {
					StringBuilder sb = new StringBuilder();
					for (IEsptouchResult resultInList : result) {
						sb.append("Esptouch success, bssid = "
								+ resultInList.getBssid()
								+ ",InetAddress = "
								+ resultInList.getInetAddress()
										.getHostAddress() + "\n");
						count++;
						if (count >= maxDisplayCount) {
							break;
						}
					}
					if (count < result.size()) {
						sb.append("\nthere's " + (result.size() - count)
								+ " more result(s) without showing\n");
					}
					mProgressDialog.setMessage(sb.toString());
				} else {
					mProgressDialog.setMessage("Esptouch fail");
				}
			}
		}
	}
}
