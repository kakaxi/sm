package com.simplemad.base.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.mongodb.gridfs.GridFSDBFile;
import com.simplemad.base.domain.ClientVersion;
import com.simplemad.bean.PhoneSystemType;

public interface ClientVersionService {

	ClientVersion upgrade(String fileName, File file);
	
	ClientVersion find(PhoneSystemType type);
	
	InputStream findUpgradeStream(PhoneSystemType type);
	
	GridFSDBFile findUpgradeFile(PhoneSystemType type);
	
	List<ClientVersion> findAll();
}
