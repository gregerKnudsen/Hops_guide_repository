package com.example.hopsguide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class SearchActivity extends ActionBarActivity {

	private Map<String,Hops> hops;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);
		hops = new HashMap<String,Hops>();
		addHops();
	}

	public void addHops(){
		hops.put("Summit", new Hops(17.0,34.0,"Tilicum,Tomahawk","aroma","Germany"));
		hops.put("Tomahawk", new Hops(23.0,12.0,"Ultra,Vanguard","bitterness","Netherlands"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	public Hops getHops(String name){
		return hops.get(name);
	}

	public void displayHops(String name){
		Hops hops = getHops(name);
		EditText editText = (EditText) findViewById(R.id.editText2);
		if(hops != null){
			editText.setText(name + hops);
		}
		editText.setText("Hops named \"" + name + "\" not found");
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