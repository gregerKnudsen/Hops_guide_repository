package com.example.hopsguide;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
	
	private Button searchSectionButton;
	private Button listButton;
	private ImageView informationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        searchSectionButton = (Button) findViewById(R.id.browseButton);
        searchSectionButton.setOnClickListener(this);
        
        listButton = (Button) findViewById(R.id.listButton);
        listButton.setOnClickListener(this);
        
        informationButton = (ImageView) findViewById(R.id.informationButton);
        informationButton.setOnClickListener(this);
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
		case R.id.listButton :
			startActivity(ListActivity.class);
			break;
		}
	}
}