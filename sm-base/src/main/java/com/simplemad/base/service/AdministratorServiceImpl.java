package com.simplemad.base.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplemad.base.dao.BaseDAO;
import com.simplemad.base.domain.Administrator;
import com.simplemad.base.domain.User;
import com.simplemad.base.util.StringUtil;

@Service
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	private BaseDAO dao;
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean create(Administrator admin) {
		User existUser = userService.findUser(admin.getMobile());
		if(existUser != null) {
			return false;
		}
		userService.register(admin.getMobile(), admin.getPassword(), "", 0);
		User user = userService.findUser(admin.getMobile());
		admin.setUser(user);
		dao.save(admin);
		return true;
	}

	@Override
	public Administrator find(String id) {
		return dao.findOne(Administrator.class, "id", new ObjectId(id));
	}

	@Override
	public List<Administrator> findAll() {
		return dao.findAll(Administrator.class);
	}

	@Override
	public boolean login(Administrator admin) {
		if(admin == null || admin.getMobile() == 0 || StringUtil.isEmpty(admin.getPassword())) {
			return false;
		}
		Administrator existAdmin = find(admin.getMobile());
		if(existAdmin == null || !existAdmin.getPassword().equals(admin.getPassword())) {
			return false;
		}
		return true;
	}

	@Override
	public Administrator find(long mobile) {
		return dao.findOne(Administrator.class, "mobile", mobile);
	}

}
