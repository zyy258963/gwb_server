package com.gwb.lee.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gwb.lee.dao.IosDao;
import com.gwb.lee.service.IosService;
import com.gwb.lee.util.ConstantParams;
import com.gwb.lee.util.FastJsonUtil;
import com.gwb.lee.vo.Ad;
import com.gwb.lee.vo.BookAppClass;
import com.gwb.lee.vo.BookCategory;
import com.gwb.lee.vo.BookClass;
import com.gwb.lee.vo.Books;
import com.gwb.lee.vo.FavouriteBook;

public class IosAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private IosService iosService;

	public IosAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		iosService = new IosDao();
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
				//log(request,response,ConstantParams.LOG_LOGIN);
			} else if ("listClass".equals(type)) {
				rs = listClass(request, response);
			} else if ("listBook".equals(type)) {
				rs = listBook(request, response);
			} else if ("searchBook".equals(type)) {
				rs = searchBook(request, response);
				log(request,response,ConstantParams.LOG_SEARCH);
			} else if ("listBookLimit".equals(type)) {
				rs = listBookLimit(request, response);
			} else if ("searchBookLimit".equals(type)) {
				rs = searchBookLimit(request, response);
				log(request,response,ConstantParams.LOG_SEARCH);
			} else if ("addFavourite".equals(type)) {
				rs = addFavourite(request, response);
			} else if ("deleteFavourite".equals(type)) {
				rs = deleteFavourite(request, response);
			} else if ("listFavourite".equals(type)) {
				rs = listFavourite(request, response);
			}else if ("listAd".equals(type)) {
				rs = listAd(request, response);
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
		
		if (ConstantParams.LOG_SEARCH.equals(logType)) {
			String keywords = request.getParameter("keywords");
			String temp = new String(keywords.getBytes("ISO-8859-1"),"UTF-8");
			iosService.log(telephone,ConstantParams.LOG_SEARCH,temp);
		}else if (ConstantParams.LOG_LOGIN.equals(logType)) {
			iosService.log(telephone,ConstantParams.LOG_LOGIN,"");
		}
		
	}
	
	private String listAd(HttpServletRequest request,
			HttpServletResponse response) {
		String adPosition = request.getParameter("adPosition");
		List<Ad> list = iosService.listAd(adPosition);

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
		List<FavouriteBook> list = iosService.listFavouritebook(userId);

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
		String userId = request.getParameter("userId");
		String favouriteId = request.getParameter("favouriteId");
		List<FavouriteBook> list = iosService.deleteFavouritebook(userId, favouriteId);

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
		String msg = iosService.addFavouritebook(userId, bookId);

		if (ConstantParams.HTTP_SUCCESS.equals(msg)) {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_SUCCESS, msg);
		} else {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_FAIL, msg);
		}
	}

	private String searchBookLimit(HttpServletRequest request,
			HttpServletResponse response) {
		String keywords = request.getParameter("keywords");
		String start = request.getParameter("start");
		String num = request.getParameter("num");
		List<Books> list = null;
		try {
			String temp = new String(keywords.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(temp);
			list = iosService.searchBookLimit(temp,start,num);
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

	private String listBookLimit(HttpServletRequest request,
			HttpServletResponse response) {
		String classId = request.getParameter("classId");
		String start = request.getParameter("start");
		String num = request.getParameter("num");
		List<Books> list = iosService.listBookLimit(classId,start,num);

		if (list != null) {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_SUCCESS, list);
		} else {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_FAIL, null);
		}
	}
	
	private String searchBook(HttpServletRequest request,
			HttpServletResponse response) {
		String keywords = request.getParameter("keywords");
		List<Books> list = null;
		try {
			String temp = new String(keywords.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(temp);
			list = iosService.searchBook(temp);
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
		List<Books> list = iosService.listBook(classId);

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
		List<BookAppClass> list = iosService.getClassByCategory(categoryId);

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
		List<BookCategory> list = iosService.getAllCategoryList();

		if (list != null) {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_SUCCESS, list);
		} else {
			return FastJsonUtil.getResultListMapJson(
					ConstantParams.HTTP_STATUS_HEADER_FAIL, null);
		}
	}

}
