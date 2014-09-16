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
	private ImageView informationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchSectionButton = (Button) findViewById(R.id.searchSectionButton);
        searchSectionButton.setOnClickListener(this);
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
    	onClick((Button) findViewById(R.id.searchSectionButton));
    }
    
    public void informationButtonClick(){
    	onClick((ImageView) findViewById(R.id.informationButton));
    }
    
	@Override
	public void onClick(View v) {	 
		switch (v.getId()){
		case R.id.searchSectionButton : 
			Intent in1 = new Intent(getApplicationContext(),SearchActivity.class);
			startActivity(in1);
			break;
//		case R.id.informationButton :
//			Intent in2 = new Intent(getApplicationContext(),InformationActivity.class);
//			startActivity(in2);
//			break;
		}
	}
}