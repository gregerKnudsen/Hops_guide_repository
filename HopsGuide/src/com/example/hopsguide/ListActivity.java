package com.example.hopsguide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class ListActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		populateListView();
		setListNamesListener();
	}
	
	public String getListNamesList(){
		String result = "";
		List<String> hopsNamesList = getListNames();
		for(String name : hopsNamesList){
			result += (name + "\n");
		}
		return result;
	}
	
	public void setListNamesLongClickListener(ListView list){
		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View viewLongClicked, int position, long id) {

				TextView textView = (TextView) viewLongClicked;
				showCreateListDialog(textView.getText().toString());
				// 	Toast.makeText(getApplicationContext(),"You just long clicked " + textView.getText().toString(),Toast.LENGTH_LONG).show();
				return true;
			}
		});
	}
	
	public void showCreateListDialog(String listName){
//		final String listNameInput = listName;
//		AlertDialog.Builder builder = new AlertDialog.Builder(this);
//		builder.setTitle("Delete");
//
//	    Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
//		input.setInputType(InputType.TYPE_CLASS_TEXT);
//		builder.setView(input);
//
//		// Set up the buttons
//		builder.setPositiveButton("Add", new DialogInterface.OnClickListener() { 
//		    @Override
//		    public void onClick(DialogInterface dialog, int which) {
//		        String listName = input.getText().toString();
//		        MainActivity.insertList(listName,listNameInput);
//				Toast.makeText(getApplicationContext(),"Succesfully deleted list " + listName,Toast.LENGTH_LONG).show();
//		    }
//		});
//		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//		    @Override
//		    public void onClick(DialogInterface dialog, int which) {
//		        dialog.cancel();
//		    }
//		});
//
//		builder.show();
	}
	
	public void populateListView(){
		List<String> result = getListNames();
		Log.i("MySQLDatabase", "Størrelse på listenavnliste: " + result.size());
		String[] listNamesArray = result.toArray(new String[result.size()]);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.listnames,listNamesArray);
		ListView list = (ListView) findViewById(R.id.listViewResult);
		list.setAdapter(adapter);
	}
	
	public static List<String> getListNames(){
		List<String> result = new ArrayList<String>();
		SQLiteDatabase sqLiteDatabaseCurr = MainActivity.getDatabase().getWritableDatabase();
		String[] columns = {Database.UID};
		Cursor cursor = sqLiteDatabaseCurr.query(Database.LIST_TABLE_NAME,columns,null,null,null,null,null);
		while(cursor.moveToNext()){
			Log.i("MySQLDatabase", "Henter ut liste fra database");
			result.add(cursor.getString(0));
		}
		return result;
	}
	
	public static String getList(String name){
		SQLiteDatabase sqLiteDatabaseCurr = MainActivity.getDatabase().getWritableDatabase();
		String[] columns = {Database.CONTENT};
		Cursor cursor = sqLiteDatabaseCurr.query(Database.LIST_TABLE_NAME,columns,
				Database.UID+" = '"+ name +"'",null,null,null,null);
		cursor.moveToNext();
		return cursor.getString(0).toString();
	}
	
	public void setListNamesListener(){
		ListView list = (ListView) findViewById(R.id.listViewResult);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View viewClicked, int position,
					long id) {
				TextView textView = (TextView) viewClicked;
				Toast.makeText(getApplicationContext(),getList(textView.getText().toString()),Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
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