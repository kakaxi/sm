package com.simplemad.ad.domain;

import java.util.Date;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.NotSaved;
import com.google.code.morphia.annotations.Reference;
import com.simplemad.base.domain.BaseEntity;
import com.simplemad.bean.AdvertisementType;

@Entity
public class Advertisement extends BaseEntity {

	private static final long serialVersionUID = 5782023525196939508L;
	
	@Reference(lazy=true)
	private Advertiser advertiser;
	
	private String name;
	
	private Date createdDate;
	
	private long publishQuantity;
	
	@NotSaved
	private long receivedQuantity;
	
	@NotSaved
	private long downloadingQuantity;
	
	@NotSaved
	private long downloadedQuantity;
	
	@NotSaved
	private long openedQuantity;
	
	@NotSaved
	private long completedQuantity;
	
	/**
	 * 所有分享总数(计费+免费)
	 */
	@NotSaved
	private long sharedQuantity;
	
	/**
	 * 广告分享总费用
	 */
	@NotSaved
	private long sharedTotalMoney;
	
	/**
	 * 广告总费用(不包括分享总费用)
	 */
	@NotSaved
	private long adTotalMoney;
	
	/**
	 * 所有计费分享总数
	 */
	@NotSaved
	private long sharedQuantityFee;
	
	@NotSaved
	private long times;
	
	private AdvertisementType adType;
	
	private AdStandard standard;
	
	private AdStatus status;
	
	private String iconId;
	
	private String contentId;
	
	private String url;
	
	private String iconExtendedName;
	
	private String contentExtendedName;
	
	public String getIconId() {
		return iconId;
	}

	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getIconExtendedName() {
		return iconExtendedName;
	}

	public void setIconExtendedName(String iconExtendedName) {
		this.iconExtendedName = iconExtendedName;
	}

	public String getContentExtendedName() {
		return contentExtendedName;
	}

	public void setContentExtendedName(String contentExtendedName) {
		this.contentExtendedName = contentExtendedName;
	}

	public Advertiser getAdvertiser() {
		return advertiser;
	}

	public void setAdvertiser(Advertiser advertiser) {
		this.advertiser = advertiser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getPublishQuantity() {
		return publishQuantity;
	}

	public void setPublishQuantity(long publishQuantity) {
		this.publishQuantity = publishQuantity;
	}

	public long getReceivedQuantity() {
		return receivedQuantity;
	}

	public void setReceivedQuantity(long receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}

	public long getDownloadedQuantity() {
		return downloadedQuantity;
	}

	public void setDownloadedQuantity(long downloadedQuantity) {
		this.downloadedQuantity = downloadedQuantity;
	}

	public long getOpenedQuantity() {
		return openedQuantity;
	}

	public void setOpenedQuantity(long openedQuantity) {
		this.openedQuantity = openedQuantity;
	}

	public long getCompletedQuantity() {
		return completedQuantity;
	}

	public void setCompletedQuantity(long completedQuantity) {
		this.completedQuantity = completedQuantity;
	}

	public AdvertisementType getAdType() {
		return adType;
	}

	public void setAdType(AdvertisementType adType) {
		this.adType = adType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getDownloadingQuantity() {
		return downloadingQuantity;
	}

	public void setDownloadingQuantity(long downloadingQuantity) {
		this.downloadingQuantity = downloadingQuantity;
	}

	public long getSharedQuantity() {
		return sharedQuantity;
	}

	public void setSharedQuantity(long sharedQuantity) {
		this.sharedQuantity = sharedQuantity;
	}

	public long getSharedQuantityFee() {
		return sharedQuantityFee;
	}

	public void setSharedQuantityFee(long sharedQuantityFee) {
		this.sharedQuantityFee = sharedQuantityFee;
	}

	public AdStandard getStandard() {
		return standard;
	}

	public void setStandard(AdStandard standard) {
		this.standard = standard;
	}

	public AdStatus getStatus() {
		return status;
	}

	public void setStatus(AdStatus status) {
		this.status = status;
	}

	public long getSharedTotalMoney() {
		return sharedTotalMoney;
	}

	public void setSharedTotalMoney(long sharedTotalMoney) {
		this.sharedTotalMoney = sharedTotalMoney;
	}

	public long getAdTotalMoney() {
		return adTotalMoney;
	}

	public void setAdTotalMoney(long adTotalMoney) {
		this.adTotalMoney = adTotalMoney;
	}

	public long getTimes() {
		return times;
	}

	public void setTimes(long times) {
		this.times = times;
	}
	
}
