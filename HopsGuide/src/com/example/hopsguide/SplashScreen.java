package com.example.hopsguide;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
 
public class SplashScreen extends Activity {
 
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    private static Database database;
    private static SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
  


        new Handler().postDelayed(new Runnable() {
 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
        		checkNetworkConnection();
        		try {
        			checkDatabaseExistence();
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		sqLiteDatabase = database.getWritableDatabase();
 
                // close this activity
                finish();
            }
        }
        , SPLASH_TIME_OUT);
        
    }
	public void checkDatabaseExistence() throws IOException{
		if(!fileExists(Database.DATABASE_FILE_LOCATION)){
			getDatabaseAccess();
			createTables();
			initializeDatabase();    //kall denne første gang applikasjonen lages
		}
		else{
//			Toast.makeText(getApplicationContext(),"Database exists!",Toast.LENGTH_LONG).show();
//			database = Database.readFromFile(Database.DATABASE_FILE_LOCATION);
		}
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
	
	public boolean fileExists(String fileName){
		File f = new File(fileName);
		return (f.exists());
	}
	
	public void createTables(){
		database.onCreate(sqLiteDatabase);
	}
	
	public void initializeDatabase(){
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
	//	database.saveToFile("database.obj");
	}
	
	public void fillHopsTable() throws IOException, InterruptedException, ExecutionException, JSONException, TimeoutException{
		MySQLDatabase sqldb = new MySQLDatabase();
		AsyncTask<URL,Void,JSONArray> result = sqldb.getDatabaseData();
		List<Hops> hopsList = parseJSONArray(result.get());
		for(Hops hops : hopsList){
			insertHops(hops.getName(),hops.getCountry(),hops.getAlphaMin(),hops.getAlphaMax(),hops.getBeta(),hops.getType(),
					hops.getStorageIndex(),hops.getTypicalFor(),hops.getSubstitutes(),hops.getAroma(),
					hops.getInformation());
		}
	}

	public void fillMyListsTable(){
		insertList("Favorites","");
		insertList("Hops for new resturant menu", "Admiral,Bobek,Chelan,Citra,Chinook,Cluster");
		insertList("My favorites", "Celeia,Nugget,Orion,Olympic");
	}
	
	public void insertHops(String name,String country,float alphaMin, float alphaMax, float beta, String type,int storageIndex,String typicalFor,
			String substitutes,String aroma, String information){
		ContentValues values = new ContentValues();
		values.put("_id",name);
		values.put("Country", country);
		values.put("AlphaMin", alphaMin);
		values.put("AlphaMax", alphaMax);
		values.put("Beta", beta);
		values.put("Type", type);
		values.put("StorageIndex", storageIndex);
		values.put("TypicalFor", typicalFor);
		values.put("Substitutes", substitutes);
		values.put("Aroma", aroma);
		values.put("Information", information);
		database.insertHops(sqLiteDatabase, values);
	}

	public static void insertList(String name, String hopsNamesCSV){
		ContentValues values = new ContentValues();
		values.put("_id",name);
		values.put("content", hopsNamesCSV);
		database.insertList(sqLiteDatabase, values);
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
					Float.parseFloat(json.getString("AlphaMin")), Float.parseFloat(json.getString("AlphaMax")),Float.parseFloat(json.getString("Beta")),json.getString("Type"),
					Integer.parseInt(json.getString("Storage Index")), json.getString("Typical for"), json.getString("Substitutes"),
					json.getString("Aroma"),json.getString("Information")));
		}
		Log.i("MySQLDatabase", "ALT GIKK BRA, RETURNERER LISTE MED " + hopsList.size());
		return hopsList;
	}
	
	public void checkNetworkConnection(){
		ConnectivityManager connMgr = (ConnectivityManager) 
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			//	Toast.makeText(getApplicationContext(),"Network available",Toast.LENGTH_SHORT).show();
		} else {
			//	Toast.makeText(getApplicationContext(),"Network not available",Toast.LENGTH_SHORT).show();
			//legger til kommentar
		}
	}
	
	public static void deleteList(String listName){
		sqLiteDatabase = database.getWritableDatabase();
		sqLiteDatabase.execSQL(SQLQueryFactory.deleteList(listName));
		
	}
	public static void getCountryNames() {
		sqLiteDatabase = database.getReadableDatabase();
		sqLiteDatabase.execSQL(SQLQueryFactory.selectCountryNames());
	}

	public static void appendHopsToList(String listName, String hopsName) throws IOException{
		sqLiteDatabase = database.getWritableDatabase();
		sqLiteDatabase.execSQL(SQLQueryFactory.updateColumn(Database.LIST_TABLE_NAME, 
				Database.CONTENT, ("'" + (ListActivity.getList(listName) + "," + hopsName) + "'"), Database.UID, listName));
	}
	
	public void checkLocalDatabaseExistence(){
		if(!fileExists("database.obj")){
			sqLiteDatabase = database.getWritableDatabase();
		}
	}
	
	public static Database getDatabase(){
		return database;
	}
} 
