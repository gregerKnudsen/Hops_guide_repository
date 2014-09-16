package com.example.hopsguide;

public class Hops {

	private double alphaAcid;
	private double betaAcid;
	private String type;
	private String country;

	public Hops(String country, double alphaAcid, double betaAcid, String type){
		this.country = country;
		this.alphaAcid = alphaAcid;
		this.betaAcid = betaAcid;
		this.type = type;
	}
	
	public double getAlphaAcid() {
		return alphaAcid;
	}

	public void setAlphaAcid(double alphaAcid) {
		this.alphaAcid = alphaAcid;
	}

	public double getBetaAcid() {
		return betaAcid;
	}

	public void setBetaAcid(double betaAcid) {
		this.betaAcid = betaAcid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return 	"Country: " + country + 
				"\nAlpha acid: " + alphaAcid +
				"\nBetaAcid:" + betaAcid +
				"\nType=" + type;
	}
}