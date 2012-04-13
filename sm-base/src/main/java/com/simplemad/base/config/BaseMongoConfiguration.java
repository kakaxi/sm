package com.simplemad.base.config;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

@Configuration
public class BaseMongoConfiguration {

	@Value("#{db.host}")
	private String host;

	@Value("#{db.name}")
	private String name;

	@Value("#{db.port}")
	private int port;

	@Bean(name = {"ds"})
	public Datastore getDatastore() throws UnknownHostException, MongoException {
		Mongo mongo = getMongo();
		Assert.notNull(name);
		Assert.notNull(mongo);
		return new Morphia().createDatastore(mongo, name);		
	}
	
	@Bean(name = {"mongoDB"})
	public DB getDB() throws UnknownHostException, MongoException {
		Assert.notNull(name);
		DB db = getMongo().getDB(name);
		return db;
	}
	
	@Bean(name = { "mongo" })
	public Mongo getMongo() throws UnknownHostException, MongoException {
		Assert.notNull(host);
		Assert.notNull(port);
		Mongo mongo = new Mongo(host, port);
		return mongo;
	}
}
