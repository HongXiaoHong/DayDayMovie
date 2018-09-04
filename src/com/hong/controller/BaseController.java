package com.hong.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hong.bean.User;
import com.hong.utils.BeanUtil;

public abstract class BaseController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String uri = req.getRequestURI();
		String action = getAction(uri);
		System.out.println("通过getAction得到的action："+action);
		try {
			System.out.println("action:"+action);
			String line = callMethod(action, req, resp, 0);
			System.out.println("line:"+line);
			if(line!=null)
			parseResult(line, req, resp);
		} catch (InstantiationException | IllegalAccessException 
				| IllegalArgumentException | InvocationTargetException e) {
			resp.sendError(404);
			e.printStackTrace();
		}
	}

	private void parseResult(String line, HttpServletRequest req, 
			HttpServletResponse resp) throws IOException{
		String reg = "([a-z]+):(.+)";
		Pattern pat = Pattern.compile(reg);
		System.out.println("pat:"+pat);
		Matcher matcher = pat.matcher(line);
		if(matcher.matches()){
			String op = matcher.group(1);
			String content = matcher.group(2);
			try {
				doResponse(op, content, req, resp);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | InstantiationException e) {
				resp.sendError(404);
				e.printStackTrace();
			}
		} else{
			resp.sendError(404);
		}
	}

	private void doResponse(String op, String content, 
				HttpServletRequest req, HttpServletResponse resp ) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		Method method = this.getClass().getSuperclass().getDeclaredMethod(op, 
										String.class, HttpServletRequest.class, HttpServletResponse.class);
		method.setAccessible(true);
		method.invoke(this, content, req, resp);
	}

	private void redirect(String content, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String ctxPath = req.getContextPath();
		resp.sendRedirect(ctxPath+content);
	}
	private void data(String content, HttpServletRequest req, HttpServletResponse resp) throws IOException{
/*		resp.getWriter().write(content);
		resp.getWriter().flush();*/
	}
	private void forward(String content, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher(content).forward(req, resp);
	}
	
	protected int getPage(HttpServletRequest req){ return 0; }
	/*
	 * callMethod调用子类的方法之后
	 * 返回一些
	 * 解析后在选择方法进行执行
	 * 命令如下：
	 * redirect:url
	 * forward:url
	 * data:content
	 */
	private String callMethod( String action, HttpServletRequest req, 
			HttpServletResponse resp, int FLAG ) throws InstantiationException, IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException{
		//进行遍历之后找到特定的方法
		Method met = null;
		int parCount = 0;   //参数的个数。
		//这里的this指代的是继承了BaseController的子类的对象
		//也就是new实例化后实际调用的对象
		Class<? extends BaseController> clazz = this.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method: methods){
			String methodName = method.getName();
			if(action.equals(methodName)){
				met = method;
				System.out.println("met:"+met);
				break;
			}
		}
		
		Class<?>[] genParamTypes = met.getParameterTypes();
		parCount = genParamTypes.length;
		System.out.println("parCount:"+parCount);
		//{ps} 判断 null 值。
		if( met==null || (parCount!=2 && parCount!=3)  ){
			resp.sendError( 404 );   //显示 404 错误
			return null;
		}
		Object[] values = new Object[parCount];
		MethodInfo info = getMethodInfo(genParamTypes, values, req, resp);
		System.out.println("info:"+info);
		//{d} 判断   req, resp, other 的参数个数对不对。
		if( info.reqCount==1 && info.respCount==1 &&
			(info.otherCount==0 || info.otherCount==1) ){
			if(info.beanPos!=-1){
				System.out.println("进入beanUtil进行封装");
				values[info.beanPos] = BeanUtil.getBean(req, info.beanClass);
				met.setAccessible(true);
			}
			for(Object obj: values){
				System.out.println("obj:"+obj);
			}
			String temp = (String) met.invoke(this, values);
			System.out.println("temp:"+temp);
			return temp;
		} else{
			resp.sendError( 404 );   //显示 404 错误
		}
		
		return null;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
	}

	public String getAction(String uri){
		//这里的$2指代的是第二个小括号里面的内容
		//这里使用replaceAll可以将匹配到的全部内容匹配为后面那个
		return uri.replaceAll("(/(\\w+))+", "$2");
	}
	
	class MethodInfo{
		int reqCount = 0;
		int respCount = 0;
		int otherCount = 0;
		Class<?> beanClass = null;
		int beanPos = -1;
		@Override
		public String toString() {
			return "MethodInfo [reqCount=" + reqCount + ", respCount="
					+ respCount + ", otherCount=" + otherCount + ", beanClass="
					+ beanClass + ", beanPos=" + beanPos + "]";
		}
	}
	//这里存放的values有什么用
	public MethodInfo getMethodInfo( Class<?>[] genParamTypes, Object[] values, 
			HttpServletRequest req, HttpServletResponse resp ){
		MethodInfo info = new MethodInfo();
		int i = 0;
		for(Class<?> type: genParamTypes){
			if(type == HttpServletRequest.class){
				info.reqCount += 1;
				values[i] = req;
			} else{
				if(type == HttpServletResponse.class){
					info.respCount += 1;
					values[i] = resp;
				} else{
					info.otherCount += 1;
					info.beanClass = type;
					info.beanPos = i;
				}
			}
			i++;
		}
		System.out.println("info"+info);
		return info;
	}
}
