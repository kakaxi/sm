package com.simplemad.base.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.gridfs.GridFSDBFile;
import com.simplemad.base.dao.BaseDAO;
import com.simplemad.base.dao.BaseFAO;
import com.simplemad.base.domain.ClientVersion;
import com.simplemad.base.util.StringUtil;
import com.simplemad.bean.PhoneSystemType;

@Service
public class ClientVersionServiceImpl implements ClientVersionService {

	private static final int DEFAULT_VERSION = 1;
	
	@Autowired
	private BaseDAO dao;
	
	@Autowired
	private BaseFAO fao;
	
	@Override
	public ClientVersion upgrade(String fileName, File file) {
		PhoneSystemType type = find(fileName);
		if(type == null) {
			return null;
		} else {
			try {
				ClientVersion oldVersion = find(type);
				String fileId = fao.createFile(file).toString();
				if(oldVersion == null) {
					ClientVersion newVersion = new ClientVersion();
					newVersion.setType(type);
					newVersion.setFileId(fileId);
					newVersion.setVersion(DEFAULT_VERSION);
					dao.save(newVersion);
					return newVersion;
				} else {
					fao.remove(new ObjectId(oldVersion.getFileId()));
					oldVersion.setFileId(fileId);
					oldVersion.setVersion(oldVersion.getVersion() + 1);
					dao.save(oldVersion);
					return oldVersion;
				}
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	@Override
	public ClientVersion find(PhoneSystemType type) {
		return dao.findOne(ClientVersion.class, "type", type);
	}

	@Override
	public InputStream findUpgradeStream(PhoneSystemType type) {
		ClientVersion version = dao.findOne(ClientVersion.class, "type", type);
		if(version == null || StringUtil.isEmpty(version.getFileId())) {
			return null;
		} else {
			return fao.findAsInputStream(new ObjectId(version.getFileId()));
		}
	}
	
	@Override
	public GridFSDBFile findUpgradeFile(PhoneSystemType type) {
		ClientVersion version = dao.findOne(ClientVersion.class, "type", type);
		if(version == null || StringUtil.isEmpty(version.getFileId())) {
			return null;
		} else {
			return fao.findAsFile(new ObjectId(version.getFileId()));
		}
	}
	
	private PhoneSystemType find(String fileName) {
		if(StringUtil.isEmpty(fileName)) {
			return null;
		}
		if(fileName.endsWith(".apk")) {
			return PhoneSystemType.ANDROID;
		} else {
			//TODO 以后需要加入iphone和window版本
			return PhoneSystemType.IPHONE;
		}
	}

	@Override
	public List<ClientVersion> findAll() {
		return dao.findAll(ClientVersion.class);
	}

}
