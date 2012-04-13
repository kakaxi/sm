package com.simplemad.base.service;

import java.util.List;

import com.simplemad.base.domain.User;
import com.simplemad.base.domain.UserProfile;
import com.simplemad.base.domain.UserProfileCondition;

public interface UserService {

	public User register(long mobile, String password, String host, int port);
	
	public User login(long mobile, String password, String host, int port);
	
	public User logout(long mobile, String password);
	
	public boolean updateUserProfileFirst(UserProfile profile);
	
	public boolean updateUserProfileSecond(UserProfile profile);
	
	public boolean updateUserProfileThird(UserProfile profile);
	
	public long addMoney(long mobile, long money);
	
	public long minusMoney(long mobile, long money);
	
	public boolean exchangMoney(long mobile, long exchangeMoney);
	
	public boolean completeExchangMoney(long mobile, long exchangeMoney);
	
	public User findUser(long mobile);
	
	public List<User> findAll();
	
	public UserProfile findUserProfile(long mobile);

	public boolean modifyPassword(long mobile, String oldPassword,
			String newPassword);
	
	public List<User> find(UserProfileCondition condition);
}
