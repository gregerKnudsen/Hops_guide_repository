package com.example.hopsguide;

import java.util.ArrayList;
import java.util.List;

public class HopsList {
	
	private List<String> list;
	private String name;
	
	public HopsList(String name){
		this.name = name;
		list = new ArrayList<String>();
	}
	
	public HopsList(String name, String hops){
		this.name = name;
		list = new ArrayList<String>();
		list.add(hops);
	}
	
	public void add(String hops){
		list.add(hops);
	}
	
	@Override
	public String toString(){
		if(list.size() > 0){
			String result = name + "\n\n";
			for(String hops : list){
				result += hops;
			}
			return result;
		}
		return "List is empty";
	}
}
