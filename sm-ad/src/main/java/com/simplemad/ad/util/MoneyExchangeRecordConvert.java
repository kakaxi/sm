package com.simplemad.ad.util;

import com.simplemad.base.util.DateUtil;
import com.simplemad.bean.MoneyExchangeRecord;

public class MoneyExchangeRecordConvert {

	public static MoneyExchangeRecord convert(com.simplemad.ad.domain.MoneyExchangeRecord record) {
		MoneyExchangeRecord beanRecord = new MoneyExchangeRecord();
		beanRecord.setAppliedDate(DateUtil.translateToString(record.getAppliedDate(), DateUtil.DEFAULT_PATTERN));
		beanRecord.setItem(record.getMoney()/100 + "无话费");
		beanRecord.setStatus(record.getStatus().getName());
		return beanRecord;
	}
}
