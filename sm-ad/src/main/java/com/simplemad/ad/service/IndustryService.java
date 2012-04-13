package com.simplemad.ad.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.simplemad.ad.domain.Industry;

public interface IndustryService {

	public Industry create(String name);
	
	public Industry find(ObjectId id);
	
	public List<Industry> findAll();
}
