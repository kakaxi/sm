package com.simplemad.base.domain;

import java.net.SocketAddress;

import com.google.code.morphia.annotations.Entity;

@Entity
public class User extends BaseEntity {

	private static final long serialVersionUID = -3443681785293044417L;

	private long mobileNo;

	private String password;
	
	private SocketAddress ipAddress;

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SocketAddress getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(SocketAddress ipAddress) {
		this.ipAddress = ipAddress;
	}
	
}
