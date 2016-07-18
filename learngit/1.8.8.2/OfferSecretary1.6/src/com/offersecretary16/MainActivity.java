package com.offersecretary16;

/**
 * offer小蜜1.8.8.2
 * 2014.12.15 21:30
 * 作者：刘杰、蔡翔林、谭秋实
 */

import android.net.ConnectivityManager;
import android.os.Bundle;

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
import android.content.Intent;
import android.os.AsyncTask;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private long mExitTime = 0l;// 两次按退出键时间间隔，初始值为1
	private TextView zongshuTextView;// 今日宣讲会总数显示框
	private Button xuanjianghuiButton;// 宣讲会按钮
	private Button gongsiButton;// 公司按钮
	private Button shixiButton;// 实习按钮
	private Button mianjingButton;// 面经按钮
	private Button luxianButton;// 路线按钮
	private Button douzaisouButton;// 大家都在搜按钮
	private Button yijianButton;// 意见按钮
	private Button bangzhuButton;// 帮助按钮
	private String JINRI = "今日";
	private String HTTP = "http://2001.offerxiaomi.sinaapp.com/easy/testcai3.php";// 请求连接的网址
	HttpClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		client = new DefaultHttpClient();
		zongshuTextView = (TextView) findViewById(R.id.zongshu);
		xuanjianghuiButton = (Button) findViewById(R.id.xuanjianghui);
		gongsiButton = (Button) findViewById(R.id.gongsi);
		shixiButton = (Button) findViewById(R.id.shixi);
		mianjingButton = (Button) findViewById(R.id.mianjing);
		luxianButton = (Button) findViewById(R.id.luxian);
		douzaisouButton = (Button) findViewById(R.id.douzaisou);
		yijianButton = (Button) findViewById(R.id.yijian);
		bangzhuButton = (Button) findViewById(R.id.bangzhu);

		zongshuTextView.setBackgroundColor(0x33ffffff);
		xuanjianghuiButton.setBackgroundColor(0xff12D9E9);
		gongsiButton.setBackgroundColor(0xffCAFF70);
		shixiButton.setBackgroundColor(0xffFB7DBC);
		mianjingButton.setBackgroundColor(0xff2571D6);
		luxianButton.setBackgroundColor(0xffF7F71A);
		douzaisouButton.setBackgroundColor(0xffF78D1A);
		yijianButton.setBackgroundColor(0xff7D57D7);
		bangzhuButton.setBackgroundColor(0xff24EC60);
		
			jinrizongshu(HTTP, JINRI);// 获取今日宣讲会总数
		

		// 点击首页宣讲会按钮，启动宣讲会
		xuanjianghuiButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent_xjh = new Intent(MainActivity.this,
						XjhActivity.class);
				startActivity(intent_xjh);
			}
		});

		// 点击首页实习按钮，启动实习查询
		shixiButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent_sx = new Intent(MainActivity.this,
						SxActivity.class);
				startActivity(intent_sx);
			}
		});
		// 点击首页面经按钮，启动面经查询
		mianjingButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent_mj = new Intent(MainActivity.this,
						MjActivity.class);
				startActivity(intent_mj);
			}
		});

		// 点击首页公司按钮，启动公司查询
		gongsiButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent_gs = new Intent(MainActivity.this,
						GsActivity.class);
				startActivity(intent_gs);
			}
		});

		// 点击首页路线按钮，启动路线查询
		luxianButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent_lx = new Intent(MainActivity.this,
						LxActivity.class);
				startActivity(intent_lx);
			}
		});

		// 点击首页大家都在搜按钮，启动大家都在搜
		douzaisouButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent_dzs = new Intent(MainActivity.this,
						DzsActivity.class);
				startActivity(intent_dzs);
			}
		});

		// 点击首页意见按钮，启动意见
		yijianButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent_yj = new Intent(MainActivity.this,
						YjActivity.class);
				startActivity(intent_yj);
			}
		});

		// 点击首页帮助按钮，启动帮助
		bangzhuButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent_bz = new Intent(MainActivity.this,
						BzActivity.class);
				startActivity(intent_bz);
			}
		});

	}

	// 获取今日宣讲会总数 方法
	public void jinrizongshu(String url, String in) {
		//判断网络连接状态再执行后续程序
				ConnectivityManager network_state=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE); // 取得ConnectivityManager，以判断网络连接状态
				if (network_state.getActiveNetworkInfo()==null) {
					Toast.makeText(MainActivity.this, "当前网络不可用，请检查网络设置", Toast.LENGTH_SHORT).show();//检查网络连接，如果无网络可用，就不进行后续操作等
				}else{
		new AsyncTask<String, Void, String>() {
			@Override
			protected String doInBackground(String... arg0) {
				HttpPost post = new HttpPost(HTTP);
				try {
					List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
					parameters.add(new BasicNameValuePair("format", "json"));
					parameters.add(new BasicNameValuePair("uid", JINRI));
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
					zongshuTextView.setText("今日宣讲会总数：" + root.getString("sum"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}.execute(url, in);
				}
	}

	// 判断连续两次退出按键后退出程序
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {// 两次退出按键事件间隔大于2000毫秒则显示提示，如果小于2000毫秒则直接退出
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
