package com.example.hopsguide;

import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ListActivity extends ActionBarActivity {

	private Database database;
	private SQLiteDatabase sqLiteDatabase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		getDatabaseAccess();
	}
	
	public void getDatabaseAccess(){
		try {
			database = new Database(this);
			sqLiteDatabase = database.getWritableDatabase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertList(String name, List<String> hopsNames){
		if(hopsNames.size() > 0){
			String nameList = hopsNames.get(0);
			ContentValues values = new ContentValues();
			values.put("_id",name);
			for(int i = 1; i < hopsNames.size(); i++){
				nameList += "," + hopsNames.get(i);
			}
			values.put("_id", name);
			values.put("content", nameList);
			database.insertHops(sqLiteDatabase, values);
		}
		//setter ikke noe inn dersom listen er tom
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}