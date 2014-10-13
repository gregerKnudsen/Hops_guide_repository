package com.example.hopsguide;

import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewActivity extends ActionBarActivity implements View.OnClickListener{
	
	private ListView listView;
	private TextView listTitle; 
	private Button deleteButton; 
	private Button addButton; 
	private String listName; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		listView = (ListView) findViewById(R.id.mylistview);
		listTitle = (TextView) findViewById(R.id.listTitle); 
		deleteButton = (Button) findViewById(R.id.deletebutton);
		addButton = (Button) findViewById(R.id.addbutton);
		Intent intent = getIntent(); 
		listName = intent.getStringExtra("Listname");
		listTitle.setText(listName);
		populateListView(); 
		addButtonListeners(); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view, menu);
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
	
	public void populateListView(){
		String result = ListActivity.getList(listName);
		String[] listContent = result.split(",");  
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.listcontent,listContent);
		ListView list = (ListView) findViewById(R.id.mylistview);
		list.setAdapter(adapter);
	}
	
	public void startActivity(Class<?> activity){
		startActivity(new Intent(getApplicationContext(),activity));
	}
	
	public void showCreateListDialog(String listName){
		final String listNameInput = listName;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Are you sure?");
		
		// Set up the buttons
		builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() { 
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        MainActivity.deleteList(listNameInput);
		        startActivity(ListActivity.class);
		        Toast.makeText(getApplicationContext(),"Succesfully deleted " + listNameInput,Toast.LENGTH_LONG).show();
		    }
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        dialog.cancel();
		    }
		});
		builder.show();
	}
	
	public void addButtonListeners(){
		addButton.setOnClickListener(this);
		deleteButton.setOnClickListener(this); 
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.addbutton : 
			startActivity(SearchActivity.class);
			break;
		case R.id.deletebutton : 
			showCreateListDialog(listName); 
			break;
		}
	}
}
