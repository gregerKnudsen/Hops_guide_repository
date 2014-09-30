package com.example.hopsguide;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
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
	//	createMyListsTable();
		try {
			try {
				fillHopsTable();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		displayHopsNames();
	}
	
	public void createHopsTable(){
		database.onCreate(sqLiteDatabase);
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
	
	public void fillHopsTable() throws IOException, InterruptedException, ExecutionException, JSONException, TimeoutException{
		Toast.makeText(getApplicationContext(), "Gets here",Toast.LENGTH_LONG).show();
		MySQLDatabase sqldb = new MySQLDatabase();
		
		Log.i("MySQLDatabase", "KOMMER FAKTISK HIT TIL CASTINGEN");
	//	Thread.currentThread().sleep(10000);
		Toast.makeText(getApplicationContext(), "Downloading database...",Toast.LENGTH_SHORT).show();
		AsyncTask<URL,Void,JSONArray> result = sqldb.getDatabaseData();
		Log.i("MySQLDatabase", "HAR HENTET UT DATAENE!");
		List<Hops> hopsList = parseJSONArray(result.get());
		for(Hops hops : hopsList){
			insertHops(hops.getName(),hops.getCountry(),hops.getAlpha(),hops.getBeta(),hops.getType(),hops.getStorageIndex(),hops.getTypicalFor(),hops.getSubstitutes(),hops.getAroma(),hops.getInformation());
		}
		Toast.makeText(getApplicationContext(), "Finished",Toast.LENGTH_SHORT).show();
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
	
	public List<Hops> parseJSONArray(JSONArray jArray) throws JSONException{
		List<Hops> hopsList = new ArrayList<Hops>();
		Log.i("MySQLDatabase", "ANKOMMER FOR LØKKEN FOR INNSETTING AV DATA");
		Log.i("MySQLDatabase", "JARRAY SIN LENGDE ER " + jArray.length());
		JSONObject json;
		for(int i = 0; i < jArray.length(); i++){
			json = jArray.getJSONObject(i);
			System.out.println("FÅR TAK I JSON OBJEKT. ER I ITERASJON NUMMER " + i);
			hopsList.add(new Hops(json.getString("Name"),json.getString("Country"),
					Float.parseFloat(json.getString("Alpha")), Float.parseFloat(json.getString("Beta")),json.getString("Type"),
					Integer.parseInt(json.getString("Storage Index")), json.getString("Typical for"), json.getString("Substitutes"),
					json.getString("Aroma"),json.getString("Information")));
		}
		Log.i("MySQLDatabase", "ALT GIKK BRA, RETURNERER LISTE MED " + hopsList.size());
		return hopsList;
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