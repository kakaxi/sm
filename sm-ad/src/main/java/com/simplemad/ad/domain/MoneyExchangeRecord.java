package com.simplemad.ad.domain;

import java.util.Date;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;
import com.simplemad.base.domain.BaseEntity;
import com.simplemad.base.domain.User;

@Entity
public class MoneyExchangeRecord extends BaseEntity {

	private static final long serialVersionUID = 8638742403879996199L;

	@Reference(lazy = true)
	private User user;
	
	private long money;
	
	private Date appliedDate;
	
	private Date processedDate;
	
	private Date completedDate;
	
	private ExchangeStatus status;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public Date getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}
	
	public ExchangeStatus getStatus() {
		return status;
	}

	public void setStatus(ExchangeStatus status) {
		this.status = status;
	}

	public Date getProcessedDate() {
		return processedDate;
	}

	public void setProcessedDate(Date processedDate) {
		this.processedDate = processedDate;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
}
