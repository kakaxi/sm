package com.simplemad.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mongodb.gridfs.GridFSDBFile;
import com.simplemad.ad.service.AdvertisementService;
import com.simplemad.base.util.StringUtil;
import com.simplemad.web.common.controller.BaseController;

/**
 * 供客户端使用的广告文件下载controller
 * @author kamen
 *
 */
@Controller
public class AdDownloadController extends BaseController {

	@Autowired
	private AdvertisementService adService;
	
	@RequestMapping
	public void downloadIcon(String adId, HttpServletRequest request, HttpServletResponse response) {
		GridFSDBFile icon = adService.findIconAsFile(adId);
		if(icon == null) {
			return;
		}
		download(icon, request, response);
	}
	
	@RequestMapping
	public void downloadContent(String adId, HttpServletRequest request, HttpServletResponse response) {
		GridFSDBFile content = adService.findContentAsFile(adId);
		if(content == null) {
			return;
		}
		download(content, request, response);
	}
	
	private void download(GridFSDBFile file, HttpServletRequest request, HttpServletResponse response) { 
		OutputStream toClient = null;
		try {
			response.reset();
	        toClient = new BufferedOutputStream(response  
	                .getOutputStream());
	        response.setContentType("application/octet-stream");
	        if(file == null) {
	        	response.addHeader("Content-Length", "" + 0);
	        } else {
	        	String range = request.getHeader("RANGE");
		        long start = 0;
		        long end = file.getLength();
		        if(!StringUtil.isEmpty(range)) {
		        	String[] rangeArray = range.split("-");
		        	if(rangeArray.length == 1) {
		        		start = Long.valueOf(rangeArray[0].replace("bytes=", ""));
		        	} else if(rangeArray.length == 2) {
		        		start = Long.valueOf(rangeArray[0].replace("bytes=", ""));
		        		end = Long.valueOf(rangeArray[1]);
		        	}
		        }
	        	response.addHeader("Content-Length", "" + file.getLength());
	        	InputStream is = file.getInputStream();
	        	is.skip(start);
		        byte[] dataArray = new byte[1024];
		        int blockLength = 0;
		        while((blockLength = is.read(dataArray)) != -1) {
		        	if(start + blockLength > end) {
		        		toClient.write(dataArray, 0, (int) (end - start));
		        		break;
		        	} else {
		        		toClient.write(dataArray, 0, blockLength);
		        		start += blockLength;
		        	}
		        }
//		        toClient.flush();
	        }
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
			if(toClient != null)
				toClient.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void download(File file, HttpServletRequest request, HttpServletResponse response) { 
		OutputStream toClient = null;
		try {
			response.reset();  
	        response.addHeader("Content-Disposition", "attachment;filename="  
	                + file.getName());  
	        response.addHeader("Content-Length", ""+file.length());  
	        response.setContentType("bin");  
	        toClient = new BufferedOutputStream(response  
	                .getOutputStream());  
	        response.setContentType("application/octet-stream"); 
	        byte[] dataArray = new byte[1024];
	        FileInputStream fis = new FileInputStream(file);
	        while(fis.read(dataArray) != -1) {
	        	 toClient.write(dataArray);  
	        }
	        toClient.flush();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
			if(toClient != null)
				toClient.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
