package com.simplemad.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.simplemad.base.domain.Administrator;
import com.simplemad.base.service.AdministratorService;
import com.simplemad.web.common.controller.BaseController;

@Controller
public class AdministratorController extends BaseController {

	@Autowired
	private AdministratorService adminService;
	
	@RequestMapping
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("administrator/administrator_list");
		mv.addObject("administratorList", adminService.findAll());
		return mv;
	}
	
	@RequestMapping
	public ModelAndView create_start() {
		return new ModelAndView("administrator/create_administrator");
	}
	
	@RequestMapping
	public ModelAndView create(Administrator admin) {
		adminService.create(admin);
		return new ModelAndView("forward:findAll.do");
	}
	
	@RequestMapping
	public ModelAndView login(Administrator admin) {
		ModelAndView mv = new ModelAndView();
		boolean isSuccess = adminService.login(admin);
		if(isSuccess) {
			mv.setViewName("index");
			setObjectInSession(USER, adminService.find(admin.getMobile()));
		} else {
			mv.setViewName("login");
		}
		return mv;
	}
	
	@RequestMapping
	public ModelAndView logout(Administrator admin) {
		ModelAndView mv = new ModelAndView("login");
		resetSession();
		return mv;
	}
}
