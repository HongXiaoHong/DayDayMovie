package com.hong.utils;

import java.lang.reflect.Field;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class BeanUtil {

	public static Object getBean(HttpServletRequest req, Class<?> clazz){
		Object obj = null;
		try {
			obj = clazz.newInstance();
			Enumeration<String> paramNames = req.getParameterNames();
			while(paramNames.hasMoreElements()){
				String name = paramNames.nextElement();
				System.out.println("封装工具类表单中的name："+name);
				Field f = null;
				try{
					f = clazz.getDeclaredField(name);
				} catch(Exception e){
					e.printStackTrace();
				} finally{
					if(f!=null){
						f.setAccessible(true);
						setValue(f, obj, req.getParameter(name));
						//f.set(obj, req.getParameter(name));
					}else{
						System.out.println("没有这个类型"+name);
					}
				}
				
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	private static void setValue(Field f, Object obj, String value) throws NumberFormatException, IllegalArgumentException, IllegalAccessException {
		Class<?> type = f.getType();
		if(value != null){
			//只有当value的值不为空的时候，才可以对主调对象进行设置
			if(type==int.class){
				f.set(obj, Integer.valueOf(value));
			} else{
				if(type==double.class){
					f.set(obj, Double.valueOf(value));
				} else {
					f.set(obj, value);
				}
			}
		} else{
			System.out.println("值为空");
		}
	}
}
