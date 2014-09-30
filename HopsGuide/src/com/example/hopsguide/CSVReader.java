package com.example.hopsguide;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

	public List<Hops> read(String filePath) throws IOException {

		List<Hops> result = new ArrayList<Hops>();
		String csvFile = filePath;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		br = new BufferedReader(new FileReader(csvFile));
		String[] row = line.split(cvsSplitBy);
		
		while ((line = br.readLine()) != null) {
			result.add(new Hops(row[0],row[1],Float.parseFloat(row[2]),Float.parseFloat(row[3]),row[4],Integer.parseInt(row[5]),row[6],row[7],row[8],row[9]));
		}
		br.close();
		return result;
	}
}