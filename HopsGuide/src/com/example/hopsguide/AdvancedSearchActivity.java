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
import android.widget.EditText;
import android.widget.Spinner;

public class AdvancedSearchActivity extends ActionBarActivity {

	private Spinner countryDropdown;
	private EditText aroma;
	private EditText typicalFor;
	private EditText type;
	private Spinner alpha;
	private Spinner beta;
	private EditText storageIndex;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advanced_search);
		
		countryDropdown = (Spinner) findViewById(R.id.countrySpinner);
	//	aroma = (EditText) findViewById(R);
		
		
		populateSpinner();
	}
	
	public void populateSpinner() {
		SQLiteDatabase sqLiteDatabaseCurr = MainActivity.getDatabase().getWritableDatabase();
		String[] columns = {Database.COUNTRY};
		Cursor cursor = sqLiteDatabaseCurr.query(Database.HOPS_TABLE_NAME, columns, null, null, null, null, null);
		List<String> countryNames = new ArrayList<String>();
		countryNames.add("Select:");
		while(cursor.moveToNext()) {
			String currCountry = cursor.getString(0);
			if(!countryNames.contains(currCountry)){
				countryNames.add(currCountry);
				Log.i("AdvancedSearchActivity", cursor.getString(0));
			}
		}
		String[] countryNamesArray = countryNames.toArray(new String[countryNames.size()]);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.countrynames, countryNamesArray);
		countryDropdown.setAdapter(dataAdapter);
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
