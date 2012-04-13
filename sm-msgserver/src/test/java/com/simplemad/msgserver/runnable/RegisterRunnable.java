package com.simplemad.msgserver.runnable;

import java.util.Date;

import com.simplemad.bean.Message;
import com.simplemad.bean.MessageType;
import com.simplemad.bean.NetAddress;
import com.simplemad.bean.UserAccount;
import com.simplemad.message.util.JacksonUtil;
import com.simplemad.msgserver.CommandFactory;
import com.simplemad.msgserver.IMessageCommand;
import com.simplemad.parameter.MsgServerParameter;

public class RegisterRunnable implements IRunnable{

	@Override
	public Message createMessage() {
		Message msg = new Message();
		msg.setSendDate(new Date());
		msg.setType(MessageType.REGISTER);
		
		NetAddress target = new NetAddress();
		target.setHost(MsgServerParameter.HTTP_HOST);
		target.setPort(MsgServerParameter.HTTP_PORT);
		msg.setTarget(target);
		
		NetAddress source = new NetAddress();
		source.setHost("192.168.100.1");
		source.setPort(12345);
		msg.setSource(source);
		
		UserAccount account = new UserAccount();
		account.setMobile(18688432301L);
		account.setPassword("123123");
		account.setRemLoginStatus(true);
		account.setRemPassword(true);
		String content = JacksonUtil.toJSON(account);
		msg.setContent(content);
		
		return msg;
	}

	@Override
	public Runnable getRunnable() {
		return new Runnable() {

			@Override
			public void run() {
				IMessageCommand command = CommandFactory.create(createMessage());
				System.out.println("create command: " + System.currentTimeMillis());
				command.execute();
			}
			
		};
	}

}
