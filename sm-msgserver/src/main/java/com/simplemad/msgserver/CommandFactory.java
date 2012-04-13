package com.simplemad.msgserver;

import com.simplemad.bean.Message;
import com.simplemad.bean.MessageType;

public class CommandFactory {
	
	public static IMessageCommand create(Message msg) {
		if(msg == null || msg.getType() == null) {
			return null;
		} else if (MessageType.LOGON.equals(msg.getType())) {
			return new LogonCommand(msg);
		} else if(MessageType.REGISTER.equals(msg.getType())) {
			return new RegisterCommand(msg);
		} else if(MessageType.LOGOFF.equals(msg.getType())) {
			return new LogoffCommand(msg);
		} else if(MessageType.AD_EFFECT.equals(msg.getType())) {
			return new AdvertisementEffectCommand(msg);
		} else if(MessageType.AD.equals(msg.getType())) {
			return new AdvertisementCommand(msg);
		} else {
			return null;
		}
	}
	
}
