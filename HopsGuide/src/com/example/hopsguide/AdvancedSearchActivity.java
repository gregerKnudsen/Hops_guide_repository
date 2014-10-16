package com.example.hopsguide;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AdvancedSearchActivity extends ActionBarActivity {

	private Spinner countries;
	private EditText aromas;
	private Spinner typicalForValues;
	private Spinner types;
	private Spinner alphaValues;
	private Spinner betaValues;
	private Spinner storageIndexes;
	private Button advancedSearchButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advanced_search);
		
		countries = (Spinner) findViewById(R.id.countrySpinner);
		aromas = (EditText) findViewById(R.id.aromaInputLabel);
		typicalForValues = (Spinner) findViewById(R.id.typicalForSpinner);
		types = (Spinner) findViewById(R.id.typeSpinner);
		alphaValues = (Spinner) findViewById(R.id.alphaSpinner);
		betaValues = (Spinner) findViewById(R.id.betaSpinner);
		storageIndexes = (Spinner) findViewById(R.id.storageIndexSpinner);
		advancedSearchButton = (Button) findViewById(R.id.advancedSearchButton);
		
		populateSpinners();
	}
	
	public void populateTypeSpinner(){
		String[] typeArray = {"Any","Aroma","Bittering","Both"};
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.types, typeArray);
		types.setAdapter(dataAdapter);
	}
	
	public void populateAlphaSpinner(){
		String[] alphaValuesArray = {"Any","0-5","6-10","11-15","16-20"};
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.alphavalues, alphaValuesArray);
		alphaValues.setAdapter(dataAdapter);
	}
	
	public void populateBetaSpinner(){
		String[] betaValuesArray = {"Any","0-2.5","2.6-5","5.1-7.5","7.6-10"};
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.betavalues, betaValuesArray);
		betaValues.setAdapter(dataAdapter);
	}
	
	public void populateStorageIndexSpinner(){
		String[] storageIndexesArray = {"Any","1-19","20-39","40-59","60-80"};
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.betavalues, storageIndexesArray);
		storageIndexes.setAdapter(dataAdapter);
	}
	
	public void populateSpinners(){
		populateDynamicSpinner(Database.HOPS_TABLE_NAME,Database.COUNTRY,countries,R.layout.countrynames);
		populateDynamicSpinner(Database.HOPS_TABLE_NAME,Database.TYPICAL_FOR,typicalForValues,R.layout.typicalforvalues);
		populateTypeSpinner();
		populateAlphaSpinner();
		populateBetaSpinner();
		populateStorageIndexSpinner();
	}
	
	public void populateDynamicSpinner(String tableName, String columnName, Spinner spinnerName, int xmlFileRef){
		SQLiteDatabase sqLiteDatabaseCurr = SplashScreen.getDatabase().getWritableDatabase();
		String[] columns = {columnName};
		Cursor cursor = sqLiteDatabaseCurr.query(tableName, columns, null, null, null, null, null);
		List<String> elementNames = new ArrayList<String>();
		elementNames.add("Any");
		while(cursor.moveToNext()) {
			String[] currElements = cursor.getString(0).split(",");
			for(int i = 0; i < currElements.length; i++){
				String currElement = currElements[i];
				if(!elementNames.contains(currElement)){
					elementNames.add(currElement);
				}
			}
		}
		String[] countryNamesArray = elementNames.toArray(new String[elementNames.size()]);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, xmlFileRef, countryNamesArray);
		spinnerName.setAdapter(dataAdapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.advanced_search, menu);
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
}
