package com.offersecretary16;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MjActivity extends Activity {
	private Button Button_back;// 返回按钮
	private Button Button_seek;// 查询按钮
	private TextView shuoming;// 选项说明框
	private Spinner spinner_date_year;// 日期,年选择
	private Spinner spinner_date_month;// 日期,月选择
	private Spinner spinner_date_day;// 日期,日选择
	private ArrayAdapter<String> adapter_date_year;// 日期选择适配器
	private ArrayAdapter<String> adapter_date_month;// 日期选择适配器
	private ArrayAdapter<String> adapter_date_day;// 日期选择适配器
	private String date_yearOperation = "";// 日期年选项值
	private String date_monthOperation = "";// 日期月选项值
	private String date_dayOperation = "";// 日期日选项值
	private int date_yearflag = 0;// 默认置零，即未选择年份
	private int date_monthflag = 0;// 默认置零，即未选择月
	private int date_dayflag = 0;// 默认置零，即未选择日
	private int date_flag = 0;// 其值为0或3方可

	// 年选项
	private String[] year = new String[] { "", "2014", "2015" };
	// 月选项
	private String[] month = new String[] { "", "01", "02", "03", "04", "05",
			"06", "07", "08", "09", "10", "11", "12" };
	// 日选项
	private String[][] day = new String[][] {
			{ "" },
			{ "", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
					"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
					"21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
					"31" },
			{ "", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
					"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
					"21", "22", "23", "24", "25", "26", "27", "28" },
			{ "", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
					"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
					"21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
					"31" },
			{ "", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
					"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
					"21", "22", "23", "24", "25", "26", "27", "28", "29", "30" },
			{ "", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
					"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
					"21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
					"31" },
			{ "", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
					"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
					"21", "22", "23", "24", "25", "26", "27", "28", "29", "30" },
			{ "", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
					"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
					"21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
					"31" },
			{ "", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
					"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
					"21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
					"31" },
			{ "", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
					"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
					"21", "22", "23", "24", "25", "26", "27", "28", "29", "30" },
			{ "", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
					"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
					"21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
					"31" },
			{ "", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
					"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
					"21", "22", "23", "24", "25", "26", "27", "28", "29", "30" },
			{ "", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
					"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
					"21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
					"31" },

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mj_main);
		Button_back = (Button) findViewById(R.id.back);
		Button_seek = (Button) findViewById(R.id.seek);
		shuoming = (TextView) findViewById(R.id.shuoming);
		shuoming.setMovementMethod(ScrollingMovementMethod.getInstance());// 设置滚动条

		setSpinner();// 加载日期下拉条

		// 返回主页按钮响应
		Button_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				MjActivity.this.finish();// 结束当前MjActivity
			}
		});

		// 查询按钮响应
		Button_seek.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				date_flag = date_dayflag + date_monthflag + date_yearflag;
				if (date_flag == 1 || date_flag == 2) {
					Toast.makeText(MjActivity.this, "提示:请选择完整日期或者不选择任何日期!",
							Toast.LENGTH_LONG).show();
				} else {
					Intent intent_cx_mj = new Intent(MjActivity.this,
							CxMjActivity.class);
					Bundle bundle = new Bundle();// 创建并实例化一个Bundle对象
					bundle.putCharSequence("date_year", date_yearOperation);// 保存日期年选项值
					bundle.putCharSequence("date_month", date_monthOperation);// 保存日期月选项值
					bundle.putCharSequence("date_day", date_dayOperation);// 保存日期日选项值
					intent_cx_mj.putExtras(bundle);// 将Bundle对象添加到Intent对象中
					startActivity(intent_cx_mj);
				}

			}
		});

	}

	// 加载日期下拉条 方法
	private void setSpinner()

	{
		spinner_date_year = (Spinner) findViewById(R.id.spinner_date_year);// 日期年选择
		spinner_date_month = (Spinner) findViewById(R.id.spinner_date_month);// 日期月选择
		spinner_date_day = (Spinner) findViewById(R.id.spinner_date_day);// 日期日选择

		// 绑定适配器和值

		// 日期
		// 年
		adapter_date_year = new ArrayAdapter<String>(MjActivity.this,
				android.R.layout.simple_spinner_item, year);
		spinner_date_year.setAdapter(adapter_date_year);
		spinner_date_year.setSelection(0, true); // 设置默认选中项，此处为默认选中第1个值
		// 月
		adapter_date_month = new ArrayAdapter<String>(MjActivity.this,
				android.R.layout.simple_spinner_item, month);
		spinner_date_month.setAdapter(adapter_date_month);
		spinner_date_month.setSelection(0, true); // 设置默认选中项，此处为默认选中第1个值

		// 日
		adapter_date_day = new ArrayAdapter<String>(MjActivity.this,
				android.R.layout.simple_spinner_item, day[0]);
		spinner_date_day.setAdapter(adapter_date_day);
		spinner_date_day.setSelection(0, true); // 默认选中第1个

		// 日期年下拉框
		spinner_date_year
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long arg3) {

						date_yearOperation = arg0.getItemAtPosition(position)
								.toString();// 获取日期选中值
						if (!date_yearOperation.equals("")) {// 用于判断是否选择了年,选择不为空则置flag为1
							date_yearflag = 1;
						} else {
							date_yearflag = 0;// 如果为空，置0
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});
		// 日期月下拉框
		spinner_date_month
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					// 默示选项被改变时触发此方法，首要实现办法：动态改变日适配器的绑定值
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						// position为当前月份选中的值的序号
						// 将日期日适配器的值改变为day[position]中的值
						adapter_date_day = new ArrayAdapter<String>(
								MjActivity.this,
								android.R.layout.simple_spinner_item,
								day[position]);
						// 设置日期日下拉列表的选项内容适配器
						spinner_date_day.setAdapter(adapter_date_day);
						date_monthOperation = arg0.getItemAtPosition(position)
								.toString();// 获取日期月选中值
						if (!date_monthOperation.equals("")) {// 用于判断是否选择了月,选择不为空则置flag为1
							date_monthflag = 1;
						} else {
							date_monthflag = 0;// 如果为空，置0
						}

					}

					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});

		// 日期日下拉框
		spinner_date_day
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						date_dayOperation = arg0.getItemAtPosition(position)
								.toString();// 获取日期日选中值
						if (!date_dayOperation.equals("")) {// 用于判断是否选择了日,选择不为空则置flag为1
							date_dayflag = 1;
						} else {
							date_dayflag = 0;// 如果为空，置0
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});

	}
}
