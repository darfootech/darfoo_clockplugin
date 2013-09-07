package com.darfoo.launcher.clockplugin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.darfoo.plugin.R;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView;
import android.widget.TextView;

public class ClockPlugin {
	View view = null;
	ImageView hour0ImageView = null;
	ImageView hour1ImageView = null;
	ImageView minute0ImageView = null;
	ImageView minute1ImageView = null;
	TextView secondTextView = null;
	ImageView dayofmonth0ImageView = null;
	ImageView dayofmonth1ImageView = null;
	ImageView monthImageView = null;
	ImageView monthpreImageView = null;
	ImageView year0ImageView = null;
	ImageView year1ImageView = null;
	ImageView year2ImageView = null;
	ImageView year3ImageView = null;
	ImageView dayofweekImageView = null;
	TextView lunarTextView = null;

	LunarCalendar lunarCalendar = null;

	Activity mContext = null;

	int[] yearArray = new int[4];
	int[] dayofmonthArray = new int[2];
	int[] hourArray = new int[2];
	int[] minuteArray = new int[2];

	String mData = null;

	public String getData() {
		return mData;
	}

	public void initView(String data) {
		if (data != null) {
			this.mData = data;
		} else {
			mData = "cleantha";
		}
	}

	public View createView(Activity mContext) {
		this.mContext = mContext;
		view = this.mContext.getLayoutInflater().inflate(R.layout.clock_view,
				null);

		this.lunarCalendar = new LunarCalendar();
		hour0ImageView = (ImageView) view.findViewById(R.id.hour0view);
		hour1ImageView = (ImageView) view.findViewById(R.id.hour1view);
		minute0ImageView = (ImageView) view.findViewById(R.id.minute0view);
		minute1ImageView = (ImageView) view.findViewById(R.id.minute1view);
		year0ImageView = (ImageView) view.findViewById(R.id.year0view);
		year1ImageView = (ImageView) view.findViewById(R.id.year1view);
		year2ImageView = (ImageView) view.findViewById(R.id.year2view);
		year3ImageView = (ImageView) view.findViewById(R.id.year3view);
		monthImageView = (ImageView) view.findViewById(R.id.monthview);
		lunarTextView = (TextView) view.findViewById(R.id.lunarview);
		dayofmonth0ImageView = (ImageView) view
				.findViewById(R.id.dayofmonth0view);
		dayofmonth1ImageView = (ImageView) view
				.findViewById(R.id.dayofmonth1view);

		dayofweekImageView = (ImageView) view.findViewById(R.id.dayofweekview);
		// secondTextView = (TextView) view.findViewById(R.id.secondview);
		monthpreImageView = (ImageView) view.findViewById(R.id.monthpreview);

		new Thread(timeRunnable).start();

		return view;
	}

	Handler timeHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Bundle data = msg.getData();
			int hour = data.getInt("hour");
			int minute = data.getInt("minute");
			int year = data.getInt("year");
			int month = data.getInt("month");
			int dayofmonth = data.getInt("day_of_month");
			int dayofweek = data.getInt("day_of_week");
			int second = data.getInt("second");

			for (int i = 0; i < 4; i++) {
				yearArray[i] = year % 10;
				year = year / 10;
			}

			setImage(yearArray[0], year0ImageView);
			setImage(yearArray[1], year1ImageView);
			setImage(yearArray[2], year2ImageView);
			setImage(yearArray[3], year3ImageView);

			if (dayofmonth < 10) {
				setImage(dayofmonth, dayofmonth0ImageView);
			} else {
				for (int i = 0; i < 2; i++) {
					dayofmonthArray[i] = dayofmonth % 10;
					dayofmonth = dayofmonth / 10;
				}

				setImage(dayofmonthArray[0], dayofmonth0ImageView);
				setImage(dayofmonthArray[1], dayofmonth1ImageView);

			}

			for (int i = 0; i < 2; i++) {
				hourArray[i] = hour % 10;
				hour = hour / 10;
			}

			setImage(hourArray[0], hour0ImageView);
			setImage(hourArray[1], hour1ImageView);

			for (int i = 0; i < 2; i++) {
				minuteArray[i] = minute % 10;
				minute = minute / 10;
			}

			setImage(minuteArray[0], minute0ImageView);
			setImage(minuteArray[1], minute1ImageView);

			// secondTextView.setText(String.valueOf(second));
			lunarTextView.setText(lunarCalendar.getLunar());
			switch (month) {
			case Calendar.JANUARY:
				// monthImageView.setImage(String.valueOf(1));
				monthImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.one));
				break;
			case Calendar.FEBRUARY:
				// monthImageView.setImage(String.valueOf(2));
				monthImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.two));
				break;
			case Calendar.MARCH:
				monthImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.three));
				break;

			case Calendar.APRIL:
				monthImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.four));
				break;

			case Calendar.MAY:
				monthImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.five));
				break;

			case Calendar.JUNE:
				monthImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.six));
				break;

			case Calendar.JULY:
				monthImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.seven));
				break;

			case Calendar.AUGUST:
				monthImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.eight));
				break;

			case Calendar.SEPTEMBER:
				monthImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.night));
				break;

			case Calendar.OCTOBER:
				monthpreImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.one));
				monthImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.zero));
				break;

			case Calendar.NOVEMBER:
				// monthImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.));
				monthpreImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.one));
				monthImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.one));

				break;

			case Calendar.DECEMBER:
				// monthImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.two));
				monthpreImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.one));
				monthImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.two));

				break;

			default:
				break;
			}

			switch (dayofweek) {
			case Calendar.MONDAY:
				// dayofweekImageView.setImage("周一");
				dayofweekImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.monday));
				break;
			case Calendar.TUESDAY:
				// dayofweekImageView.setImage("周二");
				dayofweekImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.tuesday));

				break;
			case Calendar.WEDNESDAY:
				// dayofweekImageView.setImage("周三");
				dayofweekImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.wednesday));

				break;
			case Calendar.THURSDAY:
				// dayofweekImageView.setImage("周四");
				dayofweekImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.thuesday));

				break;
			case Calendar.FRIDAY:
				// dayofweekImageView.setImage("周五");
				dayofweekImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.friday));

				break;
			case Calendar.SATURDAY:
				// dayofweekImageView.setImage("周六");
				dayofweekImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.saturday));

				break;
			case Calendar.SUNDAY:
				// dayofweekImageView.setImage("周日");
				dayofweekImageView.setImageDrawable(mContext.getResources()
						.getDrawable(R.drawable.sunday));

				break;

			default:
				break;
			}
			// dayofmonthImageView.setImage(String.valueOf(dayofmonth));
		};
	};

	private void setImage(int number, ImageView imageView) {
		switch (number) {
		case 0:
			imageView.setImageDrawable(mContext.getResources().getDrawable(
					R.drawable.zero));
			break;
		case 1:
			imageView.setImageDrawable(mContext.getResources().getDrawable(
					R.drawable.one));
			break;
		case 2:
			imageView.setImageDrawable(mContext.getResources().getDrawable(
					R.drawable.two));
			break;
		case 3:
			imageView.setImageDrawable(mContext.getResources().getDrawable(
					R.drawable.three));
			break;
		case 4:
			imageView.setImageDrawable(mContext.getResources().getDrawable(
					R.drawable.four));
			break;
		case 5:
			imageView.setImageDrawable(mContext.getResources().getDrawable(
					R.drawable.five));
			break;
		case 6:
			imageView.setImageDrawable(mContext.getResources().getDrawable(
					R.drawable.six));
			break;
		case 7:
			imageView.setImageDrawable(mContext.getResources().getDrawable(
					R.drawable.seven));
			break;
		case 8:
			imageView.setImageDrawable(mContext.getResources().getDrawable(
					R.drawable.eight));
			break;

		case 9:
			imageView.setImageDrawable(mContext.getResources().getDrawable(
					R.drawable.night));
			break;

		default:
			break;
		}
	}

	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				Calendar date = Calendar.getInstance();

				Bundle data = new Bundle();
				data.putInt("second", date.get(Calendar.SECOND));
				data.putInt("day_of_week", date.get(Calendar.DAY_OF_WEEK));
				data.putInt("day_of_month", date.get(Calendar.DAY_OF_MONTH));
				data.putInt("month", date.get(Calendar.MONTH));
				data.putInt("year", date.get(Calendar.YEAR));
				data.putInt("hour", date.get(Calendar.HOUR_OF_DAY));
				data.putInt("minute", date.get(Calendar.MINUTE));
				Message message = new Message();
				message.setData(data);
				timeHandler.sendMessage(message);

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace);
					Log.d("timeservice", "something bad in oncreate");
				}
			}
		}

	};

}
