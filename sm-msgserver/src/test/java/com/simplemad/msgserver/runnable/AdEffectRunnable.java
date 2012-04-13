package com.simplemad.msgserver.runnable;

import com.simplemad.bean.AdEffectEntity;
import com.simplemad.bean.Message;
import com.simplemad.bean.MessageType;
import com.simplemad.message.util.JacksonUtil;
import com.simplemad.msgserver.CommandFactory;
import com.simplemad.msgserver.IMessageCommand;

public class AdEffectRunnable implements IRunnable {

	@Override
	public Runnable getRunnable() {
		return new Runnable() {
			@Override
			public void run() {
				IMessageCommand command = CommandFactory.create(createMessage());
				command.execute();
			}
		};
	}

	@Override
	public Message createMessage() {
		Message msg = new Message();
		
		AdEffectEntity entity = new AdEffectEntity();
		entity.setKey(AdEffectEntity.KEY_AD_RECEIVED);
		entity.setMobile(18688432301L);
		entity.setAdId("4f4b9b3ad7cc5abfa5d820bc");
		
		msg.setContent(JacksonUtil.toJSON(entity));
		msg.setType(MessageType.AD_EFFECT);
		return msg;
	}

}
