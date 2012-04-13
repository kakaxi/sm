package com.simplemad.web.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = -7275597943013086273L;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		if(session.getAttribute("user") == null) {
			if(httpRequest.getRequestURI().indexOf("administrator/login") > -1) {
				chain.doFilter(request, response);
				return;
			} else if(httpRequest.getRequestURI().indexOf("page/page.do?page=login") > -1) {
				chain.doFilter(request, response);
				return;
			} else {
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/page/page.do?page=login");
				return;
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}
