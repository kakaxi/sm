package com.simplemad.ad.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplemad.ad.domain.ExchangeStatus;
import com.simplemad.ad.domain.MoneyExchangeRecord;
import com.simplemad.base.dao.BaseDAO;
import com.simplemad.base.domain.User;
import com.simplemad.base.domain.UserProfile;
import com.simplemad.base.service.UserService;

@Service
public class MoneyExchangeServiceImpl implements MoneyExchangeService {

	@Autowired
	private BaseDAO dao;
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean apply(long mobile, String password, long exchangeMoney) {
		UserProfile profile = userService.findUserProfile(mobile);
		if(profile == null) {
			return false;
		}
		if(!profile.getUser().getPassword().equals(password)) {
			return false;
		}
		if(profile.getMoney() < exchangeMoney) {
			return false;
		}
		dao.save(createRecord(profile.getUser(), exchangeMoney));
		userService.exchangMoney(mobile, exchangeMoney);
		return true;
	}
	
	private MoneyExchangeRecord createRecord(User user, long exchangeMoney) {
		MoneyExchangeRecord record = new MoneyExchangeRecord();
		record.setAppliedDate(new Date());
		record.setMoney(exchangeMoney);
		record.setUser(user);
		record.setStatus(ExchangeStatus.APPLIED);
		return record;
	}

	@Override
	public boolean process(String recordId) {
		MoneyExchangeRecord record = dao.findOne(MoneyExchangeRecord.class, "id", new ObjectId(recordId));
		if(record == null || !ExchangeStatus.APPLIED.equals(record.getStatus())) {
			return false;
		}
		record.setStatus(ExchangeStatus.PROCESSING);
		record.setProcessedDate(new Date());
		dao.save(record);
		return true;
	}

	@Override
	public boolean complete(String recordId) {
		MoneyExchangeRecord record = dao.findOne(MoneyExchangeRecord.class, "id", new ObjectId(recordId));
		if(record == null || record.getUser() == null || !ExchangeStatus.PROCESSING.equals(record.getStatus())) {
			return false;
		}
		boolean isSuccess = userService.exchangMoney(record.getUser().getMobileNo(), record.getMoney());
		if(!isSuccess) {
			return false;
		}
		record.setStatus(ExchangeStatus.COMPLETED);
		record.setCompletedDate(new Date());
		dao.save(record);
		return true;
	}

	@Override
	public List<MoneyExchangeRecord> findAll(long mobile) {
		User user = userService.findUser(mobile);
		return dao.findAll(MoneyExchangeRecord.class, "user", user);
	}

	@Override
	public List<MoneyExchangeRecord> findAll(ExchangeStatus status) {
		return dao.findAll(MoneyExchangeRecord.class, "status", status);
	}

}
