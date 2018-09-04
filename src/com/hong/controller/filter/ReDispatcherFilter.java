package com.hong.controller.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ReDispatcherFilter implements Filter {

	public void init(FilterConfig config) throws ServletException {
		 
		   this.includes.addAll( Arrays.asList(config.getInitParameter("includeServlets").split(",")));

	}
	public void destroy() {}

	public void doFilter(ServletRequest req, ServletResponse resp,
	    FilterChain chain) throws IOException, ServletException{
	 
	   HttpServletRequest request = (HttpServletRequest)req;
	   String target = request.getRequestURI();
	   target = target.lastIndexOf("?")>0?target.substring(target.lastIndexOf("/")+1,target.lastIndexOf("?")-target.lastIndexOf("/")):target.substring(target.lastIndexOf("/")+1);
	 
	   System.out.println(target);
	   if(this.includes.contains(target)){
	    RequestDispatcher rdsp = request.getRequestDispatcher(target);
	  
	    System.out.println("go..............."+rdsp);
	    rdsp.forward(req, resp);
	   }
	   else
	    chain.doFilter(req, resp);
	}
	private ArrayList<String> includes = new ArrayList<String>();



}
