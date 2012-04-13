package com.simplemad.base.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.simplemad.base.domain.Area;
import com.simplemad.base.domain.City;
import com.simplemad.base.domain.Province;

public interface AreaService { 

	Province createProvince(String name);
	
	Province findProvince(ObjectId id);
	
	City createCity(ObjectId proId, String name);
	
	City findCity(ObjectId id);
	
	Area findArea(ObjectId id);
	
	Area createArea(ObjectId cityId, String name);
	
	List<Province> findAllProvince();
}
