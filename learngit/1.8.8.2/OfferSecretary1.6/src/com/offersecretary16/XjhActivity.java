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

public class XjhActivity extends Activity {
	private Button Button_back;// 返回按钮
	private Button Button_seek;// 查询按钮
	private TextView shuoming;//选项说明框
	

	private Spinner spinner_city;// 城市选择
	private Spinner spinner_uni;// 学校选择
	private Spinner spinner_date_year;// 日期,年选择
	private Spinner spinner_date_month;// 日期,月选择
	private Spinner spinner_date_day;// 日期,日选择
	private Spinner spinner_time;// 时间选择
	private ArrayAdapter<String> adapter_city;// 城市选择适配器
	private ArrayAdapter<String> adapter_uni;// 学校选择适配器
	private ArrayAdapter<String> adapter_time;// 时间选择适配器
	private ArrayAdapter<String> adapter_date_year;// 时间选择适配器
	private ArrayAdapter<String> adapter_date_month;// 时间选择适配器
	private ArrayAdapter<String> adapter_date_day;// 时间选择适配器

	static int cityPosition = 0;// 设置当前城市选项的序号
	private String cityOperation = "";// 城市选项值
	private String uniOperation = "";// 大学选项值
	private String date_yearOperation = "";// 日期年选项值
	private String date_monthOperation = "";// 日期月选项值
	private String date_dayOperation = "";// 日期日选项值
	private String timeOperation = "";// 时间选项值
	private int date_yearflag = 0;// 默认置零，即未选择年份
	private int date_monthflag = 0;// 默认置零，即未选择月
	private int date_dayflag = 0;// 默认置零，即未选择日
	private int date_flag = 0;// 其值为0或3方可

	// 城市选项值
	private String[] city = new String[] { "", "北京", "长沙", "成都", "重庆", "大连",
			"福州", "广州", "杭州", "合肥", "济南", "南京", "上海", "天津", "武汉", "西安", "郑州", };
	// 学校选项值
	private String[][] uni = new String[][] {
			{ "", "安徽大学", "安徽工业大学", "安徽理工大学", "安徽农业大学", "安徽师范大学", "北京大学",
					"北京工业大学", "北京航空航天大学", "北京化工大学", "北京交通大学", "北京科技大学",
					"北京理工大学", "北京师范大学", "北京邮电大学", "长安大学", "长沙理工大学", "重庆大学",
					"重庆师范大学", "重庆邮电大学", "大连大学", "大连海事大学", "大连交通大学", "大连理工大学",
					"电子科技大学", "东北财经大学", "东北大学", "东华大学", "东南大学", "福建农林大学",
					"福建师范大学", "福州大学", "复旦大学", "广东财经大学", "广东工业大学", "广东外语外贸大学",
					"广州大学", "杭州电子科技大学", "杭州师范大学", "合肥工业大学", "河北工业大学", "河海大学",
					"河南财经大学", "河南大学", "河南工业大学", "河南科技大学", "河南师范大学", "湖北大学",
					"湖北工业大学", "湖南大学", "湖南科技大学", "湖南农业大学", "华北电力大学", "华北水利水电大学",
					"华东理工大学", "华南理工大学", "华南理工大学", "华南农业大学", "华南师范大学", "华侨大学",
					"华中科技大学", "华中农业大学", "华中师范大学", "集美大学", "暨南大学", "江南大学",
					"江苏大学", "辽宁大学", "辽宁工程技术大学", "南华大学", "南京大学", "南京工业大学",
					"南京航空航天大学", "南京理工大学", "南京农业大学", "南京师范大学", "南京信息工程大学",
					"南开大学", "青岛大学", "清华大学", "山东财经大学", "山东大学", "山东科技大学",
					"山东农业大学", "山东师范大学", "陕西师范大学", "汕头大学", "上海财经大学", "上海大学",
					"上海交通大学", "上海理工大学", "上海外国语大学", "深圳大学", "四川大学", "苏州大学",
					"天津财经大学", "天津大学", "天津工业大学", "天津科技大学", "天津商业大学", "天津师范大学",
					"同济大学", "武汉大学", "武汉科技大学", "武汉理工大学", "西安电子科技大学", "西安工程大学",
					"西安建筑科技大学", "西安交通大学", "西安理工大学", "西安外国语大学", "西北大学",
					"西北工业大学", "西北农林科技大学", "西北政法大学", "西南财经大学", "西南大学", "西南交通大学",
					"西南科技大学", "西南政法大学", "厦门大学", "湘潭大学", "仰恩大学", "浙江财经大学",
					"浙江大学", "浙江工商大学", "浙江工业大学", "浙江农林大学", "浙江师范大学", "郑州大学",
					"中国传媒大学", "中国地质大学", "中国地质大学（北京）", "中国海洋大学", "中国科学技术大学",
					"中国矿业大学", "中国矿业大学（北京）", "中国农业大学", "中国人民大学", "中国石油大学",
					"中国石油大学（北京）", "中南财经政法大学", "中南大学", "中南林业科技大学", "中南民族大学",
					"中山大学", "中央财经大学", "中央民族大学", },// 对应城市中的第一个空值，即当不选择城市的时候，大学按拼音排序

			{ "", "清华大学", "北京大学", "中国人民大学", "北京理工大学", "北京航空航天大学", "北京科技大学",
					"北京邮电大学", "北京交通大学", "北京师范大学", "中央财经大学", "中国矿业大学（北京）",
					"华北电力大学", "中国农业大学", "中国石油大学（北京）", "北京化工大学", "北京工业大学",
					"中国地质大学（北京）", "中央民族大学", "中国传媒大学" },

			{ "", "中南大学", "湖南大学", "长沙理工大学", "湘潭大学", "湖南农业大学", "中南林业科技大学",
					"湖南科技大学", "南华大学" },

			{ "", "电子科技大学", "四川大学", "西南交通大学", "西南财经大学", "西南科技大学" },

			{ "", "重庆大学", "重庆邮电大学", "西南大学", "西南政法大学", "重庆师范大学" },

			{ "", "大连理工大学", "东北大学", "辽宁大学", "大连海事大学", "东北财经大学", "大连大学",
					"大连交通大学", "辽宁工程技术大学" },

			{ "", "厦门大学", "福州大学", "福建师范大学", "华侨大学", "福建农林大学", "集美大学", "仰恩大学" },

			{ "", "中山大学", "华南理工大学", "暨南大学", "华南理工大学", "华南师范大学", "华南农业大学",
					"广东工业大学", "广州大学", "广东财经大学", "广东外语外贸大学", "深圳大学", "汕头大学" },

			{ "", "浙江大学", "浙江工业大学", "浙江师范大学", "浙江工商大学", "杭州电子科技大学", "杭州师范大学",
					"浙江农林大学", "浙江财经大学" },

			{ "", "中国科学技术大学", "合肥工业大学", "安徽大学", "安徽师范大学", "安徽理工大学", "安徽农业大学",
					"安徽工业大学" },

			{ "", "青岛大学", "山东大学", "中国海洋大学", "中国石油大学", "山东科技大学", "山东农业大学",
					"山东师范大学", "山东财经大学" },

			{ "", "南京大学", "东南大学", "南京航空航天大学", "南京理工大学", "南京师范大学", "南京农业大学",
					"河海大学", "南京工业大学", "南京信息工程大学", "中国矿业大学", "江苏大学", "苏州大学",
					"江南大学" },

			{ "", "上海交通大学", "复旦大学", "同济大学", "华东理工大学", "上海财经大学", "上海外国语大学",
					"东华大学", "上海大学", "上海理工大学" },

			{ "", "南开大学", "天津大学", "河北工业大学", "天津财经大学", "天津工业大学", "天津科技大学",
					"天津商业大学", "天津师范大学", },

			{ "", "华中科技大学", "武汉大学", "武汉理工大学", "华中师范大学", "中南财经政法大学", "中国地质大学",
					"华中农业大学", "武汉科技大学", "湖北大学", "中南民族大学", "湖北工业大学" },

			{ "", "西安交通大学", "西北工业大学", "西安电子科技大学", "西北大学", "长安大学", "西北农林科技大学",
					"陕西师范大学", "西安建筑科技大学", "西安理工大学", "西北政法大学", "西安工程大学",
					"西安外国语大学" },

			{ "", "郑州大学", "河南大学", "河南师范大学", "华北水利水电大学", "河南科技大学", "河南工业大学",
					"河南财经大学" }

	};

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
	// 时间选项
	private String[] time = new String[] { "", "07:00", "08:00", "09:00",
			"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00",
			"17:00", "18:00", "19:00", "20:00", "21:00" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xjh_main);
		Button_back = (Button) findViewById(R.id.back);
		Button_seek = (Button) findViewById(R.id.seek);
		shuoming=(TextView) findViewById(R.id.shuoming);
		shuoming.setMovementMethod(ScrollingMovementMethod.getInstance());// 设置滚动条
		
		setSpinner();// 加载城市、学校、日期、时间下拉条

		// 返回主页按钮响应
		Button_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// Intent intent_main=new
				// Intent(XjhActivity.this,MainActivity.class);
				// startActivity(intent_main);//新建主页（相当于返回主页）
				XjhActivity.this.finish();// 结束当前XjhActivity
			}
		});

		// 查询按钮响应
		Button_seek.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				date_flag = date_dayflag + date_monthflag + date_yearflag;
				if (cityOperation.equals("") && uniOperation.equals("")) {
					Toast.makeText(XjhActivity.this, "提示:至少选择一座城市或者一所学校!",
							Toast.LENGTH_LONG).show();
				} else if (date_flag == 1 || date_flag == 2) {
					Toast.makeText(XjhActivity.this, "提示:请选择完整日期或者不选择任何日期!",
							Toast.LENGTH_LONG).show();
				} else {
					Intent intent_cx = new Intent(XjhActivity.this,
							CxXjhActivity.class);
					Bundle bundle = new Bundle();// 创建并实例化一个Bundle对象
					bundle.putCharSequence("city", cityOperation);// 保存城市选项值
					bundle.putCharSequence("uni", uniOperation);// 保存学校选项值
					bundle.putCharSequence("date_year", date_yearOperation);// 保存日期年选项值
					bundle.putCharSequence("date_month", date_monthOperation);// 保存日期月选项值
					bundle.putCharSequence("date_day", date_dayOperation);// 保存日期日选项值
					bundle.putCharSequence("time", timeOperation);// 保存时间选项值
					intent_cx.putExtras(bundle);// 将Bundle对象添加到Intent对象中
					startActivity(intent_cx);
				}

			}
		});

	}

	// 加载城市、学校、日期、时间下拉条 方法
	private void setSpinner()

	{
		spinner_city = (Spinner) findViewById(R.id.spinner_city);// 城市选择
		spinner_uni = (Spinner) findViewById(R.id.spinner_uni);// 学校选择
		spinner_date_year = (Spinner) findViewById(R.id.spinner_date_year);// 日期选择
		spinner_date_month = (Spinner) findViewById(R.id.spinner_date_month);// 日期选择
		spinner_date_day = (Spinner) findViewById(R.id.spinner_date_day);// 日期选择
		spinner_time = (Spinner) findViewById(R.id.spinner_time);// 时间选择
		// 绑定适配器和值
		// 城市
		adapter_city = new ArrayAdapter<String>(XjhActivity.this,
				android.R.layout.simple_spinner_item, city);
		spinner_city.setAdapter(adapter_city);
		spinner_city.setSelection(0, true); // 设置默认选中项，此处为默认选中第1个值

		// 学校
		adapter_uni = new ArrayAdapter<String>(XjhActivity.this,
				android.R.layout.simple_spinner_item, uni[0]);
		spinner_uni.setAdapter(adapter_uni);
		spinner_uni.setSelection(0, true); // 默认选中第1个

		// 日期
		// 年
		adapter_date_year = new ArrayAdapter<String>(XjhActivity.this,
				android.R.layout.simple_spinner_item, year);
		spinner_date_year.setAdapter(adapter_date_year);
		spinner_date_year.setSelection(0, true); // 设置默认选中项，此处为默认选中第1个值
		// 月
		adapter_date_month = new ArrayAdapter<String>(XjhActivity.this,
				android.R.layout.simple_spinner_item, month);
		spinner_date_month.setAdapter(adapter_date_month);
		spinner_date_month.setSelection(0, true); // 设置默认选中项，此处为默认选中第1个值

		// 日
		adapter_date_day = new ArrayAdapter<String>(XjhActivity.this,
				android.R.layout.simple_spinner_item, day[0]);
		spinner_date_day.setAdapter(adapter_date_day);
		spinner_date_day.setSelection(0, true); // 默认选中第1个

		// 时间
		adapter_time = new ArrayAdapter<String>(XjhActivity.this,
				android.R.layout.simple_spinner_item, time);
		spinner_time.setAdapter(adapter_time);
		spinner_time.setSelection(0, true); // 默认选中第1个

		// 城市下拉框
		spinner_city
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					// 默示选项被改变时触发此方法，首要实现办法：动态改变学校适配器的绑定值
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						// position为当前城市选中的值的序号
						// 将学校适配器的值改变为uni[position]中的值
						adapter_uni = new ArrayAdapter<String>(
								XjhActivity.this,
								android.R.layout.simple_spinner_item,
								uni[position]);
						// 设置学校下拉列表的选项内容适配器
						spinner_uni.setAdapter(adapter_uni);
						cityOperation = arg0.getItemAtPosition(position)
								.toString();// 获取城市选中值

					}

					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});

		// 学校下拉框
		spinner_uni
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						uniOperation = arg0.getItemAtPosition(position)
								.toString();// 获取大学选中值
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});

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
								XjhActivity.this,
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

		// 时间下拉框
		spinner_time
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						timeOperation = arg0.getItemAtPosition(position)
								.toString();// 获取时间选中值
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});

	}

}
