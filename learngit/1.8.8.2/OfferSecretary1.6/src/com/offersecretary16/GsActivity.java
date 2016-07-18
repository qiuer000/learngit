package com.offersecretary16;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GsActivity extends Activity {
	private Button Button_back;// 返回按钮
	private Button Button_seek;// 查询按钮
	private EditText EditText_gs;// 公司编辑输入框
	private Button flush_gs;// 清空公司输入框按钮
	private EditText EditText_uni_city;// 学校或者城市编辑输入框
	private Button flush_uni_city;// 清空学校或者城市输入框
	private TextView shuoming;// 说明框
	private String input_gongsi = "";// 公司输入内容
	private String input_uni_cityString = "";// 学校或者城市输入内容

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gs_main);
		Button_back = (Button) findViewById(R.id.back);
		Button_seek = (Button) findViewById(R.id.seek);
		flush_gs = (Button) findViewById(R.id.flush_gs);
		EditText_gs = (EditText) findViewById(R.id.input_gs);
		EditText_uni_city = (EditText) findViewById(R.id.input_uni_city);
		flush_uni_city = (Button) findViewById(R.id.flush_uni_city);
		shuoming = (TextView) findViewById(R.id.shuoming);
		shuoming.setMovementMethod(ScrollingMovementMethod.getInstance());// 设置滚动条

		// 返回主页按钮响应
		Button_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				GsActivity.this.finish();// 结束当前GsActivity
			}
		});

		// 公司输入清空按钮
		flush_gs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText_gs.setText("");// 清空公司输入
			}
		});

		// 学校或城市输入清空按钮
		flush_uni_city.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText_uni_city.setText("");// 清空学校或城市输入
			}
		});

		// 查询按钮响应
		Button_seek.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				input_gongsi = EditText_gs.getText().toString();
				input_uni_cityString = EditText_uni_city.getText().toString();
				if (input_gongsi.equals("")) {
					Toast.makeText(GsActivity.this, "提示:请填写公司名称!",
							Toast.LENGTH_LONG).show();
				} else {
					Intent intent_cx_gs = new Intent(GsActivity.this,
							CxGsActivity.class);
					Bundle bundle = new Bundle();// 创建并实例化一个Bundle对象
					bundle.putCharSequence("company", input_gongsi);// 保存公司输入值
					bundle.putCharSequence("uid", input_uni_cityString);// 保存学校输入值
					intent_cx_gs.putExtras(bundle);// 将Bundle对象添加到Intent对象中
					startActivity(intent_cx_gs);
				}
			}
		});
	}

}
