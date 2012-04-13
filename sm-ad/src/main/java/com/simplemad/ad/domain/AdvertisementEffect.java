package com.simplemad.ad.domain;

import java.util.Date;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;
import com.simplemad.base.domain.BaseEntity;
import com.simplemad.base.domain.User;

@Entity
public class AdvertisementEffect extends BaseEntity {

	private static final long serialVersionUID = 386406470017528551L;
	
	@Reference(lazy=true)
	private User user;
	
	@Reference(lazy=true)
	private Advertisement advertisement;
	
	private boolean isReceivedMsg;
	
	private boolean isDownloading;
	
	private boolean isDownloaded;
	
	private boolean isOpened;
	
	private boolean isCompleted;
	
	private boolean isShared;
	
	private boolean isSharable;
	
	private int times;
	
	private Money money;
	
	private Date startDate;
	
	private Date endDate;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Advertisement getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

	public boolean isReceivedMsg() {
		return isReceivedMsg;
	}

	public void setReceivedMsg(boolean isReceivedMsg) {
		this.isReceivedMsg = isReceivedMsg;
	}

	public boolean isDownloading() {
		return isDownloading;
	}

	public void setDownloading(boolean isDownloading) {
		this.isDownloading = isDownloading;
	}

	public boolean isDownloaded() {
		return isDownloaded;
	}

	public void setDownloaded(boolean isDownloaded) {
		this.isDownloaded = isDownloaded;
	}

	public boolean isOpened() {
		return isOpened;
	}

	public void setOpened(boolean isOpened) {
		this.isOpened = isOpened;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public boolean isShared() {
		return isShared;
	}

	public void setShared(boolean isShared) {
		this.isShared = isShared;
	}

	public boolean isSharable() {
		return isSharable;
	}

	public void setSharable(boolean isSharable) {
		this.isSharable = isSharable;
	}

	public Money getMoney() {
		return money;
	}

	public void setMoney(Money money) {
		this.money = money;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public boolean isOutOfDate() {
		if(endDate == null) {
			return true;
		} else {
			return endDate.before(new Date());
		}
		
	}
	
}
