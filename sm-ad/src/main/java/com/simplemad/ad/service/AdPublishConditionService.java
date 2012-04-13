package com.simplemad.ad.service;

import com.simplemad.ad.domain.AdPublishCondition;
import com.simplemad.ad.domain.Advertisement;

public interface AdPublishConditionService {

	public AdPublishCondition create(AdPublishCondition condition);
	
	public AdPublishCondition update(AdPublishCondition condition);
	
	public AdPublishCondition copyCondition(String adId, String conditionId);
	
	public AdPublishCondition find(Advertisement ad);
}
