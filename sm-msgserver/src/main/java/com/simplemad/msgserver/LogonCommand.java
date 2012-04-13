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


public class LogonCommand implements IMessageCommand {
	
	private final Logger logger = LoggerFactory.getLogger(LogonCommand.class);

	private Message msg;
	 
	public LogonCommand(Message msg) {
		this.msg = msg;
	}
	
	@Override
	public void execute() {
		/*new Thread(new Runnable() {
			
			@Override
			public void run() {
				logon();
			}
		}).start();*/
		logon();
	}
	
	protected void logon() {
		String content = msg.getContent();
		String host = msg.getSource().getHost();
		int port = msg.getSource().getPort();
		logger.debug("LogonCommand host/port:" + host + "/" + port);
		UserAccount userAccount = JacksonUtil.getObject(content, UserAccount.class);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mobile", userAccount.getMobile());
		params.put("password", userAccount.getPassword());
		params.put("host", host);
		params.put("port", port);
		try {
			boolean result = MsgServerUtil.doPost(MsgServerParameter.USER_LOGIN, params, Boolean.class);
			logger.debug("login result " + result);
			MsgServerUtil.doSend(MessageType.LOGON, result, host, port);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
