package com.hong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hong.service.MovieService;
//cinema有电影的意思
public class PlayListAction {

	MovieService movService = new MovieService();
	public String view(HttpServletRequest req, HttpServletResponse resp) {
		//待定---判断改变路径或者其他逻辑
		return "success";
	}
	
	
}
