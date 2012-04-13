package com.simplemad.base.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplemad.base.dao.BaseDAO;
import com.simplemad.base.domain.Area;
import com.simplemad.base.domain.City;
import com.simplemad.base.domain.Province;
import com.simplemad.base.util.StringUtil;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private BaseDAO dao;
	
	@Override
	public Province createProvince(String name) {
		if(StringUtil.isEmpty(name)) {
			return null;
		}
		Province province = new Province();
		province.setName(name);
		dao.save(province);
		return province;
	}

	@Override
	public Province findProvince(ObjectId id) {
		return dao.findOne(Province.class, "id", id);
	}

	@Override
	public City createCity(ObjectId proId, String name) {
		if(proId == null || StringUtil.isEmpty(name)) {
			return null;
		}
		Province province = findProvince(proId);
		if(province == null) {
			return null;
		}
		City city = new City();
		city.setName(name);
		city.setProvince(province);
		dao.save(city);
		province.getCities().add(city);
		dao.save(province);
		return city;
	}

	@Override
	public City findCity(ObjectId id) {
		return dao.findOne(City.class, "id", id);
	}

	@Override
	public List<Province> findAllProvince() {
		return dao.findAll(Province.class);
	}

	@Override
	public Area findArea(ObjectId id) {
		return dao.findOne(Area.class, "id", id);
	}

	@Override
	public Area createArea(ObjectId cityId, String name) {
		City city = findCity(cityId);
		if(city == null) {
			return null;
		}
		Area area = new Area();
		area.setName(name);
		area.setCity(city);
		dao.save(area);
		city.getAreas().add(area);
		dao.save(city);
		return area;
	}

}
