package com.simplemad.base.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

/**
 * @author Kamen
 *
 */
@Repository(value="baseFAO")
public class BaseFAO {
	
	private GridFS gridFS;

	@Autowired
	public BaseFAO(@Qualifier(value="mongoDB")DB db) {		
		gridFS = new GridFS(db);		
	}
		
	public BaseFAO(DB db, String bucket) {
		gridFS = new GridFS(db, bucket);
	}
	
	public ObjectId createFile(String filepath) throws IOException {
		File file = new File(filepath);
		return createFile(file);
	}
	
	public ObjectId createFile(File file) throws IOException {		
		InputStream is = new FileInputStream(file);
		return createFile(is);
	}
	
	public ObjectId createFile(InputStream is) {		
		GridFSInputFile fsFile = gridFS.createFile(is);
		fsFile.save();
		return (ObjectId) fsFile.getId();		
	}
	
	public InputStream findAsInputStream(ObjectId id) {
		GridFSDBFile dbFile = gridFS.find(id);
		if(null == dbFile)
			return null;
		return dbFile.getInputStream();			
	}
	
	public GridFSDBFile findAsFile(ObjectId id) {
		return gridFS.find(id);
	}
	
	public void remove(ObjectId id) {
		gridFS.remove(id);
	}
	
	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket(45678);
			InetAddress serverAddress = InetAddress.getByName("192.168.1.122");
			String str = "hello";
			byte data[] = str.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, 45678);
			socket.send(packet);
		} catch (SocketException e) {
			System.out.println("SocketException");
			e.printStackTrace();
		} catch (UnknownHostException e) {
			System.out.println("UnknownHostException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
	}
}
