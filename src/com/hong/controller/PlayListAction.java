package com.hong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hong.service.MovieService;
//cinema�е�Ӱ����˼
public class PlayListAction {

	MovieService movService = new MovieService();
	public String view(HttpServletRequest req, HttpServletResponse resp) {
		//����---�жϸı�·�����������߼�
		return "success";
	}
	
	
}
