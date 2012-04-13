package com.simplemad.base.domain;

import org.bson.types.ObjectId;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.google.code.morphia.annotations.Id;
import com.simplemad.base.config.ArgumentEntity;
import com.simplemad.base.json.ObjectIdSerializer;

public abstract class BaseEntity implements MongoEntity<BaseEntity>, ArgumentEntity {

	private static final long serialVersionUID = 5756138036288673254L;

	@Id
	private ObjectId id;
	
	@Override
	@JsonSerialize(using=ObjectIdSerializer.class)
	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}

	@Override
	public boolean sameIdentityAs(final BaseEntity other) {
		return null != other && id.equals(other.getId());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BaseEntity) {
			BaseEntity entity = (BaseEntity) obj;
			return sameIdentityAs(entity);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return id.toString();
	}

}
