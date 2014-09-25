package com.example.hopsguide;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends ActionBarActivity implements View.OnClickListener {

	private Database database;
	private EditText inputText;
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
		createHopsTable();
		fillHopsTable();
		displayHopsNames();
	}
	
	public void createHopsTable(){
		database.onCreate(sqLiteDatabase);
	//	Toast.makeText(getApplicationContext(), "Hops table created",Toast.LENGTH_SHORT).show();
	}
	
	public void getDatabaseAccess(){
		try {
			database = new Database(this);
			sqLiteDatabase = database.getWritableDatabase();
		//	Toast.makeText(getApplicationContext(), "Database access granted",Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fillHopsTable(){
		insertHops("Armano","UK",(float) 2.4,(float) 1.9,23,"Pale Ale,IPA","Bitter","Very bitter taste, beware!");
		insertHops("Fromage","USA",(float) 4.2,(float) 9.2,12,"Pilsner","Aroma","Sweet taste, delicious!");
	}
	
	public void insertHops(String name,String country,float alpha,float beta, int storageIndex,String typicalFor,
			String aroma, String information){
		ContentValues values = new ContentValues();
		values.put("_id",name);
		values.put("Country", country);
		values.put("Alpha", alpha);
		values.put("Beta", beta);
		values.put("StorageIndex", storageIndex);
		values.put("TypicalFor", typicalFor);
		values.put("Aroma", aroma);
		values.put("Information", information);
		database.insertHops(sqLiteDatabase, values);
	//	Toast.makeText(getApplicationContext(), "Filled Hops table",Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	public Hops getHops(String name){
		SQLiteDatabase sqLiteDatabaseCurr = database.getWritableDatabase();
		String[] columns = {Database.UID,Database.COUNTRY,Database.ALPHA,Database.BETA,Database.STORAGE_INDEX,Database.TYPICAL_FOR,Database.AROMA,Database.INFORMATION};
		Cursor cursor = sqLiteDatabaseCurr.query(Database.TABLE_NAME,columns,Database.UID+" = '"+name+"'",null,null,null,null);
		if(cursor.moveToNext()){
		//	Toast.makeText(getApplicationContext(), cursor.getString(0),Toast.LENGTH_SHORT).show();
			return new Hops(cursor.getString(0),cursor.getString(1),cursor.getFloat(2),cursor.getFloat(3),cursor.getInt(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));
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
		SQLiteDatabase sqLiteDatabaseCurr = database.getWritableDatabase();
		String[] columns = {Database.UID};
		Cursor cursor = sqLiteDatabaseCurr.query(Database.TABLE_NAME,columns,null,null,null,null,null);
		while(cursor.moveToNext()){
			result.add(cursor.getString(0));
		}
	//	Toast.makeText(getApplicationContext(),"Nr. of names: " + (result.size()),Toast.LENGTH_SHORT).show();
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
		//	Toast.makeText(getApplicationContext(),"" + (hops != null),Toast.LENGTH_SHORT).show();
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