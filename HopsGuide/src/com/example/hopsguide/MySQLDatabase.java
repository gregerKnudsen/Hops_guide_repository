package com.example.hopsguide;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MySQLDatabase extends Activity{
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
	}
	
	public static List<Hops> getData(){
		List<Hops> hopsList;
		String result = "";
		InputStream isr = null;
		try{
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://www.humleapp.web44.net/index.php");
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			isr = httpEntity.getContent();
		}
		catch(Exception e){
			Toast.makeText(null,"Failed to load database: " + e.getCause(),Toast.LENGTH_LONG).show();
		}
		
		//convert response to string
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(isr,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = reader.readLine()) != null){
				sb.append(line + "\n");
			}
			isr.close();
			
			result = sb.toString();
		}
		catch(Exception e){
			Toast.makeText(null,"Failed to load database: " + e.getCause(),Toast.LENGTH_LONG).show();
		}
		
		//parse json data
		try{
			JSONArray jArray = new JSONArray(result);
			hopsList = new ArrayList<Hops>();
			
			for(int i = 0; i < jArray.length(); i++){
				JSONObject json = jArray.getJSONObject(i);
				hopsList.add(new Hops(json.getString("Name"),json.getString("Country"),(float) json.getDouble("Alpha"),(float) json.getDouble("Beta"),json.getString("Type"),json.getInt("Storage Index"), json.getString("Typical for"), json.getString("Substitutes"),json.getString("Aroma"),json.getString("Informatin")));
			}
			
			return hopsList;
		}
		catch(Exception e){
			Toast.makeText(null,"Failed to load database: " + e.getCause(),Toast.LENGTH_LONG).show();
		}
		Log.i("MainActivity", "RETURNERER NULL VERDIER I MYSQL-DATABASE");
		return null;
	}
}