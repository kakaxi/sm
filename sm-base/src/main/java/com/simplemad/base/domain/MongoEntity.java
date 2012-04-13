package com.simplemad.base.domain;

import java.io.Serializable;

import org.bson.types.ObjectId;

public interface MongoEntity<T> extends Serializable {

	boolean sameIdentityAs(T other);
	
	ObjectId getId();
	
	void setId(ObjectId id);

}
