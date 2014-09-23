package com.example.hopsguide;

import java.io.File;
import java.util.Scanner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;

public class Database extends SQLiteOpenHelper {

	private static final int 	DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "info331";
	private static final String TABLE_NAME = "hops";
	private static final String UID = "_id";	//denne kolonnen lagrer navnet på humlen, som er tabellens primærnøkkel
	private static final String COUNTRY = "Country";
	private static final String ALPHA = "Alpha";
	private static final String BETA = "Beta";
	private static final String STORAGE_INDEX = "StorageIndex";
	private static final String TYPICAL_FOR = "TypicalFor";
	private static final String AROMA = "Aroma";
	private static final String INFORMATION = "Information";
	
	private String createHopsTable;
	private String insertHops;
	private String selectAllHopsNames;
	private String selectHopsByName;
	private String selectHopsByCountry;
	private String selectHopsByAlpha;
	private String deleteTable;
	private Context context;

	public Database(Context context) throws Exception{
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
		this.context = context;
		parseSQLFiles();
	}
	
	public void parseSQLFiles() throws Exception{
		createHopsTable 		= slurpFile("sql/create_hops_table.sql");
		insertHops 				= slurpFile("sql/insert_hops.sql");
		selectAllHopsNames 		= slurpFile("sql/select_all_hops_names.sql");
		selectHopsByName 		= slurpFile("sql/select_hops_by_name.sql");
		selectHopsByCountry		= slurpFile("sql/select_hops_by_country.sql");
		selectHopsByAlpha 		= slurpFile("sql/select_hops_by_alpha.sql");
		deleteTable				= slurpFile("sql/delete_table.sql");
	}

	//Kalt når databasen lages for første gang
	//Husk at execSQL ikke returnerer noe, så denne metoden er ikke
	//passende for SELECT-spørringer
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createHopsTable);
	}

	//Kalt når databasen endrer innhold og/eller struktur
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(deleteTable);
		onCreate(db);
	}
	
	private static String slurpFile(String filePath) throws Exception{
		File f = new File(filePath);
		String fileContents = "";
		Scanner sc = new Scanner(f);
		fileContents = sc.useDelimiter("\\Z").next();
		sc.close();
		return fileContents;
	}
}