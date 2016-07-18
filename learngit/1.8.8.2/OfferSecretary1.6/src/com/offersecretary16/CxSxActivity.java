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

public class CxSxActivity extends Activity{
	private Button Button_back;// 返回按钮
	private Button Button_previousPage;// 上一页按钮
	private Button Button_nextPage;// 下一页按钮
	private EditText EditText_inputPage;// 输入跳转页编辑框
	private Button Button_jump;// 跳转按钮
	private TextView text_yindao;// 显示宣讲会信息总页数和页码等信息
	private TextView text;// 内容显示框
	private String INFO = "";// 定义显示内容的变量
	private String HTTP = "http://2001.offerxiaomi.sinaapp.com/easy/testshixi.php";// 请求连接的网址
	private HttpClient client;
	public String input_gongsi = "";// 默认公司输入为空
	public String cityseek = "";// 默认城市选项为空
	//起始日期
	public String date_yearseek = "";// 默认年份选项为空
	public String date_monthseek = "";// 默认月份选项为空
	public String date_dayseek = "";// 默认日选项为空
	public String dateseek_start = "";// 用于存放初始日期标准年月日格式字符串
	//截止日期
	public String date_yearseek_end = "";// 默认年份选项为空
	public String date_monthseek_end = "";// 默认月份选项为空
	public String date_dayseek_end = "";// 默认日选项为空
	public String dateseek_end = "";// 用于存放初始日期标准年月日格式字符串
	public int inputPage = 1;// 默认输入的页数为第1页
	private int sumPage = 1;// 默认总页数为1
	private int nowPage = 1;// 默认当前页为第1页
	private final int EBERYPAGESUM = 10;// 宣讲会每页显示的信息总数

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cx_sx_main);
		Intent intent = getIntent();// 获取Intent对象
		Bundle bundle = intent.getExtras();// 获取传递的数据包

		Button_back = (Button) findViewById(R.id.back);// 返回按钮
		Button_previousPage = (Button) findViewById(R.id.previouspage);
		Button_nextPage = (Button) findViewById(R.id.nextpage);
		EditText_inputPage = (EditText) findViewById(R.id.inputPage);
		Button_jump = (Button) findViewById(R.id.jump);
		client = new DefaultHttpClient();
		text_yindao = (TextView) findViewById(R.id.yindao);
		text = (TextView) findViewById(R.id.sx_print);
		text.setMovementMethod(ScrollingMovementMethod.getInstance());// 设置滚动条
		input_gongsi = bundle.getString("company");// 获得公司输入值
		cityseek = bundle.getString("city");// 获得城市选项值
		//起始日期
		date_yearseek = bundle.getString("date_year");// 获得年份选项值
		date_monthseek = bundle.getString("date_month");// 获得月份选项值
		date_dayseek = bundle.getString("date_day");// 获得日选项值
		dateseek_start = date_yearseek + "-" + date_monthseek + "-" + date_dayseek;// 标准化日期格式，用于传输
		//截至日期
				date_yearseek_end = bundle.getString("date_year_end");// 获得年份选项值
				date_monthseek_end = bundle.getString("date_month_end");// 获得月份选项值
				date_dayseek_end = bundle.getString("date_day_end");// 获得日选项值
				dateseek_end = date_yearseek_end + "-" + date_monthseek_end + "-" + date_dayseek_end;// 标准化日期格式，用于传输
		readNet(HTTP, input_gongsi, cityseek,dateseek_start,dateseek_end,inputPage);// 执行查询命令

		// 返回按钮，结束当前CxActivity,返回宣讲会界面
		Button_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CxSxActivity.this.finish();
			}
		});

		// 上一页按钮
		Button_previousPage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (nowPage != 1) {
					INFO="";//先将原内容置空
					readNet(HTTP, input_gongsi, cityseek,dateseek_start,dateseek_end,
							(nowPage - 1));// 如果当前页数不是第1页则执行
				} else {
					Toast.makeText(CxSxActivity.this, "提示:已经是第1页了!",
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
					readNet(HTTP, input_gongsi, cityseek,dateseek_start,dateseek_end,
							(nowPage + 1));// 如果当前页数不是最后1页则执行
				} else {
					Toast.makeText(CxSxActivity.this, "提示:已经是最后一页了!",
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
					readNet(HTTP, input_gongsi, cityseek,dateseek_start,dateseek_end,
							EditTextinputPage);
				} else {
					Toast.makeText(CxSxActivity.this, "提示:输入页码有误!",
							Toast.LENGTH_LONG).show();
				}

			}
		});

	}

	public void readNet(String url,  String company, String city , String date,String date_end,final int inputPage) {
		//判断网络连接状态再执行后续程序
				ConnectivityManager network_state=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE); // 取得ConnectivityManager，以判断网络连接状态
				if (network_state.getActiveNetworkInfo()==null) {
					Toast.makeText(CxSxActivity.this, "当前网络不可用，请检查网络设置", Toast.LENGTH_SHORT).show();//检查网络连接，如果无网络可用，就不进行后续操作等
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
					parameters.add(new BasicNameValuePair("company", input_gongsi));
					parameters.add(new BasicNameValuePair("city", cityseek));
					if(dateseek_start.equals("-"+"-")){
						dateseek_start="";//如果起始日期没有输入，则置日期为空
					}
					if(dateseek_end.equals("-"+"-")){
						dateseek_end="";//如果截止日期没有输入，则置日期为空
					}
					parameters.add(new BasicNameValuePair("date_start", dateseek_start));
					parameters.add(new BasicNameValuePair("date_end", dateseek_end));
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
					if (root.getString("flag").equals("4")) {
						text_yindao.setText("实习信息：\n总共" + sumPage + "页"
								+ root.getString("sum") + "条，当前第"
								+ root.getString("page") + "页，" + "本页共"
								+ root.getString("count") + "条。");
						JSONArray array = root.getJSONArray("info");
						for (int i = 0; i < array.length(); i++) {
							JSONObject lan = array.getJSONObject(i);
							INFO = INFO
									+ ("公司：" + lan.getString("company") + "\n")
									+ ("日期：" + lan.getString("date") + "\n")
									+ ("时间：" + lan.getString("time") + "\n")
									+ ("城市：" + lan.getString("city") + "\n")
									+ ("学校：" + lan.getString("uni") + "\n")
//									+ ("地点：" + lan.getString("local") + "\n")
									+ ("链接：" + lan.getString("description") + "\n")
									+ "--------------------------" + "\n";
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
		}.execute(url,company,city,date,date_end);
				}
	}
}
