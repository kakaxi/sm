package com.simplemad.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.simplemad.ad.domain.AdPublishCondition;
import com.simplemad.ad.domain.Advertisement;
import com.simplemad.ad.domain.AdvertisementEffect;
import com.simplemad.ad.service.AdPublishConditionService;
import com.simplemad.ad.service.AdvertisementService;
import com.simplemad.base.domain.User;
import com.simplemad.base.domain.UserProfile;
import com.simplemad.base.domain.enums.BodyType;
import com.simplemad.base.domain.enums.CarType;
import com.simplemad.base.domain.enums.Degree;
import com.simplemad.base.domain.enums.DietTaste;
import com.simplemad.base.domain.enums.Gender;
import com.simplemad.base.domain.enums.Have;
import com.simplemad.base.domain.enums.Investment;
import com.simplemad.base.domain.enums.Job;
import com.simplemad.base.domain.enums.Marriage;
import com.simplemad.base.domain.enums.PhoneAppHobby;
import com.simplemad.base.domain.enums.Salary;
import com.simplemad.base.domain.enums.SpareHobby;
import com.simplemad.base.service.AreaService;
import com.simplemad.base.service.UserService;
import com.simplemad.base.util.CollectionUtil;
import com.simplemad.base.util.DateUtil;
import com.simplemad.base.util.UserProfileConverter;
import com.simplemad.web.common.controller.BaseController;

@Controller
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private AdvertisementService adService;
	
	@Autowired
	private AdPublishConditionService publishService;
	
	/**
	 * for http request
	 * @param mobile
	 * @param password
	 * @return
	 */
	@RequestMapping
	@ResponseBody
	public boolean register(long mobile, String password) {
		System.out.println("Start register User[mobile:" + mobile + "password:" + password + "]");
		String host = getHttpServletRequest().getRemoteHost();
		int port = getHttpServletRequest().getRemotePort();
		User user = userService.register(mobile, password, host, port);
		if(user == null) {
			return false;
		} else {
			//创建新手任务的AdvertisementEffect,在注册成功后立即登录时自动获取并发送
			List<Advertisement> adList = adService.findAllNewTaskAuditPublishPassed();
			for(Advertisement ad : adList) {
				AdPublishCondition condition = publishService.find(ad);
				adService.publish(condition, user, false);
			}
			return true;
		}
	}
	
	/**
	 * for udp
	 * @param mobile
	 * @param password
	 * @return
	 */
	@RequestMapping
	@ResponseBody
	public boolean register4UDP(long mobile, String password, String host, int port) {
		System.out.println("Start register User[mobile:" + mobile + "password:" + password + "]");
		User user = userService.register(mobile, password, host, port);
		if(user == null) {
			return false;
		} else {
			//创建新手任务的AdvertisementEffect,在注册成功后立即登录时自动获取并发送
			List<Advertisement> adList = adService.findAllNewTaskAuditPublishPassed();
			for(Advertisement ad : adList) {
				AdPublishCondition condition = publishService.find(ad);
				adService.publish(condition, user, false);
			}
			return true;
		}
	}
	
	@RequestMapping
	@ResponseBody
	public boolean login(long mobile, String password) {
		String host = getHttpServletRequest().getRemoteHost();
		int port = getHttpServletRequest().getRemotePort();
		User user = userService.login(mobile, password, host, port);
		if(user == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@RequestMapping
	@ResponseBody
	public boolean login4UDP(long mobile, String password, String host, int port) {
		User user = userService.login(mobile, password, host, port);
		if(user == null) {
			return false;
		} else {
			sendAd(user);
			return true;
		}
	}
	
	private void sendAd(final User user) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				List<AdvertisementEffect> allEffects = adService.findAvaiableEffect(user);
				if(!CollectionUtil.isEmpty(allEffects)) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for(AdvertisementEffect effect : allEffects) {
					if(!effect.isOutOfDate()) {
						adService.sendAd(effect, false);
					}
				}
			}
		}).start();
	}
	
	@RequestMapping
	@ResponseBody
	public boolean logout(long mobile, String password) {
		User user = userService.logout(mobile, password);
		if(user == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@RequestMapping
	@ResponseBody
	public com.simplemad.bean.User findUser(long mobile) {
		com.simplemad.bean.User user = new com.simplemad.bean.User();
		user.setMobile(mobile);
		UserProfile profile =  userService.findUserProfile(mobile);
		if(profile != null) {
			user.setAttrs(UserProfileConverter.convert(profile));
			user.setMoney(profile.getMoney());
			user.setUserName(profile.getUserName());
			user.setRegisterDate(profile.getRegisterDate());
		}
		
		return user;
	}
	
	@RequestMapping
	@ResponseBody
	public boolean modifyPassword(long mobile, String oldPassword, String newPassword) {
		return userService.modifyPassword(mobile, oldPassword, newPassword);
	}
	
	@RequestMapping
	public ModelAndView modify_user_start_first(long mobile) {
		ModelAndView mv = new ModelAndView("user/user_profile_first");
		UserProfile profile = userService.findUserProfile(mobile);
		mv.addObject("profile", profile);
		mv.addObject("provinceList", areaService.findAllProvince());
		mv.addObject("salaryList", Salary.values());
		mv.addObject("jobList", Job.values());
		mv.addObject("yearList", createYearList());
		if(profile != null) {
			mv.addObject("birthday", DateUtil.translateToString(profile.getBirthday(), DateUtil.YEAR_PATTERN));
			if(profile.getArea() != null) {
				mv.addObject("areaList", profile.getArea().getCity().getAreas());
				mv.addObject("cityList", profile.getArea().getCity().getProvince().getCities());
			}
		}
		mv.addObject("genderList", Gender.values());
		return mv;
	}
	
	@RequestMapping
	@InitBinder
	public ModelAndView modify_user_first(UserProfile profile) {
		ModelAndView mv = new ModelAndView("forward:modify_user_start_second.do?mobile=" + profile.getUser().getMobileNo());
		userService.updateUserProfileFirst(profile);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView modify_user_start_second(long mobile) {
		ModelAndView mv = new ModelAndView("user/user_profile_second");
		UserProfile profile = userService.findUserProfile(mobile);
		mv.addObject("profile", profile);
		if(profile != null && profile.getCar() != null 
				&& Have.HAVE.equals(profile.getCar().getHave()) && profile.getCar().getBuyDate() != null) {
			mv.addObject("buyDate", DateUtil.translateToStringDefault(profile.getCar().getBuyDate()).substring(0, 4));
		}
		mv.addObject("marriageList", Marriage.values());
		mv.addObject("haveList", Have.values());
		mv.addObject("degreeList", Degree.values());
		mv.addObject("bodyList", BodyType.values());
		mv.addObject("familySalaryList", Salary.values());
		mv.addObject("genderList", Gender.values());
		mv.addObject("carList", CarType.values());
		mv.addObject("yearList", createYearList());
		return mv;
	}
	
	private List<String> createYearList() {
		List<String> yearList = new ArrayList<String>();
		for(int index = 0; index < 100; index ++) {
			yearList.add(String.valueOf(2012 - index));
		}
		return yearList;
	}
	
	@RequestMapping
	@InitBinder
	public ModelAndView modify_user_second(UserProfile profile) {
		ModelAndView mv = new ModelAndView("forward:modify_user_start_third.do?mobile=" + profile.getUser().getMobileNo());
		userService.updateUserProfileSecond(profile);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView modify_user_start_third(long mobile) {
		ModelAndView mv = new ModelAndView("user/user_profile_third");
		UserProfile profile = userService.findUserProfile(mobile);
		mv.addObject("profile", profile);
		mv.addObject("investmentList", Investment.values());
		mv.addObject("spareHobbyList", SpareHobby.values());
		mv.addObject("phoneAppHobbyList", PhoneAppHobby.values());
		mv.addObject("tasteList", DietTaste.values());
		return mv;
	}
	
	@RequestMapping
	@InitBinder
	public ModelAndView modify_user_third(UserProfile profile) {
		ModelAndView mv = new ModelAndView("redirect:/page/page.do?page=common/complete");
		userService.updateUserProfileThird(profile);
		return mv;
	}
	
}
