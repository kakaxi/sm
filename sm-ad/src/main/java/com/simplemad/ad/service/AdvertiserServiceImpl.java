package com.simplemad.ad.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplemad.ad.domain.Advertiser;
import com.simplemad.base.dao.BaseDAO;

@Service
public class AdvertiserServiceImpl implements AdvertiserService {

	@Autowired
	private BaseDAO dao;
	
	@Override
	public Advertiser register(Advertiser advertiser) {
		Advertiser existAdvertiser = dao.findOne(Advertiser.class, "name", advertiser.getName());
		if(existAdvertiser != null) {
			return null;
		}
		advertiser.setRegisterDate(new Date());
		dao.save(advertiser);
		return advertiser;
	}

	@Override
	public Advertiser find(ObjectId id) {
		return dao.findOne(Advertiser.class, "id", id);
	}

	@Override
	public List<Advertiser> findAll() {
		return dao.findAll(Advertiser.class);
	}

}
