package com.example.hopsguide;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.support.v7.app.ActionBarActivity;
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

	private Database database;
	private EditText inputText;
	private String input;
	private TextView resultText;
	private Button searchButton;
	private SQLiteDatabase sqLiteDatabase;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);      

		inputText = (EditText) findViewById(R.id.inputTextBox);
		resultText = (TextView) findViewById(R.id.textView1);
		searchButton = (Button) findViewById(R.id.searchButton);
		searchButton.setOnClickListener(this);

		getDatabaseAccess();
		fillDatabase();
	}
	
	public void getDatabaseAccess(){
		try {
			database = new Database(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fillDatabase(){
		sqLiteDatabase = database.getWritableDatabase();
		sqLiteDatabase.execSQL(SQLQueryFactory.insertHops("Armando", "UK", (float) 2.5, (float) 5.2, "Bitter", 45, "Ale", "Indigo", "Orange", "Very nice taste"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

//	private static final String UID = "_id";	//denne kolonnen lagrer navnet på humlen, som er tabellens primærnøkkel
//	private static final String COUNTRY = "Country";
//	private static final String ALPHA = "Alpha";
//	private static final String BETA = "Beta";
//	private static final String STORAGE_INDEX = "StorageIndex";
//	private static final String TYPICAL_FOR = "TypicalFor";
//	private static final String AROMA = "Aroma";
//	private static final String INFORMATION = "Information";
	
	public Hops getHops(String name){
		sqLiteDatabase = database.getWritableDatabase();
		String[] columns = {Database.UID,Database.COUNTRY,Database.ALPHA,Database.BETA,Database.STORAGE_INDEX,Database.TYPICAL_FOR,Database.AROMA,Database.INFORMATION};
		Cursor cursor = sqLiteDatabase.query(Database.TABLE_NAME,columns,null,null,null,null,null);
		if(cursor.moveToNext()){
			return new Hops(cursor.getString(0),cursor.getString(1),cursor.getFloat(2),cursor.getFloat(3),cursor.getInt(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));
		}
		return null; //humle med gitt navn finnes ikke i databasen
	}

	public void searchButtonClick(){
		onClick((Button) findViewById(R.id.searchButton));
	}

	public void displayHops(){
		input = inputText.getText().toString();
		if(!input.equals("")){
			Hops hops = getHops(input);
			if(hops != null){
				resultText.setText("**** " + (input.substring(0,1).toUpperCase() + input.substring(1,input.length()) + " ****" + "\n\n" + hops));
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