package com.hong.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hong.bean.Res;
//cinema有电影的意思
@SuppressWarnings("serial")
@WebServlet("/ResGetter/*")
public class ResGetterController extends BaseController {

	Map<String,String> MIME = new HashMap<String,String>();
	{
		MIME.put("jpg", "image/jpeg");
		MIME.put("png", "image/png");
		MIME.put("gif", "image/gif");
		MIME.put("flv", "video/x-flv");
		MIME.put("mp4", "video/mp4");
	}
	public void get(Res res, HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("进入获取资源控制器的get方法");
		
		String realPath = res.getRealPath();
		
		try {
			System.out.println("realPath"+realPath);
			File file = new File(realPath);
			@SuppressWarnings("resource")
			FileInputStream in = new FileInputStream(file);
			
/*			resp.setHeader("Pragma", "no-cache");
			resp.setHeader("Cache-Control", "no-cache");
			resp.setDateHeader("Expires", 0);*/
			//resp.setContentLength((int) file.length());
			resp.setContentType(MIME.get(res.getExtName()));
			
			OutputStream out = resp.getOutputStream();
			byte[] buffer = new byte[8192];
			int count = 0;
			while(in.available()>0){
				count = in.read(buffer);
				out.write(buffer, 0, count);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
