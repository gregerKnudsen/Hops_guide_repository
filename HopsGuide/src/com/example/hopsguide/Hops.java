package com.example.hopsguide;

public class Hops {

	private String name;
	private String country;
	private float alpha;
	private float beta;
	private int storageIndex;
	private String typicalFor;
	private String aroma;
	private String information;
	
	public Hops(String name, String country, float alpha, float beta,
			int storageIndex, String typicalFor, String aroma,
			String information) {
		this.name = name;
		this.country = country;
		this.alpha = alpha;
		this.beta = beta;
		this.storageIndex = storageIndex;
		this.typicalFor = typicalFor;
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
		return "Hops [name=" + name + ", country=" + country + ", alpha="
				+ alpha + ", beta=" + beta + ", storageIndex=" + storageIndex
				+ ", typicalFor=" + typicalFor + ", aroma=" + aroma
				+ ", information=" + information + "]";
	}
	
}