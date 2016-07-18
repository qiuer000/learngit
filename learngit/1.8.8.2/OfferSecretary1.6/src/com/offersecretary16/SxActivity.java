package com.offersecretary16;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SxActivity extends Activity{

	private Button Button_back;// 返回按钮
	private Button Button_seek;// 查询按钮
	private EditText EditText_gs;//公司编辑输入框
	private Button flush_gs;//清空公司输入框按钮
	private TextView shuoming;//说明框
	private String input_gongsi = "";// 公司输入内容
	private Spinner spinner_city;// 城市选择
	private ArrayAdapter<String> adapter_city;// 城市选择适配器
	private String cityOperation = "";// 城市选项值
	//起始日期
	private Spinner spinner_date_year;// 日期,年选择
	private Spinner spinner_date_month;// 日期,月选择
	private Spinner spinner_date_day;// 日期,日选择
	private ArrayAdapter<String> adapter_date_year;// 时间选择适配器
	private ArrayAdapter<String> adapter_date_month;// 时间选择适配器
	private ArrayAdapter<String> adapter_date_day;// 时间选择适配器
	private String date_yearOperation = "";// 日期年选项值
	private String date_monthOperation = "";// 日期月选项值
	private String date_dayOperation = "";// 日期日选项值
	private int date_yearflag = 0;// 默认置零，即未选择年份
	private int date_monthflag = 0;// 默认置零，即未选择月
	private int date_dayflag = 0;// 默认置零，即未选择日
	private int date_flag = 0;// 其值为0或3方可
	//截止日期
	private Spinner spinner_date_year_end;// 日期,年选择
	private Spinner spinner_date_month_end;// 日期,月选择
	private Spinner spinner_date_day_end;// 日期,日选择
	private ArrayAdapter<String> adapter_date_year_end;// 时间选择适配器
	private ArrayAdapter<String> adapter_date_month_end;// 时间选择适配器
	private ArrayAdapter<String> adapter_date_day_end;// 时间选择适配器
	private String date_yearOperation_end = "";// 日期年选项值
	private String date_monthOperation_end = "";// 日期月选项值
	private String date_dayOperation_end = "";// 日期日选项值
	private int date_yearflag_end = 0;// 默认置零，即未选择年份
	private int date_monthflag_end = 0;// 默认置零，即未选择月
	private int date_dayflag_end = 0;// 默认置零，即未选择日
	private int date_flag_end = 0;// 其值为0或3方可
	
	// 城市选项值
		private String[] city = new String[] { "", "北京", "长沙", "成都", "重庆", "大连",
				"福州", "广州", "杭州", "合肥", "济南", "南京", "上海", "天津", "武汉", "西安", "郑州", };
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
		setContentView(R.layout.sx_main);
		Button_back = (Button) findViewById(R.id.back);
		Button_seek = (Button) findViewById(R.id.seek);
		flush_gs=(Button) findViewById(R.id.flush_gs);
		EditText_gs=(EditText) findViewById(R.id.input_gs);	
		shuoming=(TextView) findViewById(R.id.shuoming);
		shuoming.setMovementMethod(ScrollingMovementMethod.getInstance());// 设置滚动条
		setSpinner();// 加载城市、日期1、日期2下拉条
	

		// 返回主页按钮响应
		Button_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				SxActivity.this.finish();// 结束当前SxActivity
			}
		});
		
		// 公司输入清空按钮
		flush_gs.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						EditText_gs.setText("");// 清空公司输入
					}

				});


		// 查询按钮响应
		Button_seek.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				input_gongsi=EditText_gs.getText().toString();//获取公司输入内容
					date_flag = date_dayflag + date_monthflag + date_yearflag;//起始日期
					date_flag_end = date_dayflag_end + date_monthflag_end + date_yearflag_end;//截至日期
					if (cityOperation.equals("") && input_gongsi.equals("")) {
						Toast.makeText(SxActivity.this, "提示:城市或者公司至少选填一个!",
								Toast.LENGTH_LONG).show();
					}else if (date_flag == 1 || date_flag == 2) {
						Toast.makeText(SxActivity.this, "起始日期提示:请选择完整日期或者不选择任何日期!",
								Toast.LENGTH_LONG).show();
					} else if (date_flag_end == 1 || date_flag_end == 2) {
						Toast.makeText(SxActivity.this, "截至日期提示:请选择完整日期或者不选择任何日期!",
								Toast.LENGTH_LONG).show();
					}else {
						
						Intent intent_cx_sx = new Intent(SxActivity.this,
								CxSxActivity.class);
						Bundle bundle = new Bundle();// 创建并实例化一个Bundle对象
						bundle.putCharSequence("city", cityOperation);// 保存城市选项值
						bundle.putCharSequence("company", input_gongsi);// 保存学校选项值
						//日期1
						bundle.putCharSequence("date_year", date_yearOperation);// 保存日期年选项值
						bundle.putCharSequence("date_month", date_monthOperation);// 保存日期月选项值
						bundle.putCharSequence("date_day", date_dayOperation);// 保存日期日选项值
						//日期2
						bundle.putCharSequence("date_year_end", date_yearOperation_end);// 保存日期年选项值
						bundle.putCharSequence("date_month_end", date_monthOperation_end);// 保存日期月选项值
						bundle.putCharSequence("date_day_end", date_dayOperation_end);// 保存日期日选项值
						intent_cx_sx.putExtras(bundle);// 将Bundle对象添加到Intent对象中
						startActivity(intent_cx_sx);
					}
			}
		});

	}
	// 加载城市、日期1、日期2下拉条 方法
	private void setSpinner()

	{
		spinner_city = (Spinner) findViewById(R.id.spinner_city);// 城市选择
		//起始日期
		spinner_date_year = (Spinner) findViewById(R.id.spinner_date_year);// 日期年选择
		spinner_date_month = (Spinner) findViewById(R.id.spinner_date_month);// 日期月选择
		spinner_date_day = (Spinner) findViewById(R.id.spinner_date_day);// 日期日选择
		//截止日期
		spinner_date_year_end = (Spinner) findViewById(R.id.spinner_date_year_end);// 日期年选择
		spinner_date_month_end = (Spinner) findViewById(R.id.spinner_date_month_end);// 日期月选择
		spinner_date_day_end = (Spinner) findViewById(R.id.spinner_date_day_end);// 日期日选择
		// 绑定适配器和值
		// 城市
		adapter_city = new ArrayAdapter<String>(SxActivity.this,
				android.R.layout.simple_spinner_item, city);
		spinner_city.setAdapter(adapter_city);
		spinner_city.setSelection(0, true); // 设置默认选中项，此处为默认选中第1个值


		// 日期1
		// 年
		adapter_date_year = new ArrayAdapter<String>(SxActivity.this,
				android.R.layout.simple_spinner_item, year);
		spinner_date_year.setAdapter(adapter_date_year);
		spinner_date_year.setSelection(0, true); // 设置默认选中项，此处为默认选中第1个值
		// 月
		adapter_date_month = new ArrayAdapter<String>(SxActivity.this,
				android.R.layout.simple_spinner_item, month);
		spinner_date_month.setAdapter(adapter_date_month);
		spinner_date_month.setSelection(0, true); // 设置默认选中项，此处为默认选中第1个值

		// 日
		adapter_date_day = new ArrayAdapter<String>(SxActivity.this,
				android.R.layout.simple_spinner_item, day[0]);
		spinner_date_day.setAdapter(adapter_date_day);
		spinner_date_day.setSelection(0, true); // 默认选中第1个

		// 日期2
				// 年
				adapter_date_year_end = new ArrayAdapter<String>(SxActivity.this,
						android.R.layout.simple_spinner_item, year);
				spinner_date_year_end.setAdapter(adapter_date_year_end);
				spinner_date_year_end.setSelection(0, true); // 设置默认选中项，此处为默认选中第1个值
				// 月
				adapter_date_month_end = new ArrayAdapter<String>(SxActivity.this,
						android.R.layout.simple_spinner_item, month);
				spinner_date_month_end.setAdapter(adapter_date_month_end);
				spinner_date_month_end.setSelection(0, true); // 设置默认选中项，此处为默认选中第1个值

				// 日
				adapter_date_day_end = new ArrayAdapter<String>(SxActivity.this,
						android.R.layout.simple_spinner_item, day[0]);
				spinner_date_day_end.setAdapter(adapter_date_day_end);
				spinner_date_day_end.setSelection(0, true); // 默认选中第1个

		// 城市下拉框
		spinner_city
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					// 默示选项被改变时触发此方法
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						// position为当前城市选中的值的序号
						cityOperation = arg0.getItemAtPosition(position)
								.toString();// 获取城市选中值

					}

					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});

		//日期1
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
								SxActivity.this,
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
		
		//日期2
				// 日期年下拉框
				spinner_date_year_end
						.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
							@Override
							public void onItemSelected(AdapterView<?> arg0, View arg1,
									int position, long arg3) {

								date_yearOperation_end = arg0.getItemAtPosition(position)
										.toString();// 获取日期选中值
								if (!date_yearOperation_end.equals("")) {// 用于判断是否选择了年,选择不为空则置flag为1
									date_yearflag_end = 1;
								} else {
									date_yearflag_end = 0;// 如果为空，置0
								}
							}

							@Override
							public void onNothingSelected(AdapterView<?> arg0) {
							}
						});
				// 日期月下拉框
				spinner_date_month_end
						.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
							// 默示选项被改变时触发此方法，首要实现办法：动态改变日适配器的绑定值
							public void onItemSelected(AdapterView<?> arg0, View arg1,
									int position, long arg3) {
								// position为当前月份选中的值的序号
								// 将日期日适配器的值改变为day[position]中的值
								adapter_date_day_end = new ArrayAdapter<String>(
										SxActivity.this,
										android.R.layout.simple_spinner_item,
										day[position]);
								// 设置日期日下拉列表的选项内容适配器
								spinner_date_day_end.setAdapter(adapter_date_day_end);
								date_monthOperation_end = arg0.getItemAtPosition(position)
										.toString();// 获取日期月选中值
								if (!date_monthOperation_end.equals("")) {// 用于判断是否选择了月,选择不为空则置flag为1
									date_monthflag_end = 1;
								} else {
									date_monthflag_end = 0;// 如果为空，置0
								}

							}

							public void onNothingSelected(AdapterView<?> arg0) {
							}
						});

				// 日期日下拉框
				spinner_date_day_end
						.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
							@Override
							public void onItemSelected(AdapterView<?> arg0, View arg1,
									int position, long arg3) {
								date_dayOperation_end = arg0.getItemAtPosition(position)
										.toString();// 获取日期日选中值
								if (!date_dayOperation_end.equals("")) {// 用于判断是否选择了日,选择不为空则置flag为1
									date_dayflag_end = 1;
								} else {
									date_dayflag_end = 0;// 如果为空，置0
								}
							}

							@Override
							public void onNothingSelected(AdapterView<?> arg0) {
							}
						});

	}
}
