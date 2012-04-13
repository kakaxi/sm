package com.simplemad.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.simplemad.ad.domain.AdPublishCondition;
import com.simplemad.ad.domain.AdStandard;
import com.simplemad.ad.domain.AdStatus;
import com.simplemad.ad.domain.Advertisement;
import com.simplemad.ad.domain.AdvertisementEffect;
import com.simplemad.ad.domain.Money;
import com.simplemad.ad.service.AdvertisementService;
import com.simplemad.ad.service.AdvertiserService;
import com.simplemad.base.domain.Administrator;
import com.simplemad.base.domain.User;
import com.simplemad.base.service.AdministratorService;
import com.simplemad.base.util.FileUtil;
import com.simplemad.bean.AdvertisementType;
import com.simplemad.web.common.controller.BaseController;
import com.simplemad.web.util.MultipartFileHelper;

/**
 * 广告创建,修改,删除controller
 * @author kamen
 *
 */
@Controller
public class AdManageController extends BaseController {

	@Autowired
	private AdvertisementService adService;
	
	@Autowired
	private AdvertiserService advertiserService;
	
	@Autowired
	private AdministratorService adminService;
	
	@RequestMapping
	public ModelAndView create_start() {
		ModelAndView mv = new ModelAndView("advertising/create_advertisement");
		mv.addObject("adTypes", filterAdType());
		mv.addObject("advertiserList", advertiserService.findAll());
		return mv;
	}
	
	private AdvertisementType[] filterAdType() {
		return AdvertisementType.values();
	}
	
	@RequestMapping
	public ModelAndView create(Advertisement ad, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("forward:findNew.do");
		Map<String, DiskFileItem> fileMap = MultipartFileHelper.getDiskFileItemsMap(request);
		DiskFileItem icon = fileMap.get("icon");
		DiskFileItem content = fileMap.get("content");
		ad.setIconExtendedName(FileUtil.getFileExtendedName(icon.getName()));
		ad.setContentExtendedName(FileUtil.getFileExtendedName(content.getName()));
		adService.create(ad, icon.getStoreLocation(), content.getStoreLocation());
		publishForAdmin(ad);
		return mv;
	}
	
	private void publishForAdmin(Advertisement ad) {
		List<User> userList = new ArrayList<User>();
		List<Administrator> adminList = adminService.findAll();
		for(Administrator admin : adminList) {
			userList.add(admin.getUser());
		}
		AdPublishCondition condition = new AdPublishCondition();
		condition.setAdvertisement(ad);
		condition.setMoney(new Money());
		for(int index = 0; index < userList.size(); index++) {
			User user = userList.get(index);
			AdvertisementEffect effect = adService.publish(condition, user, false);
			if(effect != null) {
				adService.sendAd(effect, false);
			}
		}
	}
	
	@RequestMapping
	public ModelAndView audit_start(String adId) {
		ModelAndView mv = new ModelAndView("advertising/audit_advertisement");
		Advertisement ad = adService.find(adId);
		List<AdStandard> standardList = AdStandard.find(ad.getAdType());
		mv.addObject("standardList", standardList);
		mv.addObject("auditList", new AdStatus[]{AdStatus.AD_AUDIT_PASSED, AdStatus.AD_AUDIT_NON_PASSED});
		mv.addObject("ad", ad);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView audit(String adId, AdStandard as, AdStatus status) {
		ModelAndView mv = new ModelAndView("forward:findUpdate.do");
		if(AdStatus.AD_AUDIT_PASSED.equals(status)) {
			adService.auditPass(adId, as);
		} else if(AdStatus.AD_AUDIT_NON_PASSED.equals(status)) {
			adService.auditNotPass(adId);
		}
		return mv;
	}
	
	@RequestMapping
	public ModelAndView audit_publish_start(String adId) {
		ModelAndView mv = new ModelAndView("advertising/audit_publish_advertisement");
		Advertisement ad = adService.find(adId);
		mv.addObject("auditList", new AdStatus[]{AdStatus.AD_PUBLISH_AUDIT_PASSED, AdStatus.AD_PUBLISH_AUDIT_NON_PASSED});
		mv.addObject("ad", ad);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView audit_publish(String adId, AdStatus status) {
		ModelAndView mv = new ModelAndView("forward:findSubmit.do");
		if(AdStatus.AD_PUBLISH_AUDIT_PASSED.equals(status)) {
			adService.auditPublishPass(adId);
		} else if(AdStatus.AD_PUBLISH_AUDIT_NON_PASSED.equals(status)) {
			adService.auditPublishNonPass(adId);
		}
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listAll() {
		ModelAndView mv = new ModelAndView("advertising/advertisement_list");
		List<Advertisement> advertisementList = adService.findAll();
		mv.addObject("adList", advertisementList);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("advertising/advertisement_list_all");
		List<Advertisement> advertisementList = adService.findAll();
		mv.addObject("adList", advertisementList);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView findNew() {
		ModelAndView mv = new ModelAndView("advertising/advertisement_list_create");
		List<Advertisement> advertisementList = adService.findAll(AdStatus.NEW);
		mv.addObject("adList", advertisementList);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView findUpdate() {
		ModelAndView mv = new ModelAndView("advertising/advertisement_list_update");
		List<Advertisement> advertisementList = new ArrayList<Advertisement>();
		advertisementList.addAll(adService.findAll(AdStatus.NEW));
		advertisementList.addAll(adService.findAll(AdStatus.UPDATE));
		advertisementList.addAll(adService.findAll(AdStatus.AD_AUDIT_NON_PASSED));
		advertisementList.addAll(adService.findAll(AdStatus.AD_PUBLISH_AUDIT_NON_PASSED));
		advertisementList.addAll(adService.findAll(AdStatus.AD_PUBLISH_AUDIT_PASSED));
		mv.addObject("adList", advertisementList);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView findAuditPassed() {
		ModelAndView mv = new ModelAndView("advertising/advertisement_list_condition");
		List<Advertisement> advertisementList = new ArrayList<Advertisement>();
		advertisementList.addAll(adService.findAll(AdStatus.AD_AUDIT_PASSED));
		advertisementList.addAll(adService.findAll(AdStatus.AD_PUBLISH_AUDIT_NON_PASSED));
		mv.addObject("adList", advertisementList);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView findSubmit() {
		ModelAndView mv = new ModelAndView("advertising/advertisement_list_submit");
		List<Advertisement> advertisementList = adService.findAll(AdStatus.AD_SUBMIT);
		mv.addObject("adList", advertisementList);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView findPublish() {
		ModelAndView mv = new ModelAndView("advertising/advertisement_list_publish");
		List<Advertisement> advertisementList = adService.findAllNonNewTaskAuditPublishPassed();
		mv.addObject("adList", advertisementList);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView viewPublished() {
		ModelAndView mv = new ModelAndView("advertising/advertisement_list_published");
		List<Advertisement> advertisementList = new ArrayList<Advertisement>();
		advertisementList.addAll(adService.findAll(AdStatus.AD_PUBLISH_AUDIT_PASSED));
		advertisementList.addAll(adService.findAll(AdStatus.AD_AUDIT_PASSED));
		advertisementList.addAll(adService.findAll(AdStatus.UPDATE));
		advertisementList.addAll(adService.findAll(AdStatus.AD_SUBMIT));
		mv.addObject("adList", advertisementList);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView viewPublishedAd(String adId) {
		ModelAndView mv = new ModelAndView("advertising/advertisement_published");
		Advertisement advertisement = adService.addup(adId);
		mv.addObject("ad", advertisement);
		return mv;
	}
	
	
}
