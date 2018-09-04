package com.hong.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class GetPropertiesUtil {

	public static Map<String,String> getConInfo(String path) {
		Map<String,String> conInfo = new HashMap<String,String>();
		InputStream in = GetPropertiesUtil.class.getClassLoader().
				getResourceAsStream(path);
		Properties prop = new Properties();
		try {
			prop.load(in);
			Enumeration<Object> keys = prop.keys();
			while(keys.hasMoreElements()){
				String key = (String) (keys.nextElement());
				conInfo.put(key, prop.getProperty(key));
			}
			
			System.out.println("从配置文件中获取信息后放到map中map="+conInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conInfo;
	}
}
