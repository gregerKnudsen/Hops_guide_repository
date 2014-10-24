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
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

	private ImageView searchSectionButton;
	private ImageView listButton;
	private ImageView informationButton;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		searchSectionButton = (ImageView) findViewById(R.id.searchButton);
		searchSectionButton.setOnClickListener(this);

		listButton = (ImageView) findViewById(R.id.myListsButton);
		listButton.setOnClickListener(this);

		informationButton = (ImageView) findViewById(R.id.informationButton);
		informationButton.setOnClickListener(this);

	}

//db was here
		





	public boolean fileExists(String fileName){
		File f = new File(fileName);
		return (f.exists());
	}






	


	//	public static String updateColumn(String table, String column, String row, String value){
	//  "UPDATE " + table + " SET " + column + "=" + value + " WHERE " + row + "='" + value + "'";



	//	public String getListByName(String name){
	//		ContentValues values = new ContentValues();
	//		values.put("_id", name);
	//		database.getList(sqLiteDatabase,values);
	//	}





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
		onClick((ImageView) findViewById(R.id.searchButton));
	}

	public void myListsButtonClick(){
		onClick((ImageView) findViewById(R.id.myListsButton));
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
		case R.id.searchButton : 
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


}