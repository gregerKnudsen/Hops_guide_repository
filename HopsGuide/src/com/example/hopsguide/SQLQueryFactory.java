package com.example.hopsguide;

public class SQLQueryFactory {
	
	public static String createHopsTable(){
		return "CREATE TABLE IF NOT EXISTS Hops (" +
			   	"_id VARCHAR(200),"+
			   	"country VARCHAR(200),"+
			   	"alpha FLOAT,"+
			   	"beta FLOAT,"+
			   	"type VARCHAR(200),"+
			   	"storageIndex INTEGER,"+
			   	"typicalFor VARCHAR(200),"+
			   	"substitutes VARCHAR(500),"+
			   	"aroma VARCHAR(500),"+
			   	"information VARCHAR(720));";
	}
	
	public static String createMyListTable(){
		return "CREATE TABLE IF NOT EXISTS MyLists (" +
			   	"_id VARCHAR(200),"+ //hver liste identifiseres med navn på liste
			   	"content VARCHAR(1024));";
	}
	
	public static String selectHopsNames(){
		return "SELECT Name FROM Hops";
	}

	public static String selectHopsByName(String name){
		return "SELECT Name FROM Hops WHERE Name = " + name;
	}
	
	public static String deleteHopsTable(){
		return "DROP TABLE IF EXISTS Hops";
	}
	
	public static String deleteMyListsTable(){
		return "DROP TABLE IF EXISTS MyLists";
	}
}