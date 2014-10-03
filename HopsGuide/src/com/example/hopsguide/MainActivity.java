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
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

	private Button searchSectionButton;
	private Button listButton;
	private ImageView informationButton;
	private SQLiteDatabase sqLiteDatabase;
	private static Database database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		searchSectionButton = (Button) findViewById(R.id.browseButton);
		searchSectionButton.setOnClickListener(this);

		listButton = (Button) findViewById(R.id.myListsButton);
		listButton.setOnClickListener(this);

		informationButton = (ImageView) findViewById(R.id.informationButton);
		informationButton.setOnClickListener(this);
		checkNetworkConnection();

		getDatabaseAccess();
		createTables();
		try {
			fillHopsTable();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		fillMyListsTable();
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

	public void createTables(){
		database.onCreate(sqLiteDatabase);
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
	
	public void insertList(String name, String hopsNamesCSV){
		Log.i("MySQLDatabase", "LEGGER TIL LISTE");
			ContentValues values = new ContentValues();
			values.put("_id",name);
			values.put("content", hopsNamesCSV);
			database.insertList(sqLiteDatabase, values);
	}

	public void fillHopsTable() throws IOException, InterruptedException, ExecutionException, JSONException, TimeoutException{
		MySQLDatabase sqldb = new MySQLDatabase();
		AsyncTask<URL,Void,JSONArray> result = sqldb.getDatabaseData();
		List<Hops> hopsList = parseJSONArray(result.get());
		for(Hops hops : hopsList){
			insertHops(hops.getName(),hops.getCountry(),hops.getAlpha(),hops.getBeta(),hops.getType(),
					hops.getStorageIndex(),hops.getTypicalFor(),hops.getSubstitutes(),hops.getAroma(),
					hops.getInformation());
		}
	}
	
	public void fillMyListsTable(){
		insertList("Hops for new resturant menu", "Admiral,Bobek,Chelan,Citra,Chinook,Cluster,Brewer's Gold");
		insertList("My favorites", "Celeia,Nugget,Orion,Olympic");
	}

	public void checkNetworkConnection(){
		ConnectivityManager connMgr = (ConnectivityManager) 
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
		//	Toast.makeText(getApplicationContext(),"Network available",Toast.LENGTH_SHORT).show();
		} else {
		//	Toast.makeText(getApplicationContext(),"Network not available",Toast.LENGTH_SHORT).show();
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	public void searchSectionButtonClick(){
		onClick((Button) findViewById(R.id.browseButton));
	}

	public void myListsButtonClick(){
		onClick((Button) findViewById(R.id.myListsButton));
	} 

	public void informationButtonClick(){
		onClick((ImageView) findViewById(R.id.informationButton));
	}

	public void startActivity(Class<?> activity){
		startActivity(new Intent(getApplicationContext(),activity));
	}

	@Override
	public void onClick(View v) {	 
		switch (v.getId()){
		case R.id.browseButton : 
			startActivity(SearchActivity.class);
			break;
		case R.id.informationButton :
			startActivity(InformationActivity.class);
			break;	
		case R.id.myListsButton :
			startActivity(ListActivity.class);
			break;
		}
	}
	
	public static Database getDatabase(){
		return database;
	}
}