package com.example.hopsguide;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
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
		resultText.setMovementMethod(new ScrollingMovementMethod());
		searchButton = (Button) findViewById(R.id.searchButton);
		searchButton.setOnClickListener(this);

		getDatabaseAccess();
		createHopsTable();
		try {
			File file = new File("C:/Users/Greger Siem Knudsen/git/Hops_guide_repository/HopsGuide/data.csv");
			Toast.makeText(getApplicationContext(), "File exists: " + file.exists(),Toast.LENGTH_LONG).show();
			fillHopsTable();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(getApplicationContext(), "Failed to load CSV: " + e.getCause(),Toast.LENGTH_LONG).show();
		}
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
	
	public void fillHopsTable() throws IOException{
	//	insertHops("Admiral","UK",(float) 14.75,(float) 5.6,"Bittering",15,"Ales","Target,Northdown",Database.NO_DATA,"Bittering hops derived from Wye Challenger. Good high-alpha bittering hops.");
	//	insertHops("Ahtanum","US",(float) 6,(float) 5.25,"Aroma",30,"American ales,lagers","Amarillo,Cascade","Distinctive floral and citrus aromas","Distinctive aromatic hops with moderate bittering power from Washington.");
		CSVReader csvReader = new CSVReader();
		List<Hops> hopsList = csvReader.read("data.csv");
		Toast.makeText(getApplicationContext(), "Number of hops in csv-file: " + hopsList.size(),Toast.LENGTH_SHORT).show();
		for(Hops hops : hopsList){
			insertHops(hops.getName(),hops.getCountry(),hops.getAlpha(),hops.getBeta(),hops.getType(),
					hops.getStorageIndex(),hops.getTypicalFor(),hops.getSubstitutes(),hops.getAroma(),hops.getInformation());
		}
	}
	
	public void insertHops(String name,String country,float alpha,float beta, String type,int storageIndex,String typicalFor,
			String substitutes,String aroma, String information){
		ContentValues values = new ContentValues();
		values.put("_id",name);
		values.put("Country", country);
		values.put("Alpha", alpha);
		values.put("Beta", beta);
		values.put("Type", type);
		values.put("StorageIndex", storageIndex);
		values.put("TypicalFor", typicalFor);
		values.put("Substitutes", substitutes);
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
		String[] columns = {Database.UID,Database.COUNTRY,Database.ALPHA,Database.BETA,Database.TYPE,Database.STORAGE_INDEX,Database.TYPICAL_FOR,Database.SUBSTITUTES,Database.AROMA,Database.INFORMATION};
		Cursor cursor = sqLiteDatabaseCurr.query(Database.TABLE_NAME,columns,Database.UID+" = '"+name+"'",null,null,null,null);
		if(cursor.moveToNext()){
		//	Toast.makeText(getApplicationContext(), cursor.getString(0),Toast.LENGTH_SHORT).show();
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