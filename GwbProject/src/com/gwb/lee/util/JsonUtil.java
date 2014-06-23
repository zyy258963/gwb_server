/**
 * 
 */
package com.gwb.lee.util;


import com.alibaba.fastjson.JSON;


public class JsonUtil {

	public JsonUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static String createJsonString(Object value) {
		return JSON.toJSONString(value);
	}
	
}

