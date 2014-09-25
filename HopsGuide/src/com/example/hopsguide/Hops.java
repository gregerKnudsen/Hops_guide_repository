package com.example.hopsguide;

public class Hops {

	private String name;
	private String country;
	private float alpha;
	private float beta;
	private String type;
	private int storageIndex;
	private String typicalFor;
	private String substitutes;
	private String aroma;
	private String information;
	
	public Hops(String name, String country, float alpha, float beta,
			String type, int storageIndex, String typicalFor, String substitutes,String aroma,
			String information) {
		this.name = name;
		this.country = country;
		this.alpha = alpha;
		this.beta = beta;
		this.type = type;
		this.storageIndex = storageIndex;
		this.typicalFor = typicalFor;
		this.substitutes = substitutes;
		this.aroma = aroma;
		this.information = information;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public float getBeta() {
		return beta;
	}

	public void setBeta(float beta) {
		this.beta = beta;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStorageIndex() {
		return storageIndex;
	}

	public void setStorageIndex(int storageIndex) {
		this.storageIndex = storageIndex;
	}

	public String getTypicalFor() {
		return typicalFor;
	}

	public void setTypicalFor(String typicalFor) {
		this.typicalFor = typicalFor;
	}

	public String getSubstitutes() {
		return substitutes;
	}

	public void setSubstitutes(String substitutes) {
		this.substitutes = substitutes;
	}

	public String getAroma() {
		return aroma;
	}

	public void setAroma(String aroma) {
		this.aroma = aroma;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	@Override
	public String toString() {
		return "Country\t\t\t" + country +
				"\nAlpha\t\t\t\t"+ alpha + 
				"\nBeta\t\t\t\t" + beta + 
				"\nType\t\t\t\t" + type + 
				"\nStorage index\t" + storageIndex +
				"\nTypicalFor\t\t" + typicalFor +
				"\nSustitutes\t\t" + substitutes + 
				"\nAroma\t\t\t" + aroma +
				"\n\nInformation\n" + information;
	}
	
}