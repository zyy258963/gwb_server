package com.gwb.lee.util;

import com.gwb.lee.vo.Header;

public class ConstantParams {

	public static final int PAGE_SIZE = 10;

	public static final int PAGE_IOS_SIZE = 10;
	public static int TOTAL_BOOK_NUM = 0;
	public static int TOTAL_PAGE_NUM = 0;

	public static final String RETURN_MSG = "respMsg";

	public static final String LOGIN_SUCCESS_FLAG = "loginSuccess";
	public static final String LOGIN_FAIL_FLAG = "loginFail";
	public static final String ADD_SUCCESS_FLAG = "addSuccess";
	public static final String ADD_FAIL_FLAG = "addFail";
	public static final String EDIT_SUCCESS_FLAG = "editSuccess";
	public static final String EDIT_FAIL_FLAG= "editFail";
	public static final String DELETE_SUCCESS_FLAG = "deleteSuccess";
	public static final String DELETE_FAIL_FLAG = "deleteFail";
	public static final String OPERATE_FAIL_FLAG = "operateFail";
	public static final String OPERATE_SUCCESS_FLAG = "operateSuccess";
	public static final String REPEAT_TELEPHONE_FLAG = "repeatTelephone";
	public static final String REPEAT_CLASS_NAME_FLAG = "repeatClassName";
	public static final String REPEAT_BOOK_NAME_FLAG = "repeatBookName";
	
	public static final String LOGIN_SUCCESS = "登陆成功！！";
	public static final String LOGIN_FAIL = "用户名或密码错误！！";
	
	public static final String ADD_SUCCESS = "添加成功！！！";
	public static final String ADD_FAIL = "添加失败！！！";
	public static final String EDIT_SUCCESS = "编辑成功！！！";
	public static final String EDIT_FAIL= "编辑失败！！！";
	public static final String DELETE_SUCCESS = "删除成功！！！";
	public static final String DELETE_FAIL = "删除失败！！！";
	public static final String OPERATE_FAIL = "操作失败！！！";
	public static final String OPERATE_SUCCESS = "操作成功！！！";
	public static final String REPEAT_TELEPHONE = "添加失败。电话已存在,请检查！！！";
	public static final String REPEAT_CLASS_NAME = "添加失败。专业名称已存在，请检查！！！";
	public static final String REPEAT_BOOK_NAME = "添加失败。文档名称已存在，请检查！！1";

	public static final String LOG_LOGIN = "登录:";
	public static final String LOG_VIEW_CATEGORY = "查看行业:";
	public static final String LOG_VIEW_CLASS = "查看专业:";
	public static final String LOG_VIEW_BOOK = "文档:";
	public static final String LOG_ADD_FAVORITE = "收藏文档:";
	public static final String LOG_DELETE_FAVORITE = "取消收藏文档:";
	public static final String LOG_SEARCH = "搜索:";
	
	public static final String FILE_SAVE_PATH = "/usr/local/tomcat/webapps/FileUpload/upload/books";
	public static final String FILE_SAVE_IMAGE_PATH = "/usr/local/tomcat/webapps/FileUpload/upload/images";
	
//	public static final String FILE_SAVE_PATH = "G:/workspace/javaee/FileUpload/WebContent/upload/books";
//	public static final String FILE_SAVE_IMAGE_PATH = "G:/workspace/javaee/FileUpload/WebContent/upload/images";

//	public static final String URL_IMAGE_PATH = "http://192.168.1.2:8080/FileUpload/upload/";
	public static final String URL_IMAGE_PATH = "http://117.79.84.185:8080/FileUpload/upload/";
	
//	与手机端通信的 静态变量的定义

	public static final String HTTP_SUCCESS= "success";
	public static final String HTTP_FAIL= "fail";
	
	public static final String HTTP_TITLE_HEADER = "header";
	public static final String HTTP_HEADER_CODE = "code";
	public static final String HTTP_HEADER_MSG = "msg";
	
	public static final String HTTP_STATUS_HEADER_SUCCESS= "success";
	public static final String HTTP_STATUS_HEADER_FAIL= "error";
	public static final String HTTP_STATUS_HEADER_FAIL_NO_MAC= "fail";

	public static final Header HTTP_SUCCESS_HEADER = new Header("1", "SUCCESS");
	public static final Header HTTP_FAIL_HEADER = new Header("0", "ERROR");
	public static final Header HTTP_FAIL_NO_MAC_HEADER = new Header("2", "FAIL");
	
	public static final String HTTP_TITLE_CONTENT = "content";
	public static final String HTTP_CONTENT_LOGIN_ID = "id";
	
	

	public static final int STATUS_FILTER_ERROR = 0;
	public static final int STATUS_FILTER_SUCCESS = 1;
	public static final int STATUS_FILTER_NO_MACADDRESS = 2;
	
}
