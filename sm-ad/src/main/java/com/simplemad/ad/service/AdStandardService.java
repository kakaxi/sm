package com.simplemad.ad.service;

import java.util.List;

import com.simplemad.ad.domain.AdStandard;
import com.simplemad.ad.domain.AdStandardMoney;

public interface AdStandardService {

	AdStandardMoney create(AdStandardMoney asm);
	
	AdStandardMoney update(AdStandardMoney asm);
	
	AdStandardMoney find(AdStandard as, int conditionCount);
	
	AdStandardMoney find(String adstandardId);
	
	List<AdStandardMoney> findAll();
}
