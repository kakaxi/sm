package com.simplemad.web.common.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * @author Kamen <br />
 *         cn.pinlog.web统一通过该controll的page方法访问在web-inf下的页面,需要配置<br />
 * 		 {@link HtmlResourceViewResolver}，并且要求order的值比{@link UrlBasedViewResolver} <br />
 *       的要小，其他需要直接用url为页面路径的页面则放到webapp下 <br />
 *       访问的url例如:http://localhost:8080/page/page?page=default，其中default为WEB-INF下的文件路径并且default不带文件后缀名<br />
 *       通过{@link HtmlResourceViewResolver} 首先查找default.html，若找不到则通过{@link InternalResourceViewResolver} <br />
 *       查找default.jsp
 */
@Controller
public class PageController extends BaseController {
	
	@RequestMapping
	public ModelAndView page(String page) throws IOException {
		return new ModelAndView(page);
	}
}
