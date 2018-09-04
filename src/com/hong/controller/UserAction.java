package com.hong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hong.bean.User;
import com.hong.service.UserService;

public class UserAction {

	private User user;
	private UserService service = new UserService();
	//因为每个请求都有一个action对应
	//也就是说每次对应的request对象都不一样
	private HttpServletRequest req = ServletActionContext.getRequest();
	//只有生成getter和setter方法才有可能进行表单的自动填写
	public User getUser() {return user;}
	public void setUser(User user) {this.user = user;}
	
	public String login(){
		System.out.println("进入数据库前的user:"+user);
		//向数据库中查询，并且将数据库中的数据填充到user中
		boolean isLogin = service.login(user);
		System.out.println("进入数据库后的user:"+user);
		//如果登录成功就将user对象加入session域中以及跳转到首页
		if (isLogin) {
			HttpSession session = req.getSession(true);
			session.setAttribute("user", user);
			return "success";
		}
		return "fail";
	}
	public String loginout(){
		//默认为失败
		String result = "fail";
		//首先获取session
		//并且判断User是否为空
		//不为空则进行移除后
		//销毁session
		HttpSession session = req.getSession(false);
		User loginedUser = (User) session.getAttribute("user");
		if(loginedUser != null){
			//从session中移除user对象
			//退出成功的同时创建销毁session
			session.removeAttribute("user");
			session.invalidate();
			result = "success";
		}
		return result;
	}
	public String regist(){
		String result = "fail";
		if(service.regist(user)){
			//在注册成功的同时
			//1、将注册成功的用户跳转到首页
			//2、将用户放置到session域中
			req.getSession().setAttribute("user", user);
			result = "success";
		}
		return result;
	}
	public String infoManage(){
		//只负责跳转
		return "success";
	}
	public String saveUser(){
		//通过拦截器从页面获取数据之后设置到已经登录的用户
		//并且通过service层写回数据库
		User loginedUser = (User) req.getSession(false).getAttribute("user");
		loginedUser.setNickName(user.getNickName());
		loginedUser.setEmail(user.getEmail());
		loginedUser.setQq(user.getQq());
		service.saveUser(loginedUser);
		return "success";
	}
	public String editPass(){
		//因为这些name无法在拦截器中设置
		//因此这里自己通过getParameter进行获取
		//进而设置到已经登录的用户中
		String oldPass = req.getParameter("oldPass");
		String newPass = req.getParameter("newPass");
		String comPass = req.getParameter("comPass");
		User loginedUser = (User) req.getSession(false).getAttribute("user");
		if(loginedUser.getPass().equals(oldPass)){
			if(newPass.equals(comPass)){
				loginedUser.setPass(newPass);
				if(service.editPass(loginedUser)){
					System.out.println("更新密码成功");
				}
			}
		}
		return "success";
	}
}
