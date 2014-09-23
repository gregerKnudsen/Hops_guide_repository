package com.example.hopsguide;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends ActionBarActivity implements View.OnClickListener {

	Connection conn;
	private Database database;
	private EditText inputText;
	private String input;
	private TextView resultText;
	private Button searchButton;
	private List<String> currData;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);      

		inputText = (EditText) findViewById(R.id.inputTextBox);
		resultText = (TextView) findViewById(R.id.textView1);
        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);
        currData = new ArrayList<String>();
	}
	
	public void initializeDatabase() throws SQLException{
		//database.createHopsTable();
	}
	
    public void fillLocalDatabase() throws SQLException {
    //	private void addHops(String name, String country, float alpha, float beta, String type, int storageIndex, 
    //			String typicalFor, String aroma, String information) throws SQLException{
    	
    	//database.addHops("Apollo","US",(float) 8.6,(float) 6.0,"Both",20,"IPAs, Ales","Citrus, Flowery","Unknown origin, but character similar to Cascade");
    }
    
//    public void searchByName(String name) throws SQLException{
//    	ResultSet rs = database.getHopsByName(name);
//    	if(rs.next()){
//    		resultText.setText(rs.getString(1));
//    	}
//    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	public Hops getHops(String name){
		return null;
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