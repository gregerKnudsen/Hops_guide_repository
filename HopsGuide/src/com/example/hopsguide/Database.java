package com.example.hopsguide;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class Database extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "info331";
	private static final String TABLE_NAME = "hops";
	private static final String UID = "_id";
	private static final String COUNTRY = "Country";
	private static final String ALPHA = "Alpha";
	private static final String BETA = "Beta";
	private static final String STORAGE_INDEX = "StorageIndex";
	private static final String TYPICAL_FOR = "TypicalFor";
	private static final String AROMA = "Aroma";
	private static final String INFORMATION = "Information";

	public Database(Context context){
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	

}