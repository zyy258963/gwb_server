package com.gwb.lee.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gwb.lee.dao.AdminDao;
import com.gwb.lee.dao.AndroidDao;
import com.gwb.lee.service.AdminService;
import com.gwb.lee.service.AndroidService;
import com.gwb.lee.util.ConstantParams;
import com.gwb.lee.util.DividePage;
import com.gwb.lee.util.FastJsonUtil;
import com.gwb.lee.vo.BookCategory;
import com.gwb.lee.vo.BookClass;
import com.gwb.lee.vo.FavouriteBook;
import com.gwb.lee.vo.OperatorLog;
import com.gwb.lee.vo.Users;

public class AndroidLoginAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AndroidService androidService;
	private String path = null;

	public AndroidLoginAction() {
		// TODO Auto-generated constructor stub
	}

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
			if ("appLogin".equals(type)) {
				//log(request,response,ConstantParams.LOG_LOGIN);
				rs = appLogin(request, response);
			} else if ("logout".equals(type)) {
//				rs = logout(request, response);
			}else if ("filterUser".equals(type)) {
				rs = filterUser(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("返回数据：<" + rs + ">");
		out.println(rs);
		out.flush();
		out.close();
	}

	
	private String filterUser(HttpServletRequest request,
			HttpServletResponse response) {
		String filterCode = request.getParameter("filterCode");
//		2 是 没有通过验证的，0 是查询报错的
		if ("2".equals(filterCode)) {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_FAIL_NO_MAC, "当前用户信息有误！");
		}else {
			return FastJsonUtil.getResultListJson(
					ConstantParams.HTTP_STATUS_HEADER_FAIL, "参数有误或系统出错！");
		}
		
	}

	private String appLogin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String telephone = request.getParameter("telephone");
//		String password = request.getParameter("password");
		String macAddress = request.getParameter("macAddress");

		Map<String, Object> map = androidService.appLogin(telephone, macAddress);

		if (map != null && !map.isEmpty()) {
			if (map.get("msg")==null || "".equals(map.get("msg"))) {
				log(request,response,ConstantParams.LOG_LOGIN);
			}
			return FastJsonUtil.getResultMapJson(
					ConstantParams.HTTP_STATUS_HEADER_SUCCESS, map);
		} else {
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("msg", "服务器出错,请联系管理员!");
			return FastJsonUtil.getResultMapJson(
					ConstantParams.HTTP_STATUS_HEADER_FAIL, temp);
		}

	}

	// 查询用户的列表的时候显示
	private void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getSession().setAttribute("user_name", null);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	public List<BookCategory> getCategoryList() {
		return androidService.getAllCategoryList();
	}
	
	private void log(HttpServletRequest request, HttpServletResponse response,
			String logType) throws Exception{
		String telephone = request.getParameter("telephone");
		
		if (ConstantParams.LOG_SEARCH.equals(logType)) {
			String temp = request.getParameter("keywords");
			String keywords = URLDecoder.decode(temp,"UTF-8");
			androidService.log(telephone,ConstantParams.LOG_SEARCH,keywords);
		}else if (ConstantParams.LOG_LOGIN.equals(logType)) {
			androidService.log(telephone,ConstantParams.LOG_LOGIN,"");
		}
	}


}
