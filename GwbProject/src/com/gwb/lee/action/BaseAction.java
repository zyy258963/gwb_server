package com.gwb.lee.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gwb.lee.util.ConstantParams;


public class BaseAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaseAction() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(request, response);
	}
	
	public void setMessage(HttpServletRequest request,String flag){
		if (ConstantParams.ADD_SUCCESS_FLAG.equals(flag)) {
			request.setAttribute(ConstantParams.RETURN_MSG, ConstantParams.ADD_SUCCESS);
		}else if (ConstantParams.ADD_FAIL_FLAG.equals(flag)) {
			request.setAttribute(ConstantParams.RETURN_MSG, ConstantParams.ADD_FAIL);
		}else if (ConstantParams.EDIT_SUCCESS_FLAG.equals(flag)) {
			request.setAttribute(ConstantParams.RETURN_MSG, ConstantParams.EDIT_SUCCESS);
		}else if (ConstantParams.EDIT_FAIL_FLAG.equals(flag)) {
			request.setAttribute(ConstantParams.RETURN_MSG, ConstantParams.ADD_FAIL);
		}else if (ConstantParams.DELETE_SUCCESS_FLAG.equals(flag)) {
			request.setAttribute(ConstantParams.RETURN_MSG, ConstantParams.DELETE_SUCCESS);
		}else if (ConstantParams.DELETE_FAIL_FLAG.equals(flag)) {
			request.setAttribute(ConstantParams.RETURN_MSG, ConstantParams.DELETE_FAIL);
		}else if (ConstantParams.OPERATE_SUCCESS_FLAG.equals(flag)) {
			request.setAttribute(ConstantParams.RETURN_MSG, ConstantParams.OPERATE_SUCCESS);
		}else if (ConstantParams.LOGIN_FAIL_FLAG.equals(flag)) {
			request.setAttribute(ConstantParams.RETURN_MSG, ConstantParams.LOGIN_FAIL);
		}else if (ConstantParams.REPEAT_TELEPHONE_FLAG.equals(flag)) {
			request.setAttribute(ConstantParams.RETURN_MSG, ConstantParams.REPEAT_TELEPHONE);
		}else if (ConstantParams.REPEAT_BOOK_NAME_FLAG.equals(flag)) {
			request.setAttribute(ConstantParams.RETURN_MSG, ConstantParams.REPEAT_BOOK_NAME);
		}else if (ConstantParams.REPEAT_BOOK_NAME_FLAG.equals(flag)) {
			request.setAttribute(ConstantParams.RETURN_MSG, ConstantParams.REPEAT_CLASS_NAME);
		}else {
			request.setAttribute(ConstantParams.RETURN_MSG, null);
		}
	}
	
	
	
}
