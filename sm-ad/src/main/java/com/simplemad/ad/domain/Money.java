package com.simplemad.ad.domain;

import java.io.Serializable;

public class Money implements Serializable {

	private static final long serialVersionUID = 4975706897310754389L;
	
	/**
	 * App供应商所得广告钱
	 */
	private long adMoney;
	
	/**
	 * App用户所得广告钱
	 */
	private long userAdMoney;

	/**
	 * App供应商分享微博所得钱
	 */
	private long sharingMoney;
	
	/**
	 * App用户分享微博所得钱
	 */
	private long userSharingMoney;
	
	public long getAdMoney() {
		return adMoney;
	}

	public void setAdMoney(long adMoney) {
		this.adMoney = adMoney;
	}

	public long getUserAdMoney() {
		return userAdMoney;
	}

	public void setUserAdMoney(long userAdMoney) {
		this.userAdMoney = userAdMoney;
	}

	public long getSharingMoney() {
		return sharingMoney;
	}

	public void setSharingMoney(long sharingMoney) {
		this.sharingMoney = sharingMoney;
	}
	
	public long getUserSharingMoney() {
		return userSharingMoney;
	}

	public void setUserSharingMoney(long userSharingMoney) {
		this.userSharingMoney = userSharingMoney;
	}
	
	/**
	 * @return 广告总单价
	 */
	public long getTotalMoney4Ad() {
		return getAdMoney() + getUserAdMoney();
	}
	
	/**
	 * @return 分享微博总单价
	 */
	public long getTotalMoney4Sharing() {
		return getSharingMoney() + getUserSharingMoney();
	}
	
	/**
	 * @return 用户所得总单价
	 */
	public long getUserTotalMoney() {
		return getUserAdMoney() + getUserSharingMoney();
	}
	
	/**
	 * @return 广告商所得总单价
	 */
	public long getAdTotalMoney() {
		return getAdMoney() + getSharingMoney();
	}
	
	/**
	 * @return 总单价
	 */
	public long getTotalMoney() {
		return getAdTotalMoney() + getUserTotalMoney();
	}

}
