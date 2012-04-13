package com.simplemad.ad.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.simplemad.ad.domain.Advertiser;

public interface AdvertiserService {

	public Advertiser register(Advertiser advertiser);
	
	public Advertiser find(ObjectId id);
	
	public List<Advertiser> findAll();
}
