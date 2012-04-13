package com.simplemad.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.simplemad.ad.domain.AdStandard;
import com.simplemad.ad.domain.AdStandardMoney;
import com.simplemad.ad.service.AdStandardService;
import com.simplemad.web.common.controller.BaseController;

@Controller
public class AdStandardController extends BaseController {

	@Autowired
	private AdStandardService standardService;
	
	@RequestMapping
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("adstandard/adstandard_list");
		mv.addObject("adstandardList", standardService.findAll());
		return mv;
	}
	
	@RequestMapping
	public ModelAndView create_start() {
		ModelAndView mv = new ModelAndView("adstandard/create_adstandard");
		mv.addObject("standardList", AdStandard.values());
		return mv;
	}
	
	@RequestMapping
	public ModelAndView create(AdStandardMoney standardMoney) {
		ModelAndView mv = new ModelAndView("forward:findAll.do");
		standardService.create(standardMoney);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView update_start(String adstandardId) {
		ModelAndView mv = new ModelAndView("adstandard/update_adstandard");
		AdStandardMoney standardMoney = standardService.find(adstandardId);
		mv.addObject("standardList", AdStandard.values());
		mv.addObject("standardMoney", standardMoney);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView update(AdStandardMoney standardMoney) {
		ModelAndView mv = new ModelAndView("forward:findAll.do");
		standardService.update(standardMoney);
		return mv;
	}
}
