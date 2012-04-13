package com.simplemad.base.cache;

import org.springframework.util.Assert;

import net.spy.memcached.MemcachedClient;

public class WebCacheManager {

	private MemcachedClient memClient = null;
	
	public void add(String key, int exp, Object value) {
		Assert.notNull(memClient);
		memClient.add(key, exp, value);
	}
	
	public void addForEver(String key, Object value) {
		Assert.notNull(memClient);
		memClient.add(key, 0, value);
	}
	
	public void delete(String key) {
		Assert.notNull(memClient);
		memClient.delete(key);
	}
	
	public Object get(String key) {
		Assert.notNull(memClient);
		return memClient.get(key);
	}
	

	public void setMemClient(MemcachedClient memClient) {
		Assert.notNull(memClient);
		this.memClient = memClient;
	}
	
}
