package com.simplemad.ad.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplemad.ad.domain.Industry;
import com.simplemad.base.dao.BaseDAO;
import com.simplemad.base.util.StringUtil;

@Service
public class IndustryServiceImpl implements IndustryService {

	@Autowired
	private BaseDAO dao;
	
	@Override
	public Industry create(String name) {
		if(StringUtil.isEmpty(name)) {
			return null;
		}
		Industry oldIndustry = dao.findOne(Industry.class, "name", name);
		if(oldIndustry != null) {
			return null;
		}
		Industry industry = new Industry();
		industry.setName(name);
		dao.save(industry);
		return industry;
		
	}

	@Override
	public Industry find(ObjectId id) {
		return dao.findOne(Industry.class, "id", id);
	}

	@Override
	public List<Industry> findAll() {
		return dao.findAll(Industry.class);
	}

}
