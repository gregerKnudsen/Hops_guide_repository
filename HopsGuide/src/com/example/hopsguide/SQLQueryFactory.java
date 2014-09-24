package com.example.hopsguide;

import java.util.List;

public class SQLQueryFactory {
	
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
		return "SELECT";
	}
}
