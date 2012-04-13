package com.simplemad.web.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.gridfs.GridFSDBFile;
import com.simplemad.base.domain.ClientVersion;
import com.simplemad.base.service.ClientVersionService;
import com.simplemad.base.util.CollectionUtil;
import com.simplemad.bean.PhoneSystemType;
import com.simplemad.bean.UpgradeInfo;
import com.simplemad.parameter.MsgServerParameter;
import com.simplemad.web.common.controller.BaseController;
import com.simplemad.web.util.MultipartFileHelper;

@Controller
public class UpgradeController extends BaseController {

	@Autowired
	private ClientVersionService versionService;
	
	@RequestMapping
	@ResponseBody
	public UpgradeInfo getUpgradeInfo(PhoneSystemType type) {
		ClientVersion version = versionService.find(type);
		if(version == null) {
			return null;
		}
		UpgradeInfo info = new UpgradeInfo();
		info.setVersionCode(version.getVersion());
		info.setUpgradeUrl("http://" + MsgServerParameter.HTTP_HOST + ":" + MsgServerParameter.HTTP_PORT + "/" + MsgServerParameter.PROJECT_NAME + "/upgrade/download.do?type=" + type);
		return info;
	}
	
	@RequestMapping
	@ResponseBody
	public void download(PhoneSystemType type, HttpServletResponse response) {
		GridFSDBFile file = versionService.findUpgradeFile(type);
		OutputStream toClient = null;
		try {
			response.reset();
	        toClient = new BufferedOutputStream(response  
	                .getOutputStream());
	        response.setContentType("application/octet-stream");
	        if(file == null) {
	        	response.addHeader("Content-Length", "" + 0);
	        } else {
	        	response.addHeader("Content-Length", "" + file.getLength());
	        	InputStream is = file.getInputStream();
		        byte[] dataArray = new byte[1024];
		        int blockLength = 0;
		        while((blockLength = is.read(dataArray)) != -1) {
		        	 toClient.write(dataArray, 0, blockLength);
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
	
	@RequestMapping
	public ModelAndView upgrade_list() {
		ModelAndView mv = new ModelAndView("upgrade/upgrade_list");
		List<ClientVersion> versionList = versionService.findAll();
		mv.addObject("versionList", versionList);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView upgrade_start() {
		return new ModelAndView("upgrade/upgrade_start");
	}
	
	@RequestMapping
	public ModelAndView upgrade(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("forward:upgrade_list.do");
		List<DiskFileItem> fileList = MultipartFileHelper.getDiskFileItemsList(request);
		if(CollectionUtil.isEmpty(fileList)) {
			return mv;
		}
		DiskFileItem item = fileList.get(0);
		versionService.upgrade(item.getName(), item.getStoreLocation());
		return mv;
	}
}
