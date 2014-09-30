package com.example.hopsguide;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

public class MySQLDatabase extends Activity{

	public AsyncTask<URL, Void, JSONArray> getDatabaseData() throws InterruptedException{
		DatabaseDownload dbdl = new DatabaseDownload();
		AsyncTask<URL, Void, JSONArray> result = dbdl.execute(new URL[2]);
		return result;
	}

	private class DatabaseDownload extends AsyncTask<URL, Void, JSONArray>{
		
		@Override
		protected JSONArray doInBackground(URL... arg0) {
			try {
				return getHopsData();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		public JSONArray getHopsData() throws JSONException{
			//	Looper.prepare();  
			Log.i("MySQLDatabase", "ANKOMMER GETDATA...");
			String result = "";
			InputStream isr = null;
			try{
				Log.i("MySQLDatabase", "ANKOMMER HTTP CLIENT...");
				HttpClient httpClient = new DefaultHttpClient();
				Log.i("MySQLDatabase", "FERDIG MED Å OPPRETTE HTTP OBJEKT");
				HttpPost httpPost = new HttpPost("http://humleapp.web44.net/index.php");
				Log.i("MySQLDatabase", "FERDIG MED Å HENTE PHP");
				HttpResponse httpResponse = httpClient.execute(httpPost);
				Log.i("MySQLDatabase", "FERDIG MED Å EXECUTE HTTP CLIENT");
				HttpEntity httpEntity = httpResponse.getEntity();
				isr = httpEntity.getContent();
				Log.i("MySQLDatabase", "FERDIG HTTP CLIENT...");
			}
			catch(Exception e){
				Log.i("MySQLDatabase", "FEIL I LESING AV HTTP RESPONS: " + e.getCause());
			}

			//convert response to string
			Log.i("MySQLDatabase", "ANKOMMER CONVERT RESPONSE...");
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(isr,"iso-8859-1"),8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while((line = reader.readLine()) != null){
					sb.append(line + "\n");
				}
				isr.close();
				result = sb.toString();
				Log.i("MySQLDatabase","DETTE LIGGER I RESULT: " + result);
				reader.close();
			}
			catch(Exception e){
			}

			//parse json data
			Log.i("MySQLDatabase", "ANKOMMER PARSING AV JSON DATA");
			JSONArray jArray = new JSONArray(result);

			return jArray;
		}
		
		public JSONArray getListsData() throws JSONException{
			//	Looper.prepare();  
			Log.i("MySQLDatabase", "ANKOMMER GETDATA...");
			String result = "";
			InputStream isr = null;
			try{
				Log.i("MySQLDatabase", "ANKOMMER HTTP CLIENT...");
				HttpClient httpClient = new DefaultHttpClient();
				Log.i("MySQLDatabase", "FERDIG MED Å OPPRETTE HTTP OBJEKT");
				HttpPost httpPost = new HttpPost("http://humleapp.web44.net/index.php");
				Log.i("MySQLDatabase", "FERDIG MED Å HENTE PHP");
				HttpResponse httpResponse = httpClient.execute(httpPost);
				Log.i("MySQLDatabase", "FERDIG MED Å EXECUTE HTTP CLIENT");
				HttpEntity httpEntity = httpResponse.getEntity();
				isr = httpEntity.getContent();
				Log.i("MySQLDatabase", "FERDIG HTTP CLIENT...");
			}
			catch(Exception e){
				Log.i("MySQLDatabase", "FEIL I LESING AV HTTP RESPONS: " + e.getCause());
			}

			//convert response to string
			Log.i("MySQLDatabase", "ANKOMMER CONVERT RESPONSE...");
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(isr,"iso-8859-1"),8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while((line = reader.readLine()) != null){
					sb.append(line + "\n");
				}
				isr.close();
				result = sb.toString();
				Log.i("MySQLDatabase","DETTE LIGGER I RESULT: " + result);
				reader.close();
			}
			catch(Exception e){
			}

			//parse json data
			Log.i("MySQLDatabase", "ANKOMMER PARSING AV JSON DATA");
			JSONArray jArray = new JSONArray(result);

			return jArray;
		}
	}
}