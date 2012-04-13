package com.simplemad.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.simplemad.ad.domain.Industry;
import com.simplemad.ad.service.IndustryService;
import com.simplemad.web.common.controller.BaseController;

@Controller
public class IndustryController extends BaseController {

	@Autowired
	private IndustryService industryService;
	
	@RequestMapping
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("industry/industry_list");
		List<Industry> industryList = industryService.findAll();
		mv.addObject("industryList", industryList);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView create_start() {
		ModelAndView mv = new ModelAndView("industry/create_industry");
		return mv;
	}
	
	@RequestMapping
	public ModelAndView create(String name) {
		ModelAndView mv = new ModelAndView();
		Industry industry = industryService.create(name);
		if(industry == null) {
			
		} else {
			mv.setViewName("forward:findAll.do");
		}
		return mv;
	}
}
