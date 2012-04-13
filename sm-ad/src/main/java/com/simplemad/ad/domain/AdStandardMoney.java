package com.simplemad.ad.domain;

import com.google.code.morphia.annotations.Entity;
import com.simplemad.base.domain.BaseEntity;

@Entity
public class AdStandardMoney extends BaseEntity {

	private static final long serialVersionUID = -5250760048512134431L;

	private AdStandard standard;
	
	private long userMoney;
	
	private long clientMoney;
	
	private int conditionCount;

	public AdStandard getStandard() {
		return standard;
	}

	public void setStandard(AdStandard standard) {
		this.standard = standard;
	}

	public long getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(long userMoney) {
		this.userMoney = userMoney;
	}

	public long getClientMoney() {
		return clientMoney;
	}

	public void setClientMoney(long clientMoney) {
		this.clientMoney = clientMoney;
	}

	public int getConditionCount() {
		return conditionCount;
	}

	public void setConditionCount(int conditionCount) {
		this.conditionCount = conditionCount;
	}
}
