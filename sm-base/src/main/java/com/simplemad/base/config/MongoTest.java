package com.simplemad.base.config;

import java.net.UnknownHostException;

import org.springframework.util.Assert;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.simplemad.base.domain.User;
import com.simplemad.base.domain.UserProfile;
import com.simplemad.base.domain.enums.Gender;

public class MongoTest {

	private String host = "localhost";

	private String name = "pinlog";

	private int port = 27017;

	public Datastore getDatastore() throws UnknownHostException, MongoException {
		Mongo mongo = getMongo();
		Assert.notNull(name);
		Assert.notNull(mongo);
		return new Morphia().createDatastore(mongo, name);		
	}
	
	public DB getDB() throws UnknownHostException, MongoException {
		Assert.notNull(name);
		DB db = getMongo().getDB(name);
		return db;
	}
	
	public Mongo getMongo() throws UnknownHostException, MongoException {
		Assert.notNull(host);
		Assert.notNull(port);
		Mongo mongo = new Mongo(host, port);
		return mongo;
	}
	
	public static void main(String[] args) throws UnknownHostException, MongoException {
		MongoTest mongo = new MongoTest();
		Datastore ds = mongo.getDatastore();
		Query<UserProfile> query = ds.find(UserProfile.class);
		query.and(query.criteria("gender").equal(Gender.Male));
		System.out.println("query:" + query.toString());
//		System.out.println("query count:" + query.countAll());
		
		System.out.println(query.asList());
	}
}
