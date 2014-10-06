package com.example.hopsguide;

import java.util.ArrayList;
import java.util.List;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends ActionBarActivity implements View.OnClickListener {
	
	private EditText inputText;
	private Button searchButton;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);      

		inputText = (EditText) findViewById(R.id.inputTextBox);
		searchButton = (Button) findViewById(R.id.searchButton);
		searchButton.setOnClickListener(this);
		updateListView(getHopsNames());
		setListViewItemListeners();
	}
	
	public void showListSelection(){
	    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
	    List<String> listNames = ListActivity.getListNames();
	    CharSequence[] listNamesArray = new String[listNames.size()];
	    listNamesArray = listNames.toArray(listNamesArray);
	    builder.setTitle(R.string.selectList)
	           .setItems(listNamesArray, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	               // The 'which' argument contains the index position
	               // of the selected item
	            	   
	           }
	    });
	    builder.create();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	public void populateListView(String[] hopsNames){
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.hopsnames,hopsNames);
		ListView list = (ListView) findViewById(R.id.listView2);
		list.setAdapter(adapter);
	}
	
	public void updateListView(List<String> hopsNames){
		String[] hopsNamesArray = hopsNames.toArray(new String[hopsNames.size()]);
		populateListView(hopsNamesArray);
	}
	
	public void setListViewItemListeners(){
		ListView list = (ListView) findViewById(R.id.listView2);
		setHopsNamesClickListener(list);
		setHopsNamesLongClickListener(list);
	}
	
	public void setHopsNamesLongClickListener(ListView list){
		list.setOnItemLongClickListener(new OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View viewLongClicked, int position, long id) {
                
				TextView textView = (TextView) viewLongClicked;
				showListSelection();
            //	Toast.makeText(getApplicationContext(),"You just long clicked " + textView.getText().toString(),Toast.LENGTH_LONG).show();
                return true;
            }
        });
	}
	
	public void setHopsNamesClickListener(ListView list){
		list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View viewClicked, int position,long id) {
				TextView textView = (TextView) viewClicked;
				Toast.makeText(getApplicationContext(),getHops(textView.getText().toString()).toString(),Toast.LENGTH_LONG).show();
			}
		});
	}

	public Hops getHops(String name){
		SQLiteDatabase sqLiteDatabaseCurr = MainActivity.getDatabase().getWritableDatabase();
		String[] columns = {Database.UID,Database.COUNTRY,Database.ALPHA,Database.BETA,Database.TYPE,Database.STORAGE_INDEX,Database.TYPICAL_FOR,Database.SUBSTITUTES,Database.AROMA,Database.INFORMATION};
		Cursor cursor = sqLiteDatabaseCurr.query(Database.HOPS_TABLE_NAME,columns,Database.UID+" = '"+(("" + name.substring(0,1).toUpperCase()) + name.substring(1,name.length()))+"'",null,null,null,null);
		if(cursor.moveToNext()){
			return new Hops(cursor.getString(0),cursor.getString(1),cursor.getFloat(2),cursor.getFloat(3),cursor.getString(4),cursor.getInt(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9));
		}
		return null; //humle med gitt navn finnes ikke i databasen
	}

	public void searchButtonClick(){
		onClick((Button) findViewById(R.id.searchButton));
	}
	
	public List<String> getHopsNames(){
		List<String> result = new ArrayList<String>();
		SQLiteDatabase sqLiteDatabaseCurr = MainActivity.getDatabase().getWritableDatabase();
		String[] columns = {Database.UID};
		Cursor cursor = sqLiteDatabaseCurr.query(Database.HOPS_TABLE_NAME,columns,null,null,null,null,null);
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

	public void displaySearchResult(){
		String input = inputText.getText().toString();
		List<String> result = new ArrayList<String>();
		result.add(input.substring(0,1).toUpperCase() + input.substring(1,input.length()));
		updateListView(result);
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
			displaySearchResult();
			break;
		}
	}
}