package com.example.hopsguide;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivity extends ActionBarActivity {
	
	private TextView resultText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		resultText = (TextView) findViewById(R.id.listViewResult);
	//	displayListNames();
		populateListView();
	}
	
	public void displayListNames(){
		resultText.setText(getListNamesList());
	}
	
	public String getListNamesList(){
		String result = "";
		List<String> hopsNamesList = getListNames();
		for(String name : hopsNamesList){
			result += (name + "\n");
		}
		return result;
	}
	
	public void populateListView(){
		String[] listNames = (String[]) getListNames().toArray();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_list,listNames);
		ListView list = (ListView) findViewById(R.id.listViewResult);
		list.setAdapter(adapter);
	}
	
	public List<String> getListNames(){
		List<String> result = new ArrayList<String>();
		SQLiteDatabase sqLiteDatabaseCurr = MainActivity.getDatabase().getWritableDatabase();
		String[] columns = {Database.UID};
		Cursor cursor = sqLiteDatabaseCurr.query(Database.LIST_TABLE_NAME,columns,null,null,null,null,null);
		while(cursor.moveToNext()){
			result.add(cursor.getString(0));
		}
		return result;
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