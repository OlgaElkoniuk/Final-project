package com.olga.couponsPhase3.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class HartaCacheManager implements ICacheManager{

private Map<Object, Object> map;
	
public HartaCacheManager() {
	this.map = new HashMap<Object, Object>();
}

	public void put(Object key, Object value) {
		this.map.put(key, value);
		//I'm printing the key in order to copy it from console and 
		//paste to url when i'll check other requests
		System.out.println(key);
		
	}

	public Object get(Object key) {
		
		return this.map.get(key);
	}
}