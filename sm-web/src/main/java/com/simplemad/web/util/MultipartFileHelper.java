package com.simplemad.web.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class MultipartFileHelper {

	/**
	 * @param request
	 * @return a map containing the parameter names as keys, and the {@link File} objects as values
	 */
	public static Map<String, File> getFilesMap(HttpServletRequest request) {
		Map<String, File> filesMap = new HashMap<String, File>();
		if (!ServletFileUpload.isMultipartContent(request))
			return filesMap;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> multipartFilesMap = multipartRequest.getFileMap();
		Iterator<Entry<String, MultipartFile>> it = multipartFilesMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, MultipartFile> entry = it.next();
			String name = entry.getKey();
			CommonsMultipartFile multipartFile = (CommonsMultipartFile) entry.getValue();
			DiskFileItem fileItem = (DiskFileItem) multipartFile.getFileItem();
			File file = fileItem.getStoreLocation();
			filesMap.put(name, file);
		}
		return filesMap;
	}

	/**
	 * @param request
	 * @return return the {@link File} object {@link List}
	 */
	public static List<File> getFilesList(HttpServletRequest request) {
		List<File> filesList = new ArrayList<File>();
		Map<String, File> filesMap = getFilesMap(request);
		Iterator<File> it = filesMap.values().iterator();
		while (it.hasNext()) {
			filesList.add(it.next());
		}
		return filesList;
	}
	
	/**
	 * @param request
	 * @return a map containing the parameter names as keys, and the {@link DiskFileItem} objects as values
	 */
	public static Map<String, DiskFileItem> getDiskFileItemsMap(HttpServletRequest request) {
		Map<String, DiskFileItem> filesMap = new HashMap<String, DiskFileItem>();
		if (!ServletFileUpload.isMultipartContent(request))
			return filesMap;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> multipartFilesMap = multipartRequest.getFileMap();
		Iterator<Entry<String, MultipartFile>> it = multipartFilesMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, MultipartFile> entry = it.next();
			String name = entry.getKey();
			CommonsMultipartFile multipartFile = (CommonsMultipartFile) entry.getValue();
			DiskFileItem fileItem = (DiskFileItem) multipartFile.getFileItem();
			filesMap.put(name, fileItem);
		}
		return filesMap;
	}

	/**
	 * @param request
	 * @return return the {@link DiskFileItem} object {@link List}
	 */
	public static List<DiskFileItem> getDiskFileItemsList(HttpServletRequest request) {
		List<DiskFileItem> filesList = new ArrayList<DiskFileItem>();
		Map<String, DiskFileItem> filesMap = getDiskFileItemsMap(request);
		Iterator<DiskFileItem> it = filesMap.values().iterator();
		while (it.hasNext()) {
			filesList.add(it.next());
		}
		return filesList;
	}
}
