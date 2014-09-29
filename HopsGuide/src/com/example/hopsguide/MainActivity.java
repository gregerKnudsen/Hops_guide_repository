package com.example.hopsguide;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

	private JSONObject jsonObject;
	private Button searchSectionButton;
	private Button listButton;
	private ImageView informationButton;

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
		Log.i("MainActivity", "SKJEKKER OM DATABASEKLASSEN RETURNERER NULL...");
	//	Toast.makeText(getApplicationContext(), "Got data from MySQL database: " + (MySQLDatabase.getData() != null),Toast.LENGTH_LONG).show();
		checkNetworkConnection();
	}

	public void checkNetworkConnection(){
		ConnectivityManager connMgr = (ConnectivityManager) 
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			Toast.makeText(getApplicationContext(),"Network available",Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getApplicationContext(),"Network not available",Toast.LENGTH_SHORT).show();
		}
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

	public void startActivity(Class activity){
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
}