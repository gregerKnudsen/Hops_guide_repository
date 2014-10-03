package com.example.hopsguide;

import java.util.ArrayList;
import java.util.List;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends ActionBarActivity implements View.OnClickListener {
	
	private EditText inputText;
	private TextView resultText;
	private Button searchButton;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);      

		inputText = (EditText) findViewById(R.id.inputTextBox);
		resultText = (TextView) findViewById(R.id.textView1);
		resultText.setMovementMethod(new ScrollingMovementMethod());
		searchButton = (Button) findViewById(R.id.searchButton);
		searchButton.setOnClickListener(this);
		
		displayHopsNames();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	public Hops getHops(String name){
		SQLiteDatabase sqLiteDatabaseCurr = MainActivity.getDatabase().getWritableDatabase();
		String[] columns = {Database.UID,Database.COUNTRY,Database.ALPHA,Database.BETA,Database.TYPE,Database.STORAGE_INDEX,Database.TYPICAL_FOR,Database.SUBSTITUTES,Database.AROMA,Database.INFORMATION};
		Cursor cursor = sqLiteDatabaseCurr.query(Database.HOPS_TABLE_NAME,columns,Database.UID+" = '"+(("" + name.substring(0,1).toUpperCase()) + name.substring(1,name.length()))+"'",null,null,null,null);
		if(cursor.moveToNext()){
			return new Hops(cursor.getString(0),cursor.getString(1),cursor.getFloat(2),cursor.getFloat(3),cursor.getString(4),cursor.getInt(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9));
		}
		return null; //humle med gitt navn finnes ikke i databasen
	}

	public void searchButtonClick(){
		onClick((Button) findViewById(R.id.searchButton));
	}
	
	public void displayHopsNames(){
		resultText.setText(getHopsNamesList());
	}
	
	public List<String> getHopsNames(){
		List<String> result = new ArrayList<String>();
		SQLiteDatabase sqLiteDatabaseCurr = MainActivity.getDatabase().getWritableDatabase();
		String[] columns = {Database.UID};
		Cursor cursor = sqLiteDatabaseCurr.query(Database.HOPS_TABLE_NAME,columns,null,null,null,null,null);
		while(cursor.moveToNext()){
			result.add(cursor.getString(0));
		}
		return result;
	}
	
	public String getHopsNamesList(){
		String result = "";
		List<String> hopsNamesList = getHopsNames();
		for(String name : hopsNamesList){
			result += (name + "\n");
		}
		return result;
	}

	public void displayHops(){
		String input = inputText.getText().toString();
		if(!input.equals("")){
			Hops hops = getHops(input);
			if(hops != null){
				resultText.setText("**** " + (hops.getName()) + " ****" + "\n\n" + hops);
			}
			else{
				resultText.setText("Sorry, hops named \"" + input + "\" not found");
			}
		}
		else{
			resultText.setText("Please type in name of desired hops");
		}
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

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.searchButton : 
			displayHops();
			break;
		}
	}
}