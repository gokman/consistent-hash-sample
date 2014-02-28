package com.gokman.cloud.consistenthash;

import java.util.SortedMap;
import java.util.TreeMap;

public class TreeMapDene {

	public static void main(String args[]){
		SortedMap<Integer,String> circle=new TreeMap<Integer,String>();
		circle.put(3, "aa");
		circle.put(2, "bir");
		circle.put(1, "iki");
		circle.tailMap(1);
		
		System.out.println(circle);
	}
}
