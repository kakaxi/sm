package com.simplemad.msgserver.runnable;

import java.util.ArrayList;
import java.util.List;

import com.simplemad.bean.Message;
import com.simplemad.bean.MessageType;
import com.simplemad.bean.NetAddress;
import com.simplemad.msgserver.CommandFactory;
import com.simplemad.msgserver.IMessageCommand;

public class AdRunnable implements IRunnable{

	@Override
	public Message createMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Runnable getRunnable() {
		return new Runnable() {
			@Override
			public void run() {
				List<Message> messages = createMuiltiAdMsg();
				for(Message msg : messages) {
					System.out.println(msg.getTarget().getHost());
					IMessageCommand command = CommandFactory.create(msg);
					command.execute();
				}								
			}
		};
	}
	
	private Message createAdMsg(String hostName) {
		Message msg = new Message();
		
		msg.setType(MessageType.AD);
		msg.setContent("<html><body><b>粉肠一条</b></body></html>");
		
		NetAddress target = new NetAddress();
		target.setHost(hostName);
		target.setPort(12345);
		msg.setTarget(target);
		
		return msg;
	}
	
	private List<Message> createMuiltiAdMsg(){
		List<Message> messages = new ArrayList<Message>();
		for(int i=1; i< 255; i++) {
			String ipAddress = "192.168.100." + i;
			Message msg = createAdMsg(ipAddress);
			messages.add(msg);
		}
		return messages;
	}

}
