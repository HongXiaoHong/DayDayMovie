package com.hong.controller.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CodeInterceptor implements Interceptor{

    @Override
    public void destroy() {}
    @Override
    public void init() {}

    @Override
    public String intercept(ActionInvocation AI) throws Exception {
    	HttpServletRequest req = ServletActionContext.getRequest();
    	HttpServletResponse resp = ServletActionContext.getResponse();
    	req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
            return AI.invoke();//已经登录继续执行
        
    }

}
