package com.simplemad.base.domain;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;

@Entity
public class Administrator extends BaseEntity {

	private static final long serialVersionUID = 4649651399609794707L;

	private long mobile;
	
	private String password;
	
	@Reference(lazy = true)
	private User user;

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
