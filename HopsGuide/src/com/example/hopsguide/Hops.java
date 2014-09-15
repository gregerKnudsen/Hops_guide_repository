package com.example.hopsguide;

public class Hops {

	private double alphaAcid;
	private double betaAcid;
	private String substitutes;
	private String type;
	private String country;

	public Hops(double alphaAcid, double betaAcid, String substitutes, String type, String country){
		this.alphaAcid = alphaAcid;
		this.betaAcid = betaAcid;
		this.substitutes = substitutes;
		this.type = type;
		this.country = country;
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

	public String getSubstitutes() {
		return substitutes;
	}

	public void setSubstitutes(String substitutes) {
		this.substitutes = substitutes;
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
		return "Hops [alphaAcid=" + alphaAcid
				+ ", betaAcid=" + betaAcid + ", substitutes=" + substitutes
				+ ", type=" + type + ", country=" + country + "]";
	}
}