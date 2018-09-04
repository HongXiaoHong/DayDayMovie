package com.hong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hong.bean.User;
import com.hong.service.UserService;

public class UserAction {

	private User user;
	private UserService service = new UserService();
	//��Ϊÿ��������һ��action��Ӧ
	//Ҳ����˵ÿ�ζ�Ӧ��request���󶼲�һ��
	private HttpServletRequest req = ServletActionContext.getRequest();
	//ֻ������getter��setter�������п��ܽ��б����Զ���д
	public User getUser() {return user;}
	public void setUser(User user) {this.user = user;}
	
	public String login(){
		System.out.println("�������ݿ�ǰ��user:"+user);
		//�����ݿ��в�ѯ�����ҽ����ݿ��е�������䵽user��
		boolean isLogin = service.login(user);
		System.out.println("�������ݿ���user:"+user);
		//�����¼�ɹ��ͽ�user�������session�����Լ���ת����ҳ
		if (isLogin) {
			HttpSession session = req.getSession(true);
			session.setAttribute("user", user);
			return "success";
		}
		return "fail";
	}
	public String loginout(){
		//Ĭ��Ϊʧ��
		String result = "fail";
		//���Ȼ�ȡsession
		//�����ж�User�Ƿ�Ϊ��
		//��Ϊ��������Ƴ���
		//����session
		HttpSession session = req.getSession(false);
		User loginedUser = (User) session.getAttribute("user");
		if(loginedUser != null){
			//��session���Ƴ�user����
			//�˳��ɹ���ͬʱ��������session
			session.removeAttribute("user");
			session.invalidate();
			result = "success";
		}
		return result;
	}
	public String regist(){
		String result = "fail";
		if(service.regist(user)){
			//��ע��ɹ���ͬʱ
			//1����ע��ɹ����û���ת����ҳ
			//2�����û����õ�session����
			req.getSession().setAttribute("user", user);
			result = "success";
		}
		return result;
	}
	public String infoManage(){
		//ֻ������ת
		return "success";
	}
	public String saveUser(){
		//ͨ����������ҳ���ȡ����֮�����õ��Ѿ���¼���û�
		//����ͨ��service��д�����ݿ�
		User loginedUser = (User) req.getSession(false).getAttribute("user");
		loginedUser.setNickName(user.getNickName());
		loginedUser.setEmail(user.getEmail());
		loginedUser.setQq(user.getQq());
		service.saveUser(loginedUser);
		return "success";
	}
	public String editPass(){
		//��Ϊ��Щname�޷���������������
		//��������Լ�ͨ��getParameter���л�ȡ
		//�������õ��Ѿ���¼���û���
		String oldPass = req.getParameter("oldPass");
		String newPass = req.getParameter("newPass");
		String comPass = req.getParameter("comPass");
		User loginedUser = (User) req.getSession(false).getAttribute("user");
		if(loginedUser.getPass().equals(oldPass)){
			if(newPass.equals(comPass)){
				loginedUser.setPass(newPass);
				if(service.editPass(loginedUser)){
					System.out.println("��������ɹ�");
				}
			}
		}
		return "success";
	}
}
