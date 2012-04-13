package com.simplemad.ad.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplemad.ad.domain.AdStandard;
import com.simplemad.ad.domain.AdStandardMoney;
import com.simplemad.base.dao.BaseDAO;

@Service
public class AdStandardServiceImpl implements AdStandardService {

	@Autowired
	private BaseDAO dao;
	
	@Override
	public AdStandardMoney create(AdStandardMoney asm) {
		if(asm == null || asm.getStandard() == null || asm.getUserMoney() < 0 || asm.getClientMoney() < 0 || asm.getClientMoney() < asm.getUserMoney()) {
			return null;
		}
		AdStandardMoney exist = find(asm.getStandard(), asm.getConditionCount());
		if(exist != null) {
			return null;
		} else {
			dao.save(asm);
			return asm;
		}
	}
	
	@Override
	public AdStandardMoney update(AdStandardMoney asm) {
		if(asm == null || asm.getStandard() == null || asm.getUserMoney() <= 0 || asm.getClientMoney() <= 0 || asm.getClientMoney() < asm.getUserMoney()) {
			return null;
		}
		AdStandardMoney exist = find(asm.getStandard(), asm.getConditionCount());
		if(exist != null) {
			exist.setClientMoney(asm.getClientMoney());
			exist.setUserMoney(asm.getUserMoney());
			dao.save(exist);
			return exist;
		} else {
			return null;
		}
	}

	@Override
	public AdStandardMoney find(AdStandard as, int conditionCount) {
		List<AdStandardMoney> asmList = dao.findAll(AdStandardMoney.class, "standard", as);
		for(AdStandardMoney asm : asmList) {
			if(asm.getConditionCount() == conditionCount) {
				return asm;
			}
		}
		return null;
	}

	@Override
	public List<AdStandardMoney> findAll() {
		return dao.findAll(AdStandardMoney.class);
	}

	@Override
	public AdStandardMoney find(String adstandardId) {
		return dao.findOne(AdStandardMoney.class, "id", new ObjectId(adstandardId));
	}

}
