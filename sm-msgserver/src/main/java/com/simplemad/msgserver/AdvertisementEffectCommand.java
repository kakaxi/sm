package com.simplemad.msgserver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simplemad.base.util.StringUtil;
import com.simplemad.bean.AdEffectEntity;
import com.simplemad.bean.Message;
import com.simplemad.message.util.JacksonUtil;
import com.simplemad.parameter.MsgServerParameter;

public class AdvertisementEffectCommand implements IMessageCommand {
	private final Logger logger = LoggerFactory.getLogger(AdvertisementEffectCommand.class);
	
	private Message msg;
	
	public AdvertisementEffectCommand(Message msg) {
		this.msg = msg;
	}
	
	@Override
	public void execute() {
		String content = msg.getContent();
		AdEffectEntity entity = JacksonUtil.getObject(content, AdEffectEntity.class);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("adId", entity.getAdId());
		params.put("mobile", entity.getMobile());
		
		String url = null;
		try {
			switch (entity.getKey()) {
			case AdEffectEntity.KEY_AD_RECEIVED:
				url = MsgServerParameter.AD_EFFECT_UPDATE_RECEIVED;
				break;
			case AdEffectEntity.KEY_AD_DOWNLOADING:
				url = MsgServerParameter.AD_EFFECT_UPDATE_DOWNLOADING;
				break;
			case AdEffectEntity.KEY_AD_DOWNLOADED:
				url = MsgServerParameter.AD_EFFECT_UPDATE_DOWNLOADED;
				break;
			case AdEffectEntity.KEY_AD_OPENED:
				url = MsgServerParameter.AD_EFFECT_UPDATE_OPENED;
				break;
			case AdEffectEntity.KEY_AD_COMPLETED:
				url = MsgServerParameter.AD_EFFECT_UPDATE_COMPLETED;
				break;
			case AdEffectEntity.KEY_AD_SHARED:
				url = MsgServerParameter.AD_EFFECT_UPDATE_SHARED;
				break;
			case AdEffectEntity.KEY_AD_TIMES:
				url = MsgServerParameter.AD_EFFECT_UPDATE_TIMES;
				params.put("times", entity.getValue());
				break;
			default:
				break;
			}
			if(!StringUtil.isEmpty(url)) {
				MsgServerUtil.doPost(url, params, Boolean.class);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
