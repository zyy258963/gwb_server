package com.gwb.lee.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class TestDemo {

	public static void main(String[] args) {

//		new TestDemo().changeChapterUrl2ImageUrl();

		
		String url = "http://117.79.84.185:8080/GwbProject/IosAction?type=searchBook&telephone=12312312312&macAddress=EACB886E-C697-486A-B716-CE1BFF5E39F2&keywords=铁路";
		try {
			String encode= URLEncoder.encode("文档", "UTF-8");
			System.out.println(encode);
			
			String decode = URLDecoder.decode(encode, "UTF-8");
			System.out.println(decode);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void changeChapterUrl2ImageUrl() {
		String chapterUrl = "http://localhost:8080/GwbProject/upload/books/bookName/chapterRank/chapterId.jsp";
		int end = chapterUrl.lastIndexOf("/");
		System.out.println(end);
		
		
		String baseUrl = chapterUrl.substring(0, end+1);
		System.out.println(baseUrl);
		
		String chapterPngUrl =  chapterUrl.substring(chapterUrl.indexOf("upload"));
		System.out.println(chapterPngUrl);
	}

}