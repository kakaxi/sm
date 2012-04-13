package com.simplemad.msgserver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simplemad.bean.Message;
import com.simplemad.bean.MessageType;
import com.simplemad.bean.UserAccount;
import com.simplemad.message.util.JacksonUtil;
import com.simplemad.parameter.MsgServerParameter;

public class RegisterCommand implements IMessageCommand {
	
	private Logger logger = LoggerFactory.getLogger(RegisterCommand.class);

	private Message msg;
	
	public RegisterCommand(Message msg) {
		this.msg = msg;
	}
	
	@Override
	public void execute() {
		String content = msg.getContent();
		String host = msg.getSource().getHost();
		int port = msg.getSource().getPort();
		logger.info("RegisterCommand host/port: {}/{}" ,host , port);
		logger.debug("******************************************");
		
		UserAccount userAccount = JacksonUtil.getObject(content, UserAccount.class);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mobile", userAccount.getMobile());
		params.put("password", userAccount.getPassword());
		params.put("host", host);
		params.put("port", port);
		
		logger.debug("parepare param: {}", System.currentTimeMillis());
		try {
			boolean result = MsgServerUtil.doPost(MsgServerParameter.USER_REGISTER, params, Boolean.class);
			logger.debug("register result: {}", System.currentTimeMillis());
			MsgServerUtil.doSend(MessageType.REGISTER, result, host, port);
			logger.debug("send back to mobile: {}" , System.currentTimeMillis());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
