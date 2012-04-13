package com.simplemad.base.domain;

import com.google.code.morphia.annotations.Entity;
import com.simplemad.bean.PhoneSystemType;

@Entity
public class ClientVersion extends BaseEntity {

	private static final long serialVersionUID = 3633622123569619644L;
	
	private int version;
	
	private PhoneSystemType type;
	
	private String fileId;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public PhoneSystemType getType() {
		return type;
	}

	public void setType(PhoneSystemType type) {
		this.type = type;
	}
}
