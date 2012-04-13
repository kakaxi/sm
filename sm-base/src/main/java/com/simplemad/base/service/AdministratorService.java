package com.simplemad.base.service;

import java.util.List;

import com.simplemad.base.domain.Administrator;

public interface AdministratorService {

	boolean create(Administrator admin);
	
	Administrator find(String id);
	
	Administrator find(long mobile);
	
	List<Administrator> findAll();
	
	boolean login(Administrator admin);
}
