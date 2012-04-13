package com.simplemad.msgserver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.simplemad.bean.Message;
import com.simplemad.bean.UserAccount;
import com.simplemad.message.util.JacksonUtil;
import com.simplemad.parameter.MsgServerParameter;

public class LogoffCommand implements IMessageCommand {

	private Message msg;
	
	public LogoffCommand(Message msg) {
		this.msg = msg;
	}
	
	@Override
	public void execute() {
		String content = msg.getContent();
		UserAccount userAccount = JacksonUtil.getObject(content, UserAccount.class);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mobile", userAccount.getMobile());
		params.put("password", userAccount.getPassword());
		try {
			MsgServerUtil.doPost(MsgServerParameter.USER_LOGOUT, params, Boolean.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
