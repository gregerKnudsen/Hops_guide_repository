package com.example.hopsguide;

import java.io.File;
import java.util.Scanner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Database extends SQLiteOpenHelper {

	private static final int 	DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "info331";
	
	public static final String TABLE_NAME = "hops";
	public static final String UID = "_id";	//denne kolonnen lagrer navnet på humlen, som er tabellens primærnøkkel
	public static final String COUNTRY = "Country";
	public static final String ALPHA = "Alpha";
	public static final String BETA = "Beta";
	public static final String STORAGE_INDEX = "StorageIndex";
	public static final String TYPICAL_FOR = "TypicalFor";
	public static final String AROMA = "Aroma";
	public static final String INFORMATION = "Information";
	private Context context;

	public Database(Context context) throws Exception{
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
		this.context = context;
		Toast.makeText(context,"contructor called",2);
	}

	//Kalt når databasen lages for første gang
	//Husk at execSQL ikke returnerer noe, så denne metoden er ikke
	//passende for SELECT-spørringer
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQLQueryFactory.createHopsTable());
	}

	//Kalt når databasen endrer innhold og/eller struktur
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("deleteDummy");
		onCreate(db);
	}
}