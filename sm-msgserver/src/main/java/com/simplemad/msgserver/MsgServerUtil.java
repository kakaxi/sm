package com.simplemad.msgserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simplemad.bean.Message;
import com.simplemad.bean.MessageType;
import com.simplemad.bean.NetAddress;
import com.simplemad.message.util.AddressUtil;
import com.simplemad.message.util.JacksonUtil;
import com.simplemad.parameter.MsgServerParameter;

public class MsgServerUtil {
	private static Logger logger = LoggerFactory.getLogger(MsgServerUtil.class);
		
	public static void doSend(SocketAddress address, Message msg) {
		SimplemadServer server = SimplemadServer.instance();
		server.getServer().send(address, msg);
	}
	
	public static void doSend(MessageType msgType, Object obj) {
		doSend(msgType, obj, MsgServerParameter.HTTP_HOST, MsgServerParameter.UDP_PORT);
	}
	
	public static void doSend(MessageType msgType, Object obj, String hostName, int port) {
		
		InetSocketAddress host = new InetSocketAddress(hostName, port);
		Message msg = new Message();
		logger.debug("before convert: {}", System.currentTimeMillis());
		NetAddress target = AddressUtil.convert(host);
		logger.debug("convert host: {}" , System.currentTimeMillis());
		msg.setTarget(target);
		msg.setSendDate(new Date());
		msg.setType(msgType);
		msg.setContent(JacksonUtil.toJSON(obj));
		
		doSend(host, msg);
	}
	
	public static <T> T doGet(String url, Class<T> clazz) throws IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		if(response.getStatusLine().equals(HttpStatus.SC_OK)) {
			String content = EntityUtils.toString(response.getEntity());
			return JacksonUtil.getObject(content, clazz);
		} else {
			return null;
		}
	}
	
	public static <T> T doPost(String url, Map<String, Object> params, Class<T> clazz) throws IOException {
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		List<NameValuePair> paramList = createNameValuePairs(params);
		HttpEntity entity = new UrlEncodedFormEntity(paramList, "UTF-8");
		request.setEntity(entity);
		HttpResponse response = client.execute(request);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String content = EntityUtils.toString(response.getEntity());
			return JacksonUtil.getObject(content, clazz);
		} else {
			try {
				return clazz.newInstance();
			} catch (InstantiationException e) {
				logger.error(e.getMessage(), e);
				return null;
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage(), e);
				return null;
			}
		}
	}

	private static List<NameValuePair> createNameValuePairs(Map<String, Object> params) {
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		if(params == null) {
			return paramList;
		}
		Set<Entry<String, Object>> paramsSet = params.entrySet();
		Iterator<Entry<String, Object>> paramsIt = paramsSet.iterator();
		while(paramsIt.hasNext()) {
			Entry<String, Object> entry = paramsIt.next();
			paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
		}
		return paramList;
	}
}
