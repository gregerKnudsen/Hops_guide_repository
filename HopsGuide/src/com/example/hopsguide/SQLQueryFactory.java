package com.example.hopsguide;

public class SQLQueryFactory {
	
	public static String createHopsTable(){
		return "CREATE TABLE IF NOT EXISTS Hops (" +
			   	"_id VARCHAR(200),"+
			   	"country VARCHAR(200),"+
			   	"alphaMin FLOAT,"+
			   	"alphaMax FLOAT,"+
			   	"beta FLOAT,"+
			   	"type VARCHAR(200),"+
			   	"storageIndex INTEGER,"+
			   	"typicalFor VARCHAR(200),"+
			   	"substitutes VARCHAR(500),"+
			   	"aroma VARCHAR(500),"+
			   	"information VARCHAR(720));";
	}
	
	public static String createMyListTable(){
		return "CREATE TABLE IF NOT EXISTS Lists (" +
			   	"_id VARCHAR(200),"+ //hver liste identifiseres med navn p� liste
			   	"content VARCHAR(1024));";
	}
	
	public static String updateColumn(String table, String column, String columnValue, String row, String rowValue){
		return "UPDATE " + table + " SET " + column + " = " + columnValue + " WHERE " + row + " = '" + rowValue + "'";
	}
	
	public static String selectHopsNames(){
		return "SELECT Name FROM Hops";
	}

	public static String selectHopsByName(String name){
		return "SELECT Name FROM Hops WHERE Name = " + name;
	}
	
	public static String deleteList(String listName){
		return "DELETE FROM Lists WHERE _id = '" + listName + "'";
	}
	
	public static String deleteHopsTable(){
		return "DROP TABLE IF EXISTS Hops";
	}
	
	public static String deleteMyListsTable(){
		return "DROP TABLE IF EXISTS Lists";
	}
	
	public static String selectCountryNames(){
		return "SELECT DISTINCT country FROM Hops";
	}
	public static String selectHopsByCountry(String countryName){
		return "SELECT Name FROM Hops WHERE Country = '"+ countryName +"';";
	}
	public static String selectHopsByTag(String tag) {
		return "SELECT Name FROM Hops WHERE typicalFor = '"+tag+"';";
	}
}