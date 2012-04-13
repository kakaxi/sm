package com.simplemad.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.simplemad.base.Timer;
import com.simplemad.base.service.TimerService;
import com.simplemad.base.util.TimerConverter;
import com.simplemad.web.common.controller.BaseController;

@Controller
public class TimerController extends BaseController {

	private static final int MINUTES = 60 * 1000;
	@Autowired
	private TimerService service;
	
	@RequestMapping
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView();
		List<Timer> timerList = new ArrayList<Timer>();
		Timer timer = service.find();
		if(timer != null) {
			changUnit(timer);
			timerList.add(timer);
		}
		mv.addObject("timerList", timerList);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView create_start() {
		ModelAndView mv = new ModelAndView();
		Timer timer = service.find();
		if(timer != null) {
			changUnit(timer);
			mv.addObject("timer", timer);
		}
		return mv;
	}
	
	@RequestMapping
	public ModelAndView create(int start, int end, int interval) {
		ModelAndView mv = new ModelAndView("forward:findAll.do");
		if(start == 0 || end == 0 || interval == 0) {
			return mv;
		}
		service.create(start * MINUTES, end * MINUTES, interval * MINUTES);
		return mv;
	}
	
	@RequestMapping
	@ResponseBody
	public com.simplemad.bean.Timer find() {
		return TimerConverter.convert(service.find());
	}
	
	private void changUnit(Timer timer) {
		if(timer != null) {
			timer.setStart(timer.getStart() / MINUTES);
			timer.setEnd(timer.getEnd() / MINUTES);
			timer.setInterval(timer.getInterval() / MINUTES);
		}
	}
}
