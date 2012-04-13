package com.simplemad.ad.service;

import java.util.List;

import com.simplemad.ad.domain.ExchangeStatus;
import com.simplemad.ad.domain.MoneyExchangeRecord;

public interface MoneyExchangeService {

	public boolean apply(long mobile, String password, long exchangeMoney);
	
	public boolean process(String recordId);
	
	public boolean complete(String recordId);
	
	public List<MoneyExchangeRecord> findAll(ExchangeStatus status);
	
	public List<MoneyExchangeRecord> findAll(long mobile);
}
