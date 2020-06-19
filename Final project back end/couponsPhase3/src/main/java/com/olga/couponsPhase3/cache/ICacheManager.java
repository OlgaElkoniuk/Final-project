package com.olga.couponsPhase3.cache;

public interface ICacheManager {
	public void put(Object key, Object value);
	public Object get(Object key);
}
