package com.simplemad.msgserver;

import com.simplemad.bean.Message;
import com.simplemad.message.util.AddressUtil;

public class AdvertisementCommand implements IMessageCommand {

	private Message msg;
	
	public AdvertisementCommand(Message msg) {
		this.msg = msg;
	}
	@Override
	public void execute() {
		//转化至客户端
		MsgServerUtil.doSend(AddressUtil.convert(msg.getTarget()), msg);
	}

}
