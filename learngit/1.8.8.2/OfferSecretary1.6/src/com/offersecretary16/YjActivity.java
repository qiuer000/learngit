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

public class YjActivity extends Activity {
	private Button Button_back;// 返回按钮
	private Button Button_submit;// 提交按钮
	private TextView yj_print;// 内容显示框
	private EditText yj_EditText;// 意见编辑框
	private String INFO = "";// 定义显示内容的变量
	private String HTTP = "http://2001.offerxiaomi.sinaapp.com/easy/testcai2.php";// 请求连接的网址
	private HttpClient client;
	private String yijian = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yj_main);
		Button_back = (Button) findViewById(R.id.back);
		Button_submit = (Button) findViewById(R.id.submit);
		yj_EditText = (EditText) findViewById(R.id.input_yj);
		yj_print = (TextView) findViewById(R.id.yj_print);
		yj_print.setMovementMethod(ScrollingMovementMethod.getInstance());// 设置滚动条
		client = new DefaultHttpClient();

		// 返回主页按钮响应
		Button_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				YjActivity.this.finish();// 结束当前YjActivity
			}
		});
		
		// 提交按钮响应
		Button_submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (yj_EditText.getText().toString().equals("")) {
					Toast.makeText(YjActivity.this, "提示:您还没有输入意见！",
							Toast.LENGTH_LONG).show();

				}else{
						readNet(HTTP, yijian);// 执行意见命令
				}
			}
		});
	}

	public void readNet(String url, String yijian) {
		//判断网络连接状态再执行后续程序
				ConnectivityManager network_state=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE); // 取得ConnectivityManager，以判断网络连接状态
				if (network_state.getActiveNetworkInfo()==null) {
					Toast.makeText(YjActivity.this, "当前网络不可用，请检查网络设置", Toast.LENGTH_SHORT).show();//检查网络连接，如果无网络可用，就不进行后续操作等
					yj_print.setText("当前网络不可用，请检查网络设置");// 显示网络问题到界面的显示框
					yj_print.setTextColor(Color.RED);//设置文字颜色为红色
				}else{
		new AsyncTask<String, Void, String>() {
			String inputyj = "意见#" + yj_EditText.getText().toString();// 获取意见

			@Override
			protected String doInBackground(String... arg0) {
				HttpPost post = new HttpPost(HTTP);
				try {
					List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
					parameters.add(new BasicNameValuePair("format", "json"));
					parameters.add(new BasicNameValuePair("uid", inputyj));
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
					yj_print.setText(INFO);// 显示内容到界面的显示框
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}.execute(url, yijian);
				}
	}
}
