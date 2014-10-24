package com.example.hopsguide;

public class Hops {

	private String name;
	private String country;
	private float alphaMin;
	private float alphaMax;
	private float beta;
	private String type;
	private int storageIndex;
	private String typicalFor;
	private String substitutes;
	private String aroma;
	private String information;
	
	public Hops(String name, String country, float alphaMin, float alphaMax, float beta, String type, int storageIndex, String typicalFor, String substitutes,String aroma,
				String information) {
		this.name = name;
		this.country = country;
		this.alphaMin = alphaMin;
		this.alphaMax = alphaMax;
		this.beta = beta;
		this.type = type;
		this.storageIndex = storageIndex;
		this.typicalFor = typicalFor;
		this.substitutes = substitutes;
		this.aroma = aroma;
		this.information = information;
	}
	//comment
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

	public float getAlphaMin() {
		return alphaMin;
	}

	public void setAlphaMin(float alphaMin) {
		this.alphaMin = alphaMin;
	}
	
	public float getAlphaMax() {
		return alphaMax;
	}

	public void setAlphaMax(float alphaMax) {
		this.alphaMax = alphaMax;
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
		return "Country: " + country +
				"\n\nAlphaMin: "+ alphaMin +
				"\n\nAlphaMax: "+ alphaMax +
				"\n\nBeta: " + beta + 
				"\n\nType: " + type + 
				"\n\nStorage index: " + storageIndex +
				"\n\nTypical for: " + typicalFor +
				"\n\nSustitutes: " + substitutes + 
				"\n\nAroma: " + aroma +
				"\n\nInformation\n" + information;
	}
}