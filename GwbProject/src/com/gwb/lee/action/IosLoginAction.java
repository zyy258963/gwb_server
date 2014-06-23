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

import com.gwb.lee.dao.IosDao;
import com.gwb.lee.service.IosService;
import com.gwb.lee.util.ConstantParams;
import com.gwb.lee.util.FastJsonUtil;

public class IosLoginAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IosService iosService;

	public IosLoginAction() {
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
			if ("appLogin".equals(type)) {
//				log(request,response,ConstantParams.LOG_LOGIN);
				rs = appLogin(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("返回数据：<" + rs + ">");
		out.println(rs);
		out.flush();
		out.close();
	}


	
	private String appLogin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String telephone = request.getParameter("telephone");
//		String password = request.getParameter("password");
		String macAddress = request.getParameter("macAddress");

		Map<String, Object> map = iosService.appLogin(telephone, macAddress);

		if (map != null && !map.isEmpty()) {
			return FastJsonUtil.getResultMapJson(
					ConstantParams.HTTP_STATUS_HEADER_SUCCESS, map);
		} else {
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("msg", "服务器出错,请联系管理员!");
			return FastJsonUtil.getResultMapJson(
					ConstantParams.HTTP_STATUS_HEADER_FAIL, temp);
		}
	}

}
