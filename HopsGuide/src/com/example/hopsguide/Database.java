package com.example.hopsguide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper implements Serializable{

	private static final int 	DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "info331";
	public static final String UID = "_id";
	
	public static final String HOPS_TABLE_NAME = "hops";
	public static final String COUNTRY = "Country";
	public static final String ALPHA = "Alpha";
	public static final String BETA = "Beta";
	public static final String TYPE = "Type";
	public static final String STORAGE_INDEX = "StorageIndex";
	public static final String TYPICAL_FOR = "TypicalFor";
	public static final String SUBSTITUTES = "Substitutes";
	public static final String AROMA = "Aroma";
	public static final String INFORMATION = "Information";
	
	public static final String LIST_TABLE_NAME = "lists";
	public static final String CONTENT = "content";
	
	public static final String NO_DATA = "Not available";

	public Database(Context context) throws Exception{
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
//		this.context = context;
	}

	//Kalt når databasen lages for første gang
	//Husk at execSQL ikke returnerer noe, så denne metoden er ikke
	//passende for SELECT-spørringer
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQLQueryFactory.deleteHopsTable());
		db.execSQL(SQLQueryFactory.deleteMyListsTable());
		db.execSQL(SQLQueryFactory.createHopsTable());
		db.execSQL(SQLQueryFactory.createMyListTable());
	}
	
	public void insertHops(SQLiteDatabase db, ContentValues values){
		db.insert("Hops","no data",values);
	}
	
	public void insertList(SQLiteDatabase db, ContentValues values){
		db.insert("Lists",null,values); 	//ikke nødvendig med nullColumnHack fordi brukeren
											//må alltid fylle inn all informasjon
	}

	//Kalt når databasen endrer innhold og/eller struktur
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	//	db.execSQL("deleteDummy");
		onCreate(db);
		try {
			saveToFile("database.obj");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveToFile(String fileName) throws IOException{
		File file = new File(fileName);
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this);
	}
	
	public static Database readFromFile(String fileName) throws StreamCorruptedException, IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		return (Database) ois.readObject();
	}
}