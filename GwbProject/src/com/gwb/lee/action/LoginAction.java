package com.gwb.lee.action;

import java.io.IOException;
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
import com.gwb.lee.service.AdminService;
import com.gwb.lee.util.ConstantParams;
import com.gwb.lee.util.DividePage;
import com.gwb.lee.vo.BookCategory;
import com.gwb.lee.vo.BookClass;
import com.gwb.lee.vo.FavouriteBook;
import com.gwb.lee.vo.OperatorLog;
import com.gwb.lee.vo.Users;

public class LoginAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminService adminService;
	private String path = null;

	public LoginAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		adminService = new AdminDao();
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		path = request.getContextPath();
		try {
			if ("adminLogin".equals(type)) {
				adminLogin(request, response);
			} else if ("logout".equals(type)) {
				logout(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void adminLogin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		if (adminService.login(userName, password)) {
//			request.getSession().setAttribute("listCategoryMaps", getCategoryList());
			request.getSession().setAttribute("user_name", userName);
			response.sendRedirect(path + "/main.jsp");
		} else {
			setMessage(request, ConstantParams.LOGIN_FAIL_FLAG);
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		}

	}

	// 查询用户的列表的时候显示
	private void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getSession().setAttribute("user_name", null);
		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

//	public List<Map<String, String>> getCategoryList() {
//		return adminService.getAllCategoryList();
//	}

}
