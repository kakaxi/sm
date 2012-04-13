package com.simplemad.base.config;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;

import com.simplemad.base.cache.WebCacheManager;

//@Configuration
public class MemcachedConfiguration {
	
	private static Logger log = Logger.getLogger(MemcachedConfiguration.class);
	
	@Value("#{memcached.host}")
	private String host;
	
	@Value("#{memcached.port}")
	private int port;
	
	@Bean
	public WebCacheManager getCacheManager() {
		Assert.notNull(host);
		Assert.notNull(port);
		log.info("initialize the MemcachedClient for the host : " + host + "and the port : " + port);
		
		MemcachedClient mc = null;
		try {
			mc = new MemcachedClient(new InetSocketAddress(host, port));
		} catch (IOException e) {
			log.error("initialze the MemcachedClient fail for the host : " + host + "and the port : " + port);
		}
		Assert.notNull(mc);
		log.info("initialze the MemcachedClient successfully the host : " + host + "and the port : " + port);
		WebCacheManager wcm = new WebCacheManager();
		wcm.setMemClient(mc);
		return wcm;
	}
}
