package com.offersecretary16;

/**
 * offerС��1.8.8.2
 * 2014.12.15 21:30
 * ���ߣ����ܡ������֡�̷��ʵ
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
	private long mExitTime = 0l;// ���ΰ��˳���ʱ��������ʼֵΪ1
	private TextView zongshuTextView;// ����������������ʾ��
	private Button xuanjianghuiButton;// �����ᰴť
	private Button gongsiButton;// ��˾��ť
	private Button shixiButton;// ʵϰ��ť
	private Button mianjingButton;// �澭��ť
	private Button luxianButton;// ·�߰�ť
	private Button douzaisouButton;// ��Ҷ����Ѱ�ť
	private Button yijianButton;// �����ť
	private Button bangzhuButton;// ������ť
	private String JINRI = "����";
	private String HTTP = "http://2001.offerxiaomi.sinaapp.com/easy/testcai3.php";// �������ӵ���ַ
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
		
			jinrizongshu(HTTP, JINRI);// ��ȡ��������������
		

		// �����ҳ�����ᰴť������������
		xuanjianghuiButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent_xjh = new Intent(MainActivity.this,
						XjhActivity.class);
				startActivity(intent_xjh);
			}
		});

		// �����ҳʵϰ��ť������ʵϰ��ѯ
		shixiButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent_sx = new Intent(MainActivity.this,
						SxActivity.class);
				startActivity(intent_sx);
			}
		});
		// �����ҳ�澭��ť�������澭��ѯ
		mianjingButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent_mj = new Intent(MainActivity.this,
						MjActivity.class);
				startActivity(intent_mj);
			}
		});

		// �����ҳ��˾��ť��������˾��ѯ
		gongsiButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent_gs = new Intent(MainActivity.this,
						GsActivity.class);
				startActivity(intent_gs);
			}
		});

		// �����ҳ·�߰�ť������·�߲�ѯ
		luxianButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent_lx = new Intent(MainActivity.this,
						LxActivity.class);
				startActivity(intent_lx);
			}
		});

		// �����ҳ��Ҷ����Ѱ�ť��������Ҷ�����
		douzaisouButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent_dzs = new Intent(MainActivity.this,
						DzsActivity.class);
				startActivity(intent_dzs);
			}
		});

		// �����ҳ�����ť���������
		yijianButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent_yj = new Intent(MainActivity.this,
						YjActivity.class);
				startActivity(intent_yj);
			}
		});

		// �����ҳ������ť����������
		bangzhuButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent_bz = new Intent(MainActivity.this,
						BzActivity.class);
				startActivity(intent_bz);
			}
		});

	}

	// ��ȡ�������������� ����
	public void jinrizongshu(String url, String in) {
		//�ж���������״̬��ִ�к�������
				ConnectivityManager network_state=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE); // ȡ��ConnectivityManager�����ж���������״̬
				if (network_state.getActiveNetworkInfo()==null) {
					Toast.makeText(MainActivity.this, "��ǰ���粻���ã�������������", Toast.LENGTH_SHORT).show();//����������ӣ������������ã��Ͳ����к���������
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
					zongshuTextView.setText("����������������" + root.getString("sum"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}.execute(url, in);
				}
	}

	// �ж����������˳��������˳�����
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {// �����˳������¼��������2000��������ʾ��ʾ�����С��2000������ֱ���˳�
				Toast.makeText(this, "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
