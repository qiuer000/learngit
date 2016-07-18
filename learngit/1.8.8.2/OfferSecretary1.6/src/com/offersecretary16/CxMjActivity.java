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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class CxMjActivity extends Activity{
	private Button Button_back;// 返回按钮
	private Button Button_previousPage;// 上一页按钮
	private Button Button_nextPage;// 下一页按钮
	private EditText EditText_inputPage;// 输入跳转页编辑框
	private Button Button_jump;// 跳转按钮
	private TextView text_yindao;// 显示宣讲会信息总页数和页码等信息
	private TextView text;// 内容显示框
	private String INFO = "";// 定义显示内容的变量
	private String HTTP = "http://2001.offerxiaomi.sinaapp.com/easy/testmj.php";// 请求连接的网址
	private HttpClient client;
	public String date_yearseek = "";// 默认年份选项为空
	public String date_monthseek = "";// 默认月份选项为空
	public String date_dayseek = "";// 默认日选项为空
	public String dateseek = "";// 用于存放标准年月日格式字符串
	public int inputPage = 1;// 默认输入的页数为第1页
	private int sumPage = 1;// 默认总页数为1
	private int nowPage = 1;// 默认当前页为第1页
	private final int EBERYPAGESUM = 10;// 宣讲会每页显示的信息总数

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cx_mj_main);
		Intent intent = getIntent();// 获取Intent对象
		Bundle bundle = intent.getExtras();// 获取传递的数据包
		Button_back = (Button) findViewById(R.id.back);// 返回按钮
		Button_previousPage = (Button) findViewById(R.id.previouspage);
		Button_nextPage = (Button) findViewById(R.id.nextpage);
		EditText_inputPage = (EditText) findViewById(R.id.inputPage);
		Button_jump = (Button) findViewById(R.id.jump);
		client = new DefaultHttpClient();
		text_yindao = (TextView) findViewById(R.id.yindao);
		text = (TextView) findViewById(R.id.print_mj);
		text.setMovementMethod(ScrollingMovementMethod.getInstance());// 设置滚动条
		date_yearseek = bundle.getString("date_year");// 获得年份选项值
		date_monthseek = bundle.getString("date_month");// 获得月份选项值
		date_dayseek = bundle.getString("date_day");// 获得日选项值
		dateseek = date_yearseek + "-" + date_monthseek + "-" + date_dayseek;// 标准化日期格式，用于传输
		readNet(HTTP, dateseek, inputPage);// 执行查询命令

		// 返回按钮，结束当前CxActivity,返回宣讲会界面
		Button_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CxMjActivity.this.finish();
			}
		});

		// 上一页按钮
		Button_previousPage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (nowPage != 1) {
					INFO="";//先将原内容置空
					readNet(HTTP,  dateseek, 
							(nowPage - 1));// 如果当前页数不是第1页则执行
				} else {
					Toast.makeText(CxMjActivity.this, "提示:已经是第1页了!",
							Toast.LENGTH_LONG).show();
				}
				EditText_inputPage.setText(null);// 清空页码输入框
			}
		});

		// 下一页按钮
		Button_nextPage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (nowPage != sumPage) {
					INFO="";//先将原内容置空
					readNet(HTTP,  dateseek, 
							(nowPage + 1));// 如果当前页数不是最后1页则执行
				} else {
					Toast.makeText(CxMjActivity.this, "提示:已经是最后一页了!",
							Toast.LENGTH_LONG).show();
				}
				EditText_inputPage.setText(null);// 清空页码输入框
			}
		});

		// 跳转按钮
		Button_jump.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				int EditTextinputPage = 1;
				try {
					EditTextinputPage = Integer.parseInt(EditText_inputPage
							.getText().toString());// 获取用户输入的页码
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (EditTextinputPage <= sumPage && EditTextinputPage >= 1) {
					INFO="";//先将原内容置空
					readNet(HTTP, dateseek,
							EditTextinputPage);
				} else {
					Toast.makeText(CxMjActivity.this, "提示:输入页码有误!",
							Toast.LENGTH_LONG).show();
				}

			}
		});

	}

	public void readNet(String url,String date,final int inputPage) {
		//判断网络连接状态再执行后续程序
				ConnectivityManager network_state=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE); // 取得ConnectivityManager，以判断网络连接状态
				if (network_state.getActiveNetworkInfo()==null) {
					Toast.makeText(CxMjActivity.this, "当前网络不可用，请检查网络设置", Toast.LENGTH_SHORT).show();//检查网络连接，如果无网络可用，就不进行后续操作等
					text.setText("当前网络不可用，请检查网络设置");// 显示网络问题到界面的显示框
					text.setTextColor(Color.RED);//设置文字颜色为红色
				}else{
		new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... arg0) {
				HttpPost post = new HttpPost(HTTP);
				try {
					List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
					parameters.add(new BasicNameValuePair("format", "json"));
					if(dateseek.equals("-"+"-")){
						dateseek="";//如果日期没有输入，则置日期为空
					}
					parameters.add(new BasicNameValuePair("date", dateseek));
					parameters.add(new BasicNameValuePair("page", inputPage
							+ ""));// 页数默认为第1页
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
					try {
						int sumPageflag= Integer.parseInt(root.getString("sum"));//临时变量
						sumPage =(sumPageflag%EBERYPAGESUM)==0?(sumPageflag/10):(sumPageflag/10+1);// 总页数=sum除以每页的条数,如果不能整除再加1
						nowPage = Integer.parseInt(root.getString("page"));// 获得当前页码
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (root.getString("flag").equals("9")) {
						text_yindao.setText("面经信息：\n总共" + sumPage + "页"
								+ root.getString("sum") + "条，当前第"
								+ root.getString("page") + "页，" + "本页共"
								+ root.getString("count") + "条。");

						JSONArray array = root.getJSONArray("info");
						for (int i = 0; i < array.length(); i++) {
							JSONObject lan = array.getJSONObject(i);
							INFO = INFO
									+ ("标题：" + lan.getString("name") + "\n")
									+ ("日期：" + lan.getString("date") + "\n")
									+ ("链接：" + lan.getString("description") + "\n")
									+ "-----------------------------------" + "\n";
						}
					} else {
						INFO = root.getString("info") + "\n";
					}
					text.setText(INFO);// 显示内容到界面的显示框
					text.scrollTo(0, 0);//将滚动条定位在顶部
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}.execute(url,date);
				}
	}
}
