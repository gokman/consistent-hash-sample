package com.gokman.cloud.consistenthash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHash<T> {

	private final int numberOfReplica;
	private final SortedMap<Integer,T> circle=new TreeMap<Integer,T>();
	
	public ConsistentHash(int numberOfReplica,Collection<T> nodes) throws NoSuchAlgorithmException{
		this.numberOfReplica=numberOfReplica;
		
		for (T node : nodes){
			add(node);
		}
	}
	
	private void add(T node) throws NoSuchAlgorithmException {
		for(int i=0;i<numberOfReplica;i++){
			circle.put(hash(node.toString()+1), node);
		}
	}
	
	public void remove(T node) throws NoSuchAlgorithmException{
		for(int i=0;i<numberOfReplica;i++){
			circle.remove(hash(node.toString()+1));
		}
		
	}
	
	public T get(String key) throws NoSuchAlgorithmException{
		if(circle.isEmpty()){
			return null;
		}
		int hash = hash(key);
		if(!circle.containsKey(hash)){
			SortedMap<Integer, T> tailMap=circle.tailMap(hash);
			hash=tailMap.isEmpty()?circle.firstKey():tailMap.firstKey();
		}
		return circle.get(hash);
	}

	public int hash(String text) throws NoSuchAlgorithmException{
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(text.getBytes());
		
		return Integer.parseInt(messageDigest.digest().toString());
		
	}
}
