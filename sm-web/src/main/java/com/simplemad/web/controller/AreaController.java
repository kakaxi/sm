package com.simplemad.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.simplemad.base.domain.Area;
import com.simplemad.base.domain.City;
import com.simplemad.base.domain.Province;
import com.simplemad.base.service.AreaService;
import com.simplemad.base.util.StringUtil;
import com.simplemad.web.common.controller.BaseController;

@Controller
public class AreaController extends BaseController {

	@Autowired
	private AreaService areaService;
	
	@RequestMapping
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("area/province_list");
		mv.addObject("provinceList", areaService.findAllProvince());
		return mv;
	}
	
	@RequestMapping
	public ModelAndView findAllCities(String proId) {
		ModelAndView mv = new ModelAndView("area/city_list");
		Province province = areaService.findProvince(new ObjectId(proId));
		mv.addObject("province", province);
		mv.addObject("cityList", province.getCities());
		return mv;
	}
	
	@RequestMapping
	public ModelAndView findAllAreas(String cityId) {
		ModelAndView mv = new ModelAndView("area/area_list");
		City city = areaService.findCity(new ObjectId(cityId));
		mv.addObject("areaList", city.getAreas());
		mv.addObject("city", city);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView create_start_province() {
		ModelAndView mv = new ModelAndView("area/create_province");
		return mv;
	}
	
	@RequestMapping
	public ModelAndView create_province(String name) {
		ModelAndView mv = new ModelAndView();
		Province province = areaService.createProvince(name);
		if(province == null) {
			
		} else {
			mv.setViewName("forward:findAll.do");
		}
		return mv;
	}
	
	@RequestMapping
	public ModelAndView create_start_city(String proId) {
		ModelAndView mv = new ModelAndView("area/create_city");
		if(StringUtil.isEmpty(proId)) {
			return mv;
		}
		Province province = areaService.findProvince(new ObjectId(proId));
		mv.addObject("province", province);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView create_city(String proId, String name) {
		ModelAndView mv = new ModelAndView();
		if(StringUtil.isEmpty(proId) || StringUtil.isEmpty(name)) {
			return mv;
		}
		City city = areaService.createCity(new ObjectId(proId), name);
		if(city == null) {
			
		} else {
			mv.setViewName("forward:findAllCities.do?proId=" + proId);
		}
		return mv;
	}
	
	@RequestMapping
	public ModelAndView create_start_area(String cityId) {
		ModelAndView mv = new ModelAndView("area/create_area");
		if(StringUtil.isEmpty(cityId)) {
			return mv;
		}
		City city = areaService.findCity(new ObjectId(cityId));
		mv.addObject("city", city);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView create_area(String cityId, String name) {
		ModelAndView mv = new ModelAndView();
		if(StringUtil.isEmpty(cityId) || StringUtil.isEmpty(name)) {
			return mv;
		}
		Area area = areaService.createArea(new ObjectId(cityId), name);
		if(area == null) {
			
		} else {
			mv.setViewName("forward:findAllAreas.do?cityId=" + cityId);
		}
		return mv;
	}
	
	@RequestMapping
	@ResponseBody
	public List<Province> findProvinces() {
		List<Province> provinces = areaService.findAllProvince();
		for(Province p : provinces) {
			p.setCities(null);
		}
		return provinces;
	}
	
	@ResponseBody
	@RequestMapping
	public List<City> findCities(String proId) {
		Province province = areaService.findProvince(new ObjectId(proId));
		List<City> cities = new ArrayList<City>();
		for(City city : province.getCities()) {
			city.setProvince(null);
			city.setAreas(null);
			cities.add(city);
		}
		return cities;
	}
	
	@ResponseBody
	@RequestMapping
	public List<Area> findAreas(String cityId) {
		City city = areaService.findCity(new ObjectId(cityId));
		List<Area> areaList = new ArrayList<Area>();
		for(Area area : city.getAreas()) {
			area.setCity(null);
			areaList.add(area);
		}
		return areaList;
	}
	
	@ResponseBody
	public ModelAndView select_area_start() {
		ModelAndView mv = new ModelAndView("area/select_area");
		return mv;
	}
	
}
