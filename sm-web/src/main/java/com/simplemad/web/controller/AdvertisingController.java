package com.simplemad.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.simplemad.ad.domain.AdPublishCondition;
import com.simplemad.ad.domain.AdStatus;
import com.simplemad.ad.domain.Advertisement;
import com.simplemad.ad.domain.AdvertisementEffect;
import com.simplemad.ad.service.AdPublishConditionService;
import com.simplemad.ad.service.AdvertisementService;
import com.simplemad.base.domain.User;
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
import com.simplemad.base.service.UserService;
import com.simplemad.web.common.controller.BaseController;

/**
 * 广告投放controller
 * @author kamen
 *
 */
@Controller
public class AdvertisingController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AdvertisementService adService;
	
	@Autowired
	private AdPublishConditionService conditionService;
	
	@RequestMapping
	public ModelAndView initCondition(String adId) {
		ModelAndView mv = new ModelAndView("advertising/publish_condition");
		Advertisement ad = adService.find(adId);
		AdPublishCondition condition = conditionService.find(ad);
		if(condition != null) {
			List<User> users = userService.find(condition.getCondition());
			int totalUser = users.size();
			users = adService.findAvaiableUser(ad, users);
			int avaiableUser = users.size();
			mv.addObject("totalUser", totalUser);
			mv.addObject("avaiableUser", avaiableUser);
			mv.addObject("condition", condition);
		} 
		mv.addObject("ad", ad);
		mv.addObject("genders", Gender.values());
		mv.addObject("salaries", Salary.values());
		mv.addObject("jobs", Job.values());
		mv.addObject("marriages", Marriage.values());
		mv.addObject("bodies", BodyType.values());
		mv.addObject("degrees", Degree.values());
		mv.addObject("familySalaries", Salary.values());
		mv.addObject("investments", Investment.values());
		mv.addObject("spareHobbies", SpareHobby.values());
		mv.addObject("appHobbies", PhoneAppHobby.values());
		mv.addObject("tastes", DietTaste.values());
		mv.addObject("haves", Have.values());
		mv.addObject("carTypes", CarType.values());
		return mv;
	}
	
	@RequestMapping
	public ModelAndView createOrUpdateCondition(AdPublishCondition condition) {
		ModelAndView mv = new ModelAndView("advertising/view_condition_money_submit");
		AdPublishCondition savedCondition = null;
		if(condition.getId() != null) {
			savedCondition = conditionService.update(condition);
		} else {
			savedCondition = conditionService.create(condition);
		}
		if(savedCondition == null) {
			mv.setViewName("forward:initCondition.do?adId=" + condition.getAdvertisement().getId().toString());
			return mv;
		}
		Advertisement ad = adService.addup(savedCondition.getAdvertisement().getId().toString());
		List<User> users = userService.find(savedCondition.getCondition());
		int totalUser = users.size();
		users = adService.findAvaiableUser(ad, users);
		int avaiableUser = users.size();
		mv.addObject("ad", ad);
		mv.addObject("totalUser", totalUser);
		mv.addObject("avaiableUser", avaiableUser);
		mv.addObject("conditionViews", AdPublishCondition.view(savedCondition));
		return mv;
	}
	
	@RequestMapping
	public ModelAndView copyCondition(String adId, String conditionId) {
		ModelAndView mv = new ModelAndView("forward:initCondition.do?adId=" + adId);
		conditionService.copyCondition(adId, conditionId);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView viewBeforePublish(String adId) {
		ModelAndView mv = new ModelAndView("advertising/view_condition_money");
		Advertisement ad = adService.addup(adId);
		AdPublishCondition savedCondition = conditionService.find(ad);
		List<User> users = userService.find(savedCondition.getCondition());
		int totalUser = users.size();
		users = adService.findAvaiableUser(ad, users);
		int avaiableUser = users.size();
		mv.addObject("ad", ad);
		mv.addObject("totalUser", totalUser);
		mv.addObject("avaiableUser", avaiableUser);
		mv.addObject("conditionViews", AdPublishCondition.view(savedCondition));
		return mv;
	}
	
	/**
	 * submit to audit publish
	 * @param adId
	 * @param publishNum
	 * @return
	 */
	@RequestMapping
	public ModelAndView submit(String adId, int publishNum) {
		ModelAndView mv = new ModelAndView("redirect:/admanage/findAuditPassed.do");
		adService.submit(adId, publishNum);
		return mv;
	}
	
	@RequestMapping
	@ResponseBody
	public String publish(String adId) {
		Advertisement ad = adService.find(adId);
		if(ad == null) {
			return "广告不存在, 发布失败";
		}
		if(!ad.getStatus().equals(AdStatus.AD_PUBLISH_AUDIT_PASSED)) {
			return "广告发布审核不通过, 请通知管理员进行广告发布审核";
		}
		long publishNum = ad.getPublishQuantity();
		AdPublishCondition condition = conditionService.find(ad);
		List<User> userList = userService.find(condition.getCondition());
		userList = adService.findAvaiableUser(ad, userList);
		for(int index = 0; index < userList.size(); index++) {
			if(index >= publishNum && publishNum > 0) {
				break;
			}
			User user = userList.get(index);
			AdvertisementEffect effect = adService.publish(condition, user, true);
			if(effect != null) {
				adService.sendAd(effect, true);
			}
		}
		return "发布成功";
			
	}
	
	@RequestMapping
	@ResponseBody
	public boolean updateReceived(String adId, long mobile) {
		User user = userService.findUser(mobile);
		return adService.updateReceived(adId, user);
	}
	
	@RequestMapping
	@ResponseBody
	public boolean updateDownloading(String adId, long mobile) {
		User user = userService.findUser(mobile);
		return adService.updateDownloading(adId, user);
	}
	
	@RequestMapping
	@ResponseBody
	public boolean updateDownloaded(String adId, long mobile) {
		User user = userService.findUser(mobile);
		return adService.updateDownloaded(adId, user);
	}
	
	@RequestMapping
	@ResponseBody
	public boolean updateOpened(String adId, long mobile) {
		User user = userService.findUser(mobile);
		return adService.updateOpened(adId, user);
	}
	
	@RequestMapping
	@ResponseBody
	public boolean updateCompleted(String adId, long mobile) {
		User user = userService.findUser(mobile);
		return adService.updateCompleted(adId, user);
	}
	
	@RequestMapping
	@ResponseBody
	public boolean updateShared(String adId, long mobile) {
		User user = userService.findUser(mobile);
		return adService.updateShared(adId, user);
	}
	
	@RequestMapping
	@ResponseBody
	public boolean updateTimes(String adId, long mobile, int times) {
		User user = userService.findUser(mobile);
		return adService.updateTimes(adId, user, times);
	}
	
	@RequestMapping
	@ResponseBody
	public long earnAdMoney(String adId, long mobile) {
		User user = userService.findUser(mobile);
		Advertisement ad = adService.find(adId);
		if(user == null || ad == null) {
			return 0;
		}
		boolean isCompleted = adService.updateCompleted(adId, user);
		if(isCompleted) {
			AdvertisementEffect effect = adService.findEffect(ad, user);
			if(effect == null) {
				return 0;
			} else {
				userService.addMoney(mobile, effect.getMoney().getUserAdMoney());
				return effect.getMoney().getUserAdMoney();
			}
		} else {
			return 0;
		}
	}
	
	@RequestMapping
	@ResponseBody
	public long earnSharingMoney(String adId, long mobile) {
		User user = userService.findUser(mobile);
		Advertisement ad = adService.find(adId);
		if(user == null || ad == null) {
			return 0;
		}
		boolean isShared = adService.updateShared(adId, user);
		if(isShared) {
			AdvertisementEffect effect = adService.findEffect(ad, user);
			if(effect == null) {
				return 0;
			} else {
				userService.addMoney(mobile, effect.getMoney().getUserSharingMoney());
				return effect.getMoney().getUserSharingMoney();
			}
		} else {
			return 0;
		}
	}
}
