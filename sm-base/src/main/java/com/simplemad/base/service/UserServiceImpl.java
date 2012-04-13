package com.simplemad.base.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Criteria;
import com.google.code.morphia.query.Query;
import com.simplemad.base.dao.BaseDAO;
import com.simplemad.base.domain.User;
import com.simplemad.base.domain.UserProfile;
import com.simplemad.base.domain.UserProfileCondition;
import com.simplemad.base.domain.enums.Have;
import com.simplemad.base.util.CollectionUtil;
import com.simplemad.base.util.CriteriaUtil;
import com.simplemad.base.util.StringUtil;
import com.simplemad.base.util.UserProfileConditionUtil;
import com.simplemad.bean.NetAddress;
import com.simplemad.message.util.AddressUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BaseDAO dao;
	
	@Override
	public User register(long mobile, String password, String host, int port) {
		if(findUser(mobile) != null) {
			return null;
		}
		User user = new User();
		user.setMobileNo(mobile);
		user.setPassword(password);
		dao.save(user);
		dao.save(createProfile(user));
		return login(mobile, password, host, port);
	}
	
	private UserProfile createProfile(User user) {
		UserProfile profile = new UserProfile();
		profile.setUser(user);
		profile.setRegisterDate(new Date());
		return profile;
	}

	@Override
	public User login(long mobile, String password, String host, int port) {
		User user = findUser(mobile);
		System.out.println("UserService:" + (user == null));
		if(user == null) {
			return null;
		}
		System.out.println("UserService:" + user.getPassword().equals(password));
		if(user.getPassword().equals(password)) {
			if(!StringUtil.isEmpty(host)) {
				NetAddress address = new NetAddress();
				address.setHost(host);
				address.setPort(port);
				user.setIpAddress(AddressUtil.convert(address));
			}
			dao.save(user);
			return user;
		} else {
			return null;
		}
	}

	@Override
	public long addMoney(long mobile, long money) {
		UserProfile profile = findUserProfile(mobile);
		profile.setMoney(profile.getMoney() + money);
		dao.save(profile);
		return profile.getMoney();
	}
	
	@Override
	public long minusMoney(long mobile, long money) {
		UserProfile profile = findUserProfile(mobile);
		profile.setMoney(profile.getMoney() - money);
		dao.save(profile);
		return profile.getMoney();
	}

	@Override
	public User findUser(long mobile) {
		return dao.findOne(User.class, "mobileNo", mobile);
	}

	@Override
	public UserProfile findUserProfile(long mobile) {
		User user = findUser(mobile);
		if(user == null) {
			return null;
		}
		return dao.findOne(UserProfile.class, "user", user);
	}

	@Override
	public List<User> findAll() {
		return dao.findAll(User.class);
	}

	@Override
	public User logout(long mobile, String password) {
		User user = findUser(mobile);
		if(user == null) {
			return null;
		}
		if(user.getPassword().equals(password)) {
			user.setIpAddress(null);
			dao.save(user);
			return user;
		}
		return null;
	}

	@Override
	public boolean modifyPassword(long mobile, String oldPassword,
			String newPassword) {
		User user = findUser(mobile);
		if(user == null) {
			return false;
		}
		if(!user.getPassword().equals(oldPassword)) {
			return false;
		}
		user.setPassword(newPassword);
		dao.save(user);
		return true;
	}

	@Override
	public boolean updateUserProfileFirst(UserProfile profile) {
		UserProfile oldProfile = findUserProfile(profile.getUser().getMobileNo());
		oldProfile.setUserName(profile.getUserName());
		oldProfile.setBirthday(profile.getBirthday());
		oldProfile.setGender(profile.getGender());
		oldProfile.setAddress(profile.getAddress());
		oldProfile.setArea(profile.getArea());
		oldProfile.setJob(profile.getJob());
		oldProfile.setSalary(profile.getSalary());
		return updateUserProfile(oldProfile);
	}

	@Override
	public boolean updateUserProfileSecond(UserProfile profile) {
		UserProfile oldProfile = findUserProfile(profile.getUser().getMobileNo());
		if(Have.HAVE_NOT.equals(profile.getCar().getHave())) {
			profile.getCar().setBuyDate(null);
			profile.getCar().setCar(null);
		}
		if(Have.HAVE_NOT.equals(profile.getChild().getGender())) {
			profile.getChild().setAge(0);
			profile.getChild().setDegree(null);
			profile.getChild().setGender(null);
		}
		oldProfile.setBody(profile.getBody());
		oldProfile.setCar(profile.getCar());
		oldProfile.setChild(profile.getChild());
		oldProfile.setDegree(profile.getDegree());
		oldProfile.setMarriage(profile.getMarriage());
		oldProfile.setFamilySalary(profile.getFamilySalary());
		return updateUserProfile(oldProfile);
	}

	@Override
	public boolean updateUserProfileThird(UserProfile profile) {
		UserProfile oldProfile = findUserProfile(profile.getUser().getMobileNo());
		oldProfile.setInvestments(profile.getInvestments());
		oldProfile.setPhoneAppHobbies(profile.getPhoneAppHobbies());
		oldProfile.setSpareHobbies(profile.getSpareHobbies());
		oldProfile.setTastes(profile.getTastes());
		return updateUserProfile(oldProfile);
	}
	
	private boolean updateUserProfile(UserProfile profile) {
		if(profile == null || profile.getUser() == null) {
			return false;
		}
		dao.save(profile);
		return true;
	}

	@Override
	public List<User> find(UserProfileCondition condition) {
		if(condition == null || condition.isEmpty()) {
			return findAll();
		}
		return findInternal(condition);
	}
	
	private List<User> findInternal(UserProfileCondition condition) {
		Query<UserProfile> query = dao.createQuery(UserProfile.class);
		Criteria[] andCriterias = createCriterias(query, condition);
		if(andCriterias != null && andCriterias.length > 0) {
			query.and(andCriterias);
		}
		System.out.println("UserProfile condition query : ");
		System.out.println(query.toString());
		System.out.println(query.countAll());
		return getUserList(query.asList());
	}
	
	private Criteria[] createCriterias(Query<UserProfile> query, UserProfileCondition condition) {
		List<Criteria> criteriaList = new ArrayList<Criteria>();
		CriteriaUtil.addCriteria(criteriaList, UserProfileConditionUtil.ageCriteria(query, condition.getAges()));
		CriteriaUtil.addCriteria(criteriaList, UserProfileConditionUtil.areaCriteria(query, condition.getAreas()));
		CriteriaUtil.addCriteria(criteriaList, UserProfileConditionUtil.bodyCriteria(query, condition.getBodies()));
		CriteriaUtil.addCriteria(criteriaList, UserProfileConditionUtil.carsCriteria(query, condition.getCars()));
		CriteriaUtil.addCriteria(criteriaList, UserProfileConditionUtil.childrenCriteria(query, condition.getChilds()));
		CriteriaUtil.addCriteria(criteriaList, UserProfileConditionUtil.degreeCriteria(query, condition.getDegrees()));
		CriteriaUtil.addCriteria(criteriaList, UserProfileConditionUtil.dietTasteCriteria(query, condition.getTastes()));
		CriteriaUtil.addCriteria(criteriaList, UserProfileConditionUtil.familySalaryCriteria(query, condition.getFamilySalaries()));
		CriteriaUtil.addCriteria(criteriaList, UserProfileConditionUtil.genderCriteria(query, condition.getGenders()));
		CriteriaUtil.addCriteria(criteriaList, UserProfileConditionUtil.investmentCriteria(query, condition.getInvestments()));
		CriteriaUtil.addCriteria(criteriaList, UserProfileConditionUtil.jobCriteria(query, condition.getJobs()));
		CriteriaUtil.addCriteria(criteriaList, UserProfileConditionUtil.marriageCriteria(query, condition.getMarriages()));
		CriteriaUtil.addCriteria(criteriaList, UserProfileConditionUtil.phoneAppHobbyCriteria(query, condition.getAppHobbies()));
		CriteriaUtil.addCriteria(criteriaList, UserProfileConditionUtil.salaryCriteria(query, condition.getSalaries()));
		CriteriaUtil.addCriteria(criteriaList, UserProfileConditionUtil.spareHobbyCriteria(query, condition.getSpareHobbies()));
		
		return CriteriaUtil.toArray(criteriaList);
	}

	private List<User> getUserList(List<UserProfile> profileList) {
		List<User> userList = new ArrayList<User>();
		if(CollectionUtil.isEmpty(profileList)) {
			return userList;
		}
		for(UserProfile profile : profileList) {
			userList.add(profile.getUser());
		}
		return userList;
	}

	@Override
	public boolean exchangMoney(long mobile, long exchangeMoney) {
		UserProfile profile = findUserProfile(mobile);
		if(profile == null) {
			return false;
		}
		if(profile.getMoney() < exchangeMoney) {
			return false;
		}
		profile.setExchangeMoney(exchangeMoney);
		profile.setMoney(profile.getMoney() - exchangeMoney);
		dao.save(profile);
		return true;
	}
	
	@Override
	public boolean completeExchangMoney(long mobile, long exchangeMoney) {
		UserProfile profile = findUserProfile(mobile);
		if(profile == null) {
			return false;
		}
		profile.setExchangeMoney(profile.getExchangeMoney() - exchangeMoney);
		dao.save(profile);
		return true;
	}

}
