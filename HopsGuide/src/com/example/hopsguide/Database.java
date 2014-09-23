package com.example.hopsguide;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Database {
	Connection conn;
	private Map<String,Hops> hops;
	private String createHopsTable;
	private String insertHops;
	private String selectAllHopsNames;
	private String selectHopsByName;
	private String selectHopsByCountry;
	private String getHopsByAlpha;

	public Database(Connection conn) throws Exception {
		this.conn = conn;
		
		createHopsTable 		= slurpFile("sql/create_hops_table.sql");
		insertHops 				= slurpFile("sql/insert_hops.sql");
		
		selectAllHopsNames 		= slurpFile("sql/select_all_hops_names.sql");
		selectHopsByName 		= slurpFile("sql/select_hops_by_name.sql");
		selectHopsByCountry		= slurpFile("sql/select_hops_by_country.sql");
		getHopsByAlpha 			= slurpFile("sql/select_hops_by_alpha.sql");

		//må også lage for innsetting av hops

		hops = new HashMap<String,Hops>();
		System.out.println("Alt gikk fint");
	}

	/**
	 * Henter informasjon om monster med gitt ID
	 * @return resultat-sett med nÃ¸dvendig informasjon om gitt monster
	 */
	public ResultSet getHopsByName(String name) throws SQLException{
		PreparedStatement ps = conn.prepareStatement(selectHopsByName);
		ps.setString(1, name);
		return ps.executeQuery();
	}

	/**
	 * Oppretter en gitt tabell-type i databasen
	 * @param tableName navnet pÃ¥ tabellen som skal lages
	 * @throws SQLException
	 */
	public void createHopsTable() throws SQLException{
		PreparedStatement ps;
		ps = conn.prepareStatement(createHopsTable);
		ps.execute();
	}

	private void addHops(String name, String country, float alpha, float beta, String type, int storageIndex, 
			String typicalFor, String aroma, String information) throws SQLException{
		PreparedStatement ps = conn.prepareStatement(insertHops);
		ps.setString(1, name);
		ps.setString(2, country);
		ps.setFloat(3, alpha);
		ps.setFloat(4, beta);
		ps.setString(5, type);
		ps.setInt(5, storageIndex);
		ps.setString(6, typicalFor);
		ps.setString(7, aroma);
		ps.setString(8, information);
		ps.execute();
	}

	private static String slurpFile(File f) throws Exception{
		String fileContents = "";
		Scanner sc = new Scanner(f);
		fileContents = sc.useDelimiter("\\Z").next();
		sc.close();
		return fileContents;
	}

	private static String slurpFile(String s) throws Exception {
		return Database.slurpFile(new File(s));
	}

	/**
	 * Henter ut en instans av forbindelsen til SQL-databasen
	 * @return forbindelse til SQL-databasen
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws InstantiationException, 
	IllegalAccessException, ClassNotFoundException, SQLException{
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";    
		Class.forName(driver).newInstance();                       
		String protocol = "jdbc:derby:"; 				 	      
		String databaseNavn = "database";						   
		String jdbcUrl = protocol + databaseNavn + ";create=true";
		return DriverManager.getConnection(jdbcUrl);
	}

	public void deleteTable(String tableName) throws SQLException{
		PreparedStatement ps = conn.prepareStatement("DROP TABLE Hops");
		ps.execute();
	}
}