package com.simplemad.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.simplemad.ad.domain.ExchangeStatus;
import com.simplemad.ad.service.MoneyExchangeService;
import com.simplemad.ad.util.MoneyExchangeRecordConvert;
import com.simplemad.bean.MoneyExchangeRecord;
import com.simplemad.web.common.controller.BaseController;

@Controller
public class MoneyExchangeController extends BaseController {

	@Autowired
	private MoneyExchangeService exchangeService;
	
	@RequestMapping
	@ResponseBody
	public boolean exchange(long mobile, String password, long exchangeMoney) {
		exchangeService.apply(mobile, password, exchangeMoney);
		return false;
	}
	
	@RequestMapping
	public ModelAndView findRecordsList() {
		return new ModelAndView("exchange/exchange_list");
	}
	
	@RequestMapping
	public ModelAndView findAppliedRecords() {
		ModelAndView mv = new ModelAndView("exchange/applied_list");
		mv.addObject("recordList", exchangeService.findAll(ExchangeStatus.APPLIED));
		return mv;
	}
	
	@RequestMapping
	public ModelAndView findProcessedRecords() {
		ModelAndView mv = new ModelAndView("exchange/processed_list");
		mv.addObject("recordList", exchangeService.findAll(ExchangeStatus.APPLIED));
		return mv;
	}
	
	@RequestMapping
	public ModelAndView findCompletedRecords() {
		ModelAndView mv = new ModelAndView("exchange/completed_list");
		mv.addObject("recordList", exchangeService.findAll(ExchangeStatus.APPLIED));
		return mv;
	}
	
	@RequestMapping
	@ResponseBody
	public boolean process(String recordId) {
		return exchangeService.process(recordId);
	}
	
	@RequestMapping
	@ResponseBody
	public boolean complete(String recordId) {
		return exchangeService.complete(recordId);
	}
	
	@RequestMapping
	@ResponseBody
	public List<MoneyExchangeRecord> findAll(long mobile) {
		List<MoneyExchangeRecord> resultList = new ArrayList<MoneyExchangeRecord>();
		List<com.simplemad.ad.domain.MoneyExchangeRecord> recordList = exchangeService.findAll(mobile);
		for(com.simplemad.ad.domain.MoneyExchangeRecord record : recordList) {
			resultList.add(MoneyExchangeRecordConvert.convert(record));
		}
		return resultList;
	}
}
