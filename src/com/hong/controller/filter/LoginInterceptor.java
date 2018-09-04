package com.hong.controller.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor{

    @Override
    public void destroy() {}
    @Override
    public void init() {}

    @Override
    public String intercept(ActionInvocation AI) throws Exception {
        System.out.println("In....interceptor");
        HttpServletRequest req = ServletActionContext.getRequest();
        String action = req.getRequestURI();
        Map<String,Object> session = AI.getInvocationContext().getSession();//获取session
        if(action.contains("login")
        		||action.contains("regist")
        		||action.contains("loginout")
        		||session.get("user")!=null)
        {
            return AI.invoke();//已经登录继续执行
        }else
        {
            return "login";//返回登录页面，当然这个只是个result
        }
    }

}
