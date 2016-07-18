package com.offersecretary16;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LxActivity extends Activity {
	private Button Button_back;// 返回按钮
	private Button Button_lx_seek;// 查询路线按钮
	private Button flush_city;// 清空城市输入框按钮
	private Button flush_source;// 清空城市输入框按钮
	private Button flush_destination;// 清空城市输入框按钮
	private EditText lx_inputCity;
	private EditText lx_inputSource;
	private EditText lx_inputDestination;
	private TextView lx_print;// 显示路线内容框
	private String INFO = "";// 定义显示内容的变量
	private String HTTP = "http://2001.offerxiaomi.sinaapp.com/easy/testcai2.php";// 请求连接的网址
	private HttpClient client;
	private String luxian = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.lx_main);
		Button_back = (Button) findViewById(R.id.back);
		Button_lx_seek = (Button) findViewById(R.id.lx_seek);
		flush_city = (Button) findViewById(R.id.flush_city);
		flush_source = (Button) findViewById(R.id.flush_source);
		flush_destination = (Button) findViewById(R.id.flush_destination);
		lx_inputCity = (EditText) findViewById(R.id.lx_inputCity);
		lx_inputSource = (EditText) findViewById(R.id.lx_inputSource);
		lx_inputDestination = (EditText) findViewById(R.id.lx_inputDestination);
		lx_print = (TextView) findViewById(R.id.lx_print);
		lx_print.setMovementMethod(ScrollingMovementMethod.getInstance());// 设置滚动条
		client = new DefaultHttpClient();

		// 返回主页按钮响应
		Button_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				LxActivity.this.finish();// 结束当前LxActivity
			}
		});

		// 城市清空按钮
		flush_city.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				lx_inputCity.setText("");// 清空城市输入
			}

		});

		// 出发地清空按钮
		flush_source.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				lx_inputSource.setText("");// 清空出发地输入
			}

		});

		// 目的地清空按钮
		flush_destination.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				lx_inputDestination.setText("");// 清空目的地输入
			}

		});

		// 路线查询按钮
		Button_lx_seek.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (lx_inputCity.getText().toString().equals("")
						|| lx_inputSource.getText().toString().equals("")
						|| lx_inputDestination.getText().toString().equals("")) {
					Toast.makeText(LxActivity.this, "提示:[城市]、[出发地]和[目的地]均为必填项，请将信息填写完整。",
							Toast.LENGTH_LONG).show();

				}else{
						readNet(HTTP, luxian);// 执行查询路线命令
				}
			}

		});

	}

	public void readNet(String url, String luxian) {
		//判断网络连接状态再执行后续程序
				ConnectivityManager network_state=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE); // 取得ConnectivityManager，以判断网络连接状态
				if (network_state.getActiveNetworkInfo()==null) {
					Toast.makeText(LxActivity.this, "当前网络不可用，请检查网络设置", Toast.LENGTH_SHORT).show();//检查网络连接，如果无网络可用，就不进行后续操作等
					lx_print.setText("当前网络不可用，请检查网络设置");// 显示网络问题到界面的显示框
					lx_print.setTextColor(Color.RED);//设置文字颜色为红色
				}else{
		new AsyncTask<String, Void, String>() {

			String inputlx = "路线#" + lx_inputCity.getText().toString() + "#"
					+ lx_inputSource.getText().toString() + "#"
					+ lx_inputDestination.getText().toString();// 获取路线

			@Override
			protected String doInBackground(String... arg0) {
				HttpPost post = new HttpPost(HTTP);
				try {
					List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
					parameters.add(new BasicNameValuePair("format", "json"));
					parameters.add(new BasicNameValuePair("uid",inputlx));//路线#电子科技大学清水河校区#四川大学望江校区
					post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
				} catch (UnsupportedEncodingException e1) {
				}

				try {
					HttpResponse response = client.execute(post);
					String value = EntityUtils.toString(response.getEntity(),
							"UTF-8");
					return value;
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
				try {
					JSONObject root = new JSONObject(result);
					INFO = root.getString("info") + "\n";
					lx_print.setText(INFO);// 显示内容到路线界面的显示框
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}.execute(url, luxian);
				}
	}
}
