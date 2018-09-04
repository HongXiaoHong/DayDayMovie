package com.hong.utils;

import java.util.UUID;

import org.junit.Test;

public class UUIDUtil {

	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
/*	@Test
	public void test(){
		String temp = getUUID();
		System.out.println("temp:"+temp);
		System.out.println("temp的长度为："+temp.length());
	}*/
}
