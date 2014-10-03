package com.example.hopsguide;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
	//	displayListNames();
		populateListView();
		setListNamesListener();
	}
	
//	public void displayListNames(){
//		resultText.setText(getListNamesList());
//	}
	
	public String getListNamesList(){
		String result = "";
		List<String> hopsNamesList = getListNames();
		for(String name : hopsNamesList){
			result += (name + "\n");
		}
		return result;
	}
	
	public void populateListView(){
	//	String[] a = list.toArray(new String[list.size()]);
		List<String> result = getListNames();
		String[] listNamesArray = result.toArray(new String[result.size()]);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.listnames,listNamesArray);
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
	
	public String getList(String name){
		SQLiteDatabase sqLiteDatabaseCurr = MainActivity.getDatabase().getWritableDatabase();
		String[] columns = {Database.CONTENT};
		Cursor cursor = sqLiteDatabaseCurr.query(Database.LIST_TABLE_NAME,columns,
				Database.UID+" = '"+ name +"'",null,null,null,null);
		cursor.moveToNext();
		return cursor.getString(0);
	}
	
	public void setListNamesListener(){
		ListView list = (ListView) findViewById(R.id.listViewResult);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View viewClicked, int position,
					long id) {
				TextView textView = (TextView) viewClicked;
				Toast.makeText(getApplicationContext(),getList(textView.getText().toString()),Toast.LENGTH_LONG).show();
			}
		});
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