package com.hong.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		System.out.println("进入过滤器");
		String uri = req.getRequestURI();
		System.out.println("uri:"+uri);
		if(uri.contains("login") || uri.contains("index")||uri.contains("regist")
				|| uri.equals("/Movie/") || uri.equals("/Movie")
				|| uri.contains("css")|| uri.contains("images") ||
				uri.contains("js"))
		{
			System.out.println("直接放行");
			chain.doFilter(req, resp);
		}
		else {
			HttpSession session = req.getSession(false);
			if(session != null){
				Object obj = session.getAttribute("user");
				if(obj != null ){
					chain.doFilter(req, resp);
				} else{
					resp.sendRedirect("/Movie/");
				}
			} else{
				resp.sendRedirect("/Movie/");
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
