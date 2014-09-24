package com.example.hopsguide;

import java.util.List;

public class SQLQueryFactory {
	
	public static String createHopsTable(){
		return "CREATE TABLE IF NOT EXISTS Hops (" +
			   	"_id VARCHAR(200) PRIMARY KEY,"+
			   	"country VARCHAR(200),"+
			   	"alpha FLOAT,"+
			   	"beta FLOAT,"+
			   	"type VARCHAR(200),"+
			   	"storageIndex INTEGER,"+
			   	"typicalFor VARCHAR(200),"+
			   	"substitutes VARCHAR(500),"+
			   	"aroma VARCHAR(500),"+
			   	"information VARCHAR(720))";
	}
	
	public static String selectHopsNames(){
		return "SELECT Name FROM Hops";
	}
	
	public static String insertHops(String _id, String country, float alpha, float beta, String type, 
			int storageIndex, String typicalFor, String substitutes, String aroma, String information){
		return "INSERT INTO Hops (_id, country, alpha, beta, type, storageIndex, typicalFor, substitutes, aroma, information) " +
			   "VALUES (" + _id + "," + country + "," + alpha + "," + beta + "," + 
				type + "," + storageIndex + "," + typicalFor + "," + substitutes + "," + aroma + "," + information + ")";
	}
	
	public static String selectHopsByName(String name){
		return "SELECT Name FROM Hops WHERE Name = " + name;
	}
}
