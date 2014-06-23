//package com.gwb.lee.util;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.gwb.lee.vo.Content;
//import com.gwb.lee.vo.Header;
//import com.gwb.lee.vo.JsonMessage;
//
//public class TestJson {
//
//	public static void main(String[] args) {
//
//		Map<String, Object> map = new HashMap<String, Object>();
//
////		Map<String, String> headerMap = new HashMap<String, String>();
////		headerMap.put("code", "1");
////		headerMap.put("msg", "SUCCESS");
////		map.put("header", headerMap);
////
//		Map<String, String> map1 = new HashMap<String, String>();
//		map1.put("id", "101");
//		map1.put("name", "joy");
//		Map<String, String> map2 = new HashMap<String, String>();
//		map2.put("id", "101");
//		map2.put("name", "joy");
////		map.put("content", content);
//
//		// System.out.println(FastJsonUtil.createJsonString(map));
//
////		JsonMessage message = new JsonMessage(
////				new Header("1", "SUCCESS"),new Content(content));
//		
////		System.out.println(message.getJsonMap());
////		map = message.getJsonMap();
//		
//		List<Map<String, String>> content = new ArrayList<Map<String,String>>();
//
//		content.add(map1);
//		content.add(map2);
//		
//		Content content2 = new Content(content);
//		
//		map.put("header1", new Header("1", "SUCCESS"));
//		map.put("content", content2);
//		
//		System.out.println(FastJsonUtil.createJsonString(map));
//	}
//
//}
