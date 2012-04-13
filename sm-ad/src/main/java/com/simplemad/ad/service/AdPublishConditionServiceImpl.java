package com.simplemad.ad.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplemad.ad.domain.AdPublishCondition;
import com.simplemad.ad.domain.AdStandardMoney;
import com.simplemad.ad.domain.AdStatus;
import com.simplemad.ad.domain.Advertisement;
import com.simplemad.ad.domain.Money;
import com.simplemad.base.dao.BaseDAO;

@Service
public class AdPublishConditionServiceImpl implements AdPublishConditionService {

	@Autowired
	private BaseDAO dao;
	
	@Autowired
	private AdStandardService standardService;
	
	@Override
	public AdPublishCondition create(AdPublishCondition condition) {
		if(condition.getId() != null) {
			return null;
		}
		if(condition.getAdvertisement() == null || condition.getAdvertisement().getId() == null) {
			return null;
		}
		Advertisement ad = dao.findOne(Advertisement.class, "id", condition.getAdvertisement().getId());
		if(ad.getStandard() == null || !ad.getStatus().equals(AdStatus.AD_AUDIT_PASSED)) {
			return null;
		}
		if(find(ad) != null) {
			return null;
		}
		AdPublishCondition newCondition = new AdPublishCondition();
		newCondition.setAdvertisement(ad);
		
		copyFieldTo(condition, newCondition);
		
		dao.save(newCondition);
		
		return newCondition;
	}
	
	private void calculateMoney(AdPublishCondition condition) {
		AdStandardMoney asm = standardService.find(condition.getAdvertisement().getStandard(), condition.getCondition().getCondionNum());
		Money money = new Money();
		money.setUserAdMoney(asm.getUserMoney());
		money.setAdMoney(asm.getClientMoney() - asm.getUserMoney());
		if(condition.isSharable()) {
			//设置分享微博所得金额
			money.setSharingMoney(50);
			money.setUserSharingMoney(100);
		}
		condition.setMoney(money);
	}
	
	private void modifyAdStatus(ObjectId adId, AdStatus status) {
		Advertisement ad = dao.findOne(Advertisement.class, "id", adId);
		ad.setStatus(status);
		dao.save(ad);
	}

	@Override
	public AdPublishCondition update(AdPublishCondition condition) {
		if(condition.getId() == null) {
			return null;
		}
		AdPublishCondition oldCondition = dao.findOne(AdPublishCondition.class, "id", condition.getId());
		Advertisement ad = oldCondition.getAdvertisement();
		if(ad.getStandard() == null || !ad.getStatus().equals(AdStatus.AD_AUDIT_PASSED)) {
			return null;
		}
		copyFieldTo(condition, oldCondition);
		
		dao.save(oldCondition);
		
		return oldCondition;
	}
	
	private void copyFieldTo(AdPublishCondition fromCondition, AdPublishCondition toCondition) {
		toCondition.setCondition(fromCondition.getCondition());
		toCondition.setSharable(fromCondition.isSharable());
		
		calculateMoney(toCondition);
		
	}

	@Override
	public AdPublishCondition copyCondition(String adId, String conditionId) {
		Advertisement ad = dao.findOne(Advertisement.class, "id", new ObjectId(adId));
		if(ad.getStandard() == null || !ad.getStatus().equals(AdStatus.AD_AUDIT_PASSED)) {
			return null;
		}
		AdPublishCondition condition = dao.findOne(AdPublishCondition.class, "id", new ObjectId(conditionId));
		condition.setId(null);
		condition.setAdvertisement(ad);
		modifyAdStatus(ad.getId(), AdStatus.NEW);
		calculateMoney(condition);
		dao.save(condition);
		return condition;
	}

	@Override
	public AdPublishCondition find(Advertisement ad) {
		return dao.findOne(AdPublishCondition.class, "advertisement", ad);
	}

}
