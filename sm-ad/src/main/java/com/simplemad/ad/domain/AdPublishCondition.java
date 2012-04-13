package com.simplemad.ad.domain;

import java.util.ArrayList;
import java.util.List;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;
import com.simplemad.base.domain.BaseEntity;
import com.simplemad.base.domain.UserProfileCondition;

@Entity
public class AdPublishCondition extends BaseEntity {

	private static final long serialVersionUID = -8840455725943642985L;
	
	@Reference(lazy=true)
	private Advertisement advertisement;
	
	/**
	 * 根据condition计算出一个用户可以获取该广告的金额,即单价,不包括分享微博
	 */
	private Money money;
	
	private boolean isSharable;
	
	private UserProfileCondition condition;

	public Advertisement getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

	public Money getMoney() {
		if(money == null) {
			money = new Money();
		}
		return money;
	}

	public void setMoney(Money money) {
		this.money = money;
	}

	public boolean isSharable() {
		return isSharable;
	}

	public void setSharable(boolean isSharable) {
		this.isSharable = isSharable;
	}

	public UserProfileCondition getCondition() {
		if(condition == null) {
			condition = new UserProfileCondition();
		}
		return condition;
	}

	public void setCondition(UserProfileCondition condition) {
		this.condition = condition;
	}
	
	public static String[] view(AdPublishCondition condition) {
		List<String> result = new ArrayList<String>();
		String[] upConditionView = UserProfileCondition.view(condition.getCondition());
		if(upConditionView != null) {
			for(int index = 0; index < upConditionView.length; index++) {
				result.add(upConditionView[index]);
			}
		}
		result.add("是否分享微博 : " + (condition.isSharable() ? "是" : "否"));
		if(condition.isSharable()) {
			result.add("分享微博单价 : " + condition.getMoney().getTotalMoney4Sharing() + "分");
		}
		result.add("广告总单价 : " + condition.getMoney().getTotalMoney4Ad() + "分");
		return convert(result);
	}

	private static String[] convert(List<String> result) {
		String[] resultArray = new String[result.size()];
		for(int index = 0; index < result.size(); index++) {
			resultArray[index] = result.get(index);
		}
		return resultArray;
	}
}
