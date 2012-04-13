package com.simplemad.base.util;

import java.util.ArrayList;
import java.util.List;

import com.simplemad.base.domain.Area;
import com.simplemad.base.domain.Car;
import com.simplemad.base.domain.Child;
import com.simplemad.base.domain.UserProfile;
import com.simplemad.base.domain.enums.DietTaste;
import com.simplemad.base.domain.enums.Have;
import com.simplemad.base.domain.enums.Investment;
import com.simplemad.base.domain.enums.PhoneAppHobby;
import com.simplemad.base.domain.enums.SpareHobby;
import com.simplemad.bean.UserAttribute;

public final class UserProfileConverter {
	
	public static List<UserAttribute> convert(UserProfile userProfile) {
		List<UserAttribute> attrs = new ArrayList<UserAttribute>();
		long mobile = userProfile.getUser().getMobileNo();
		int order = 1;
		attrs.add(createUserAttribute(mobile, "姓名", userProfile.getUserName(), order++));
		attrs.add(createUserAttribute(mobile, "姓别", userProfile.getGender() == null ? "" : userProfile.getGender().getName(), order++));
		String birthday = DateUtil.translateToString(userProfile.getBirthday(), DateUtil.DEFAULT_PATTERN);
		attrs.add(createUserAttribute(mobile, "出生日期", birthday, order++));
		attrs.add(createUserAttribute(mobile, "常驻地区", getAreaAttr(userProfile), order++));
		attrs.add(createUserAttribute(mobile, "月薪", userProfile.getSalary() == null ? "" : userProfile.getSalary().getName(), order++));
		attrs.add(createUserAttribute(mobile, "职业", userProfile.getJob() == null ? "" : userProfile.getJob().getName(), order++));
		attrs.add(createUserAttribute(mobile, "婚烟", userProfile.getMarriage() == null ? "" : userProfile.getMarriage().getName(), order++));
		attrs.add(createUserAttribute(mobile, "体型", userProfile.getBody() == null ? "" : userProfile.getBody().getName(), order++));
		attrs.add(createUserAttribute(mobile, "学历", userProfile.getDegree() == null ? "" : userProfile.getDegree().getName(), order++));
		attrs.add(createUserAttribute(mobile, "家庭月收入", userProfile.getFamilySalary() == null ? "" : userProfile.getFamilySalary().getName(), order++));
		attrs.add(createUserAttribute(mobile, "车子", getCarStatus(userProfile.getCar()), order++));
		attrs.add(createUserAttribute(mobile, "小孩", getChildStatus(userProfile.getChild()), order++));
		attrs.add(createUserAttribute(mobile, "投资意向", getInvestments(userProfile.getInvestments()), order++));
		attrs.add(createUserAttribute(mobile, "业余爱好", getSpareHobbies(userProfile.getSpareHobbies()), order++));
		attrs.add(createUserAttribute(mobile, "手机应用喜好", getPhoneAppHobbies(userProfile.getPhoneAppHobbies()), order++));
		attrs.add(createUserAttribute(mobile, "饮食口味", getDietTastes(userProfile.getTastes()), order++));
		return attrs;
	}
	
	private static String getPhoneAppHobbies(List<PhoneAppHobby> phoneAppHobbies) {
		if(CollectionUtil.isEmpty(phoneAppHobbies)) {
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		for(int index = 0; index < phoneAppHobbies.size(); index++) {
			buffer.append(phoneAppHobbies.get(index).getName());
			if(index != phoneAppHobbies.size() - 1) {
				buffer.append("/");
			}
		}
		return buffer.toString();
	}
	
	private static String getDietTastes(List<DietTaste> tastes) {
		if(CollectionUtil.isEmpty(tastes)) {
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		for(int index = 0; index < tastes.size(); index++) {
			buffer.append(tastes.get(index).getName());
			if(index != tastes.size() - 1) {
				buffer.append("/");
			}
		}
		return buffer.toString();
	}

	private static String getSpareHobbies(List<SpareHobby> spareHobbies) {
		if(CollectionUtil.isEmpty(spareHobbies)) {
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		for(int index = 0; index < spareHobbies.size(); index++) {
			buffer.append(spareHobbies.get(index).getName());
			if(index != spareHobbies.size() - 1) {
				buffer.append("/");
			}
		}
		return buffer.toString();
	}

	private static String getInvestments(List<Investment> investments) {
		if(CollectionUtil.isEmpty(investments)) {
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		for(int index = 0; index < investments.size(); index++) {
			buffer.append(investments.get(index).getName());
			if(index != investments.size() - 1) {
				buffer.append("/");
			}
		}
		return buffer.toString();
	}

	private static String getChildStatus(Child child) {
		if(child == null || child.getHave() == null) {
			return null;
		}
		if(child.getHave().equals(Have.HAVE_NOT)) {
			return Have.HAVE_NOT.getName();
		} else {
			StringBuffer buffer = new StringBuffer();
			buffer.append(Have.HAVE.getName()).append(" ");
			if(child.getGender() != null) {
				buffer.append("性别:").append(child.getGender().getName()).append(" ");
			}
			if(child.getDegree() != null) {
				buffer.append("学历:").append(child.getDegree().getName()).append(" ");
			}
			if(child.getAge() != null) {
				buffer.append("年龄:").append(child.getAge().intValue()).append(" ");
			}
			return buffer.toString();
		}
	}

	private static String getCarStatus(Car car) {
		if(car == null || car.getHave() == null) {
			return "";
		}
		if(car.getHave().equals(Have.HAVE_NOT)) {
			return Have.HAVE_NOT.getName();
		} else {
			StringBuffer buffer = new StringBuffer();
			buffer.append(Have.HAVE.getName()).append(" ");
			if(car.getCar() != null) {
				buffer.append("车型:").append(car.getCar().getName()).append(" ");
			}
			if(car.getBuyDate() != null) {
				buffer.append("购买年份:").append(DateUtil.translateToString(car.getBuyDate(), DateUtil.YEAR_PATTERN));
			}
			return buffer.toString();
		}
	}

	private static String getAreaAttr(UserProfile profile) {
		StringBuffer buffer = new StringBuffer();
		Area area = profile.getArea();
		if(area == null) {
			return "";
		}
		
		buffer.append(area.getCity().getProvince().getName());
		buffer.append(area.getCity().getName());
		buffer.append(area.getName());
		return buffer.toString();
	}
	
	private static UserAttribute createUserAttribute(long mobile, String key, String value, int order) {
		UserAttribute userAttribute = new UserAttribute();
		userAttribute.setMobile(mobile);
		userAttribute.setKey(key);
		userAttribute.setValue(StringUtil.isEmpty(value) ? "" : value);
		userAttribute.setOrder(order);
		return userAttribute;
	}
}
