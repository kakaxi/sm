package com.simplemad.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.simplemad.ad.domain.Advertiser;
import com.simplemad.ad.service.AdvertiserService;
import com.simplemad.ad.service.IndustryService;
import com.simplemad.base.service.AreaService;
import com.simplemad.web.common.controller.BaseController;

@Controller
public class AdvertiserController extends BaseController{

	@Autowired
	private AdvertiserService advertiserService;
	
	@Autowired
	private IndustryService industryService;
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping
	public ModelAndView create(Advertiser advertiser) {
		ModelAndView mv = new ModelAndView();
		Advertiser newAdvertiser = advertiserService.register(advertiser);
		if(newAdvertiser == null) {
			
		} else {
			mv.setViewName("forward:findAll.do");
		}
		return mv;
	}
	
	@RequestMapping
	public ModelAndView create_start() {
		ModelAndView mv = new ModelAndView("advertiser/create_advertiser");
		mv.addObject("provinceList", areaService.findAllProvince());
		mv.addObject("industryList", industryService.findAll());
		return mv;
	}
	
	@RequestMapping
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("advertiser/advertiser_list");
		List<Advertiser> advertiserList = advertiserService.findAll();
		mv.addObject("advertiserList", advertiserList);
		return mv;
	}
}
