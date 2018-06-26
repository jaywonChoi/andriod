package com.example.scit.calander;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class Ex11_CalendarActivity extends Activity implements OnClickListener,
		OnItemClickListener {

	ArrayList<String> mItems;
	ArrayAdapter<String> adapter;
	TextView textYear;
	TextView textMon;
	GridView grid;

	MyDBHelper mDBHelper; // SQLite 제어 클래스

	Cursor cursor;
	SQLiteDatabase db;

	TextView GridViewItems;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mDBHelper = new MyDBHelper(this, "Today.db", null, 1);
		db = mDBHelper.getWritableDatabase();

		textYear = (TextView) this.findViewById(R.id.edit1);
		textMon = (TextView) this.findViewById(R.id.edit2);

		mItems = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mItems);

		// GridView 설정
		grid = (GridView) this.findViewById(R.id.grid1);
		grid.setAdapter(adapter);
		grid.setOnItemClickListener(this);

		Date date = new Date();// 오늘에 날짜를 세팅 해준다.
		int year = date.getYear() + 1900;
		int mon = date.getMonth() + 1;
		textYear.setText(year + "");
		textMon.setText(mon + "");





		fillDate(year, mon);

		Button btnmove = (Button) this.findViewById(R.id.bt1);
		btnmove.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0.getId() == R.id.bt1) {
			int year = Integer.parseInt(textYear.getText().toString());
			int mon = Integer.parseInt(textMon.getText().toString());
			fillDate(year, mon);
		}

	}

	private void fillDate(int year, int mon) {
		mItems.clear();

		mItems.add("일");
		mItems.add("월");
		mItems.add("화");
		mItems.add("수");
		mItems.add("목");
		mItems.add("금");
		mItems.add("토");

		// 표시될 요일계산
		Date current = new Date(year - 1900, mon - 1, 1);
		int day = current.getDay(); // 요일도 int로 저장.

		for (int i = 0; i < day; i++) {
			mItems.add("");
		}

		current.setDate(32);// 32일까지 입력하면 1일로 바꿔준다.
		int last = 32 - current.getDate();





		grid.getAdapter().getCount();


		for (int i = 1; i <= last; i++) {
			mItems.add(i + "");
			 /*cursor = db.rawQuery("SELECT * FROM today WHERE _id='" + textYear.getText().toString() + "/"
					+ textMon.getText().toString() + "/" + grid.getAdapter().getCount()
					+ "'", null);



			 if(cursor != null)
			 {
				 Log.d("view : " , String.valueOf(grid.getAdapter().getCount()));
			 }*/

		}
		adapter.notifyDataSetChanged();



	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		/*GridViewItems = (TextView) arg1;

		GridViewItems.setBackgroundColor(Color.parseColor("#814f00"));*/

		// TODO Auto-generated method stub
		if (mItems.get(arg2).equals("")) {
			;
		} else {
			Intent intent = new Intent(this, ExToday.class);
			intent.putExtra("Param1", textYear.getText().toString() + "/"
					+ textMon.getText().toString() + "/" + mItems.get(arg2));

			Log.d("arg : " , String.valueOf(arg2));

			startActivity(intent);
		}
	}



}