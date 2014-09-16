package com.example.hopsguide;

import java.util.HashMap;
import java.util.Map;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends ActionBarActivity implements View.OnClickListener {

	private Map<String,Hops> hops;
	private EditText inputText;
	private String input;
	private TextView resultText;
	private Button searchButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);
		hops = new HashMap<String,Hops>();
		inputText = (EditText) findViewById(R.id.inputTextBox);
		resultText = (TextView) findViewById(R.id.textView1);
		addHops();
        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);
	}

	public void addHops(){
		hops.put("admiral", new Hops("UK",14.75,5.6,"Bittering"));
		hops.put("ahtanum", new Hops("US",6,5.25,"Aroma"));
		hops.put("amarillo Gold", new Hops("US",8.5,6,"Both"));
		hops.put("apollo", new Hops("US",17,6.75,"Bittering"));
		
//		Aquila US 6.5 3 Aroma
//
//		Atlas Slovenia 9 3.7 Aroma
//
//		Aurora Slovenia 8.25 3.5 Both
//		
//		Banner US 10 4 Bittering
//
//		Bobek Slovenia 5.25 5 Both
//
//		Bramling Cross UK 6 3 Aroma
//
//		Bravo US 15.5 3.5 Bittering
//
//		Brewer's Gold UK 8 3.7 Bittering
//
//		Bullion UK 8 4.5 Bittering
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	public Hops getHops(String name){
		return hops.get(name.toLowerCase());
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