package com.gwb.lee.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gwb.lee.dao.AndroidDao;
import com.gwb.lee.service.AndroidService;
import com.gwb.lee.util.ConstantParams;
import com.gwb.lee.util.FastJsonUtil;
import com.gwb.lee.util.JsonUtil;
import com.gwb.lee.vo.Ad;
import com.gwb.lee.vo.BookAppClass;
import com.gwb.lee.vo.BookCategory;
import com.gwb.lee.vo.BookClass;
import com.gwb.lee.vo.Books;
import com.gwb.lee.vo.FavouriteBook;
import com.gwb.lee.vo.Users;

public class AndroidAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AndroidService androidService;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		androidService = new AndroidDao();
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String rs = null;

		try {
			if ("listCategory".equals(type)) {
				rs = listCategory(request, response);
			} else if ("listClass".equals(type)) {
				rs = listClass(request, response);
			} else if ("listBook".equals(type)) {
				rs = listBook(request, response);
			} else if ("searchBook".equals(type)) {
				rs = searchBook(request, response);
				log(request,response,ConstantParams.LOG_SEARCH);
			} else if ("addFavourite".equals(type)) {
				rs = addFavourite(request, response);
			} else if ("deleteFavourite".equals(type)) {
				rs = deleteFavourite(request, response);
			} else if ("listFavourite".equals(type)) {
				rs = listFavourite(request, response);
			} else if ("listAd".equals(type)) {
				rs = listAd(request, response);
			} else if ("log".equals(type)) {
				log(request, response , ConstantParams.LOG_OPEN_DOC );
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("返回数据：<" + rs + ">");
		out.println(rs);
		out.flush();
		out.close();
	}
	
	private void log(HttpServletRequest request, HttpServletResponse response,
			String logType) throws Exception{
		String telephone = request.getParameter("telephone");
		System.out.println(logType);
		if (ConstantParams.LOG_SEARCH.equals(logType)) {
			String temp = request.getParameter("keywords");
			String keywords= URLDecoder.decode(temp, "UTF-8");
			androidService.log(telephone,ConstantParams.LOG_SEARCH,keywords);
		}else if (ConstantParams.LOG_LOGIN.equals(logType)) {
			androidService.log(telephone,ConstantParams.LOG_LOGIN,"");
		}else if (ConstantParams.LOG_OPEN_DOC.equals(logType)) {
			String temp = request.getParameter("keywords");
			String keywords= URLDecoder.decode(temp, "UTF-8");
			androidService.log(telephone,ConstantParams.LOG_OPEN_DOC,keywords);
		}
	}

	private String listAd(HttpServletRequest request,
			HttpServletResponse response) {
		String adPosition = request.getParameter("adPosition");
		List<Ad> list = androidService.listAd(adPosition);

		if (list != null) {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_SUCCESS, list);
		} else {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_FAIL, null);
		}
	}
	
	private String listFavourite(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");
		List<FavouriteBook> list = androidService.listFavouritebook(userId);

		if (list != null) {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_SUCCESS, list);
		} else {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_FAIL, null);
		}
	}

	private String deleteFavourite(HttpServletRequest request,
			HttpServletResponse response) {
		String telephone = request.getParameter("telephone");
		String favouriteId = request.getParameter("favouriteId");
		List<FavouriteBook> list = androidService.deleteFavouritebook(telephone,
				favouriteId);

		if (list != null) {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_SUCCESS, list);
		} else {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_FAIL, null);
		}
	}

	private String addFavourite(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String bookId = request.getParameter("bookId");
		String msg = androidService.addFavouritebook(userId, bookId);
		System.out.println(msg);
		if (ConstantParams.HTTP_SUCCESS.equals(msg)) {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_SUCCESS, msg);
		} else {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_FAIL, msg);
		}
	}


	private String searchBook(HttpServletRequest request,
			HttpServletResponse response) {
		String keywords = request.getParameter("keywords");
		List<Books> list = null;
		try {
			list = androidService.searchBook(URLDecoder.decode(keywords,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (list != null) {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_SUCCESS, list);
		} else {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_FAIL, null);
		}
	}

	private String listBook(HttpServletRequest request,
			HttpServletResponse response) {
		String classId = request.getParameter("classId");
		List<Books> list = androidService.listBook(classId);

		if (list != null) {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_SUCCESS, list);
		} else {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_FAIL, null);
		}
	}

	private String listClass(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String categoryId = request.getParameter("categoryId");
		List<BookAppClass> list = androidService.getClassByCategory(categoryId);

		if (list != null) {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_SUCCESS, list);
		} else {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_FAIL, null);
		}
	}

	private String listCategory(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<BookCategory> list = androidService.getAllCategoryList();

		if (list != null) {
			// return JsonUtil.createJsonString(list);
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_SUCCESS, list);
		} else {
			// return JsonUtil.createJsonString(list);
			return FastJsonUtil.getResultListMapJson(
					ConstantParams.HTTP_STATUS_HEADER_FAIL, null);
		}
	}
}