package com.gwb.lee.action;

import java.awt.print.Book;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.gwb.lee.dao.AdminDao;
import com.gwb.lee.service.AdminService;
import com.gwb.lee.util.ConstantParams;
import com.gwb.lee.util.DividePage;
import com.gwb.lee.util.FileUtils;
import com.gwb.lee.vo.Ad;
import com.gwb.lee.vo.BookCategory;
import com.gwb.lee.vo.BookClass;
import com.gwb.lee.vo.Books;
import com.gwb.lee.vo.FavouriteBook;
import com.gwb.lee.vo.OperatorLog;
import com.gwb.lee.vo.Users;

public class AdminAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminService adminService;
	private String path = null;

	public AdminAction() {
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
			if ("listUser".equals(type)) {
				listUser(request, response);
			} else if ("addUser".equals(type)) {
				addUser(request, response);
			} else if ("viewUser".equals(type)) {
				viewUser(request, response);
			} else if ("editUser".equals(type)) {
				editUser(request, response);
			} else if ("deleteUser".equals(type)) {
				deleteUser(request, response);
			} else if ("listFavorite".equals(type)) {
				listFavorite(request, response);
			} else if ("listOperator".equals(type)) {
				listOperator(request, response);
			} else if ("changePwd".equals(type)) {
				changePwd(request, response);
			} else if ("unbindUser".equals(type)) {
				unbindUser(request, response);
			} else if ("deleteManyUser".equals(type)) {
				deleteManyUser(request, response);
			} else if ("deleteManyUserOperator".equals(type)) {
				deleteManyUserOperator(request, response);
			}

			if ("addCategory".equals(type)) {
				addCategory(request, response);
			} else if ("viewCategory".equals(type)) {
				viewCategory(request, response);
			} else if ("editCategory".equals(type)) {
				editCategory(request, response);
			} else if ("deleteCategory".equals(type)) {
				deleteCategory(request, response);
			} else if ("listCategory".equals(type)) {
				listCategory(request, response);
			}

			if ("initAddClass".equals(type)) {
				initAddClass(request, response);
			} else if ("addClass".equals(type)) {
				addClass(request, response);
			} else if ("viewClass".equals(type)) {
				viewClass(request, response);
			} else if ("editClass".equals(type)) {
				editClass(request, response);
			} else if ("deleteClass".equals(type)) {
				deleteClass(request, response);
			} else if ("listClass".equals(type)) {
				listClass(request, response);
			}

			if ("initAddBook".equals(type)) {
				initAddBook(request, response);
			} else if ("addBook".equals(type)) {
				addBook(request, response);
			} else if ("viewBook".equals(type)) {
				viewBook(request, response);
			} else if ("editBook".equals(type)) {
				editBook(request, response);
			} else if ("deleteBook".equals(type)) {
				deleteBook(request, response);
			} else if ("listBook".equals(type)) {
				listBook(request, response);
			} else if ("changeBookClass".equals(type)) {
				changeBookClass(request, response);
			} else if ("deleteManyBook".equals(type)) {
				deleteManyBook(request, response);
			}

			if ("addAd".equals(type)) {
				addAd(request, response);
			} else if ("viewAd".equals(type)) {
				viewAd(request, response);
			} else if ("listAd".equals(type)) {
				listAd(request, response);
			} else if ("deleteAd".equals(type)) {
				deleteAd(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteManyBook(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bookIds = request.getParameter("checkBookIds");
		boolean flag = adminService.deleteManyBook(bookIds);
		if (flag) {
			setMessage(request, ConstantParams.DELETE_SUCCESS_FLAG);
		} else {
			setMessage(request, ConstantParams.DELETE_FAIL_FLAG);
		}

		request.setAttribute("listCategoryMaps", getCategoryList());
		request.getRequestDispatcher("/AdminAction?type=listBook").forward(
				request, response);
	}

	private void deleteManyUserOperator(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userIds = request.getParameter("checkIds");
		String operatorUserId = request.getParameter("operatorUserId");
		boolean flag = adminService.deleteManyUserOperator(userIds);
		if (flag) {
			setMessage(request,ConstantParams.DELETE_SUCCESS_FLAG);
		} else {
			setMessage(request,ConstantParams.DELETE_FAIL_FLAG); 
		}
		request.setAttribute("userId", operatorUserId);
		request.getRequestDispatcher("/AdminAction?type=listOperator&userId="+operatorUserId).forward(request, response);
	}

	private void deleteManyUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userIds = request.getParameter("checkIds");
		boolean flag = adminService.deleteManyUser(userIds);
		String rs = null;
		if (flag) {
			rs = ConstantParams.DELETE_SUCCESS_FLAG;
		} else {
			rs = ConstantParams.DELETE_FAIL_FLAG;
		}

		response.sendRedirect(path + "/AdminAction?type=listUser&respMsg=" + rs);
	}

	
	private void unbindUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获得修改后的变量的值
		String userid = request.getParameter("userId");
		boolean flag = adminService.unbindUser(userid);
		if (flag) {
			setMessage(request, ConstantParams.OPERATE_SUCCESS_FLAG);
		} else {
			setMessage(request, ConstantParams.OPERATE_FAIL_FLAG);
		}
		request.getRequestDispatcher("/AdminAction?type=listUser").forward(
				request, response);

	}

	private void deleteAd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String adId = request.getParameter("adId");
		boolean flag = adminService.deleteAd(adId);
		if (flag) {
			setMessage(request, ConstantParams.DELETE_SUCCESS_FLAG);
		} else {
			setMessage(request, ConstantParams.DELETE_FAIL_FLAG);
		}
		request.getRequestDispatcher("/AdminAction?type=listAd").forward(
				request, response);
	}

	private void listAd(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Ad> ads = adminService.listAd();
		request.setAttribute("listAd", ads);
		request.getRequestDispatcher("/jsp/ad/listAd.jsp").forward(request,
				response);

	}

	private void viewAd(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String adId = request.getParameter("adId");
		Ad ad = adminService.viewAd(adId);
		request.setAttribute("ad", ad);
		request.setAttribute("adSrc",
				ConstantParams.URL_IMAGE_PATH + ad.getAdPic());
		request.getRequestDispatcher("/jsp/ad/viewAd.jsp").forward(request,
				response);

	}

	private void addAd(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 保存上载文件
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 构建一个文件上传类
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		fileUpload.setFileSizeMax(2 * 1024 * 1024);
		fileUpload.setSizeMax(3 * 1024 * 1024);
		List<FileItem> fileItems = null;
		Map<String, Object> params = new HashMap<String, Object>();

		try {
			fileItems = fileUpload.parseRequest(request);
			for (FileItem fileItem : fileItems) {
				if (fileItem.isFormField()) {
					if ("adName".equals(fileItem.getFieldName())) {
						params.put("adName", fileItem.getString("utf-8"));
					}
					if ("adUrl".equals(fileItem.getFieldName())) {
						params.put("adUrl", fileItem.getString("utf-8"));
					}
					if ("adPosition".equals(fileItem.getFieldName())) {
						params.put("adPosition", fileItem.getString("utf-8"));
					}
				}
			}
			for (FileItem fileItem : fileItems) {
				if (!fileItem.isFormField()) {
					String docName = fileItem.getName();
					if (docName != null && !"".equals(docName)) {
						// 上传文件保存路径
						String imgDir = ConstantParams.FILE_SAVE_IMAGE_PATH
								+ File.separator + params.get("adPosition");
						FileUtils.createDir(imgDir);
						File saveFile = new File(imgDir, getFileName()
								+ docName.substring(docName.lastIndexOf(".")));
						fileItem.write(saveFile);
						params.put("adPic",
								imgDir.substring(imgDir.lastIndexOf("/") + 1)
										+ File.separator + saveFile.getName());
					} else {
						params.put("adPic", "");
					}
				}
			}

		} catch (Exception e) {
			System.out.println("-------》上传文件失败");
			e.printStackTrace();
		}

		boolean flag = adminService.addAd(params);
		if (flag) {
			setMessage(request, ConstantParams.ADD_SUCCESS_FLAG);
		} else {
			setMessage(request, ConstantParams.ADD_FAIL_FLAG);
		}
		request.getRequestDispatcher("/AdminAction?type=listAd").forward(
				request, response);

	}

	private void changeBookClass(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");

		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 构建一个文件上传类
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		fileUpload.setFileSizeMax(20 * 1024 * 1024);
		fileUpload.setSizeMax(30 * 1024 * 1024);
		List<FileItem> fileItems = null;
		Map<String, Object> params = new HashMap<String, Object>();

		fileItems = fileUpload.parseRequest(request);
		for (FileItem fileItem : fileItems) {
			if (fileItem.isFormField()) {
				if ("categoryId".equals(fileItem.getFieldName())) {
					params.put("categoryId", fileItem.getString("utf-8"));
				}
				if ("classId".equals(fileItem.getFieldName())) {
					params.put("classId", fileItem.getString("utf-8"));
				}
				if ("bookName".equals(fileItem.getFieldName())) {
					params.put("bookName", fileItem.getString("utf-8"));
				}
				if ("bookDesc".equals(fileItem.getFieldName())) {
					params.put("bookDesc", fileItem.getString("utf-8"));
				}
				if ("bookId".equals(fileItem.getFieldName())) {
					params.put("bookId", fileItem.getString("utf-8"));
				}
			}
		}

		request.setAttribute("listCategoryMaps", getCategoryList());
		request.setAttribute("listClassMaps",
				getClassMap(params.get("categoryId").toString()));
		request.setAttribute("categoryId", params.get("categoryId"));
		request.setAttribute("classId", params.get("classId"));
		request.setAttribute("bookName", params.get("bookName"));
		request.setAttribute("bookDesc", params.get("bookDesc"));

		if (action != null && !"".equals(action) && "add".equals(action)) {

			request.getRequestDispatcher("/jsp/book/addBook.jsp").forward(
					request, response);
		} else {
			request.getRequestDispatcher("/jsp/book/editBook.jsp").forward(
					request, response);
		}

	}

	private void changePwd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String password = request.getParameter("password");
		String userName = request.getSession().getAttribute("user_name")
				.toString();
		boolean flag = adminService.changePwd(userName, password);
		if (flag) {
			setMessage(request, ConstantParams.EDIT_SUCCESS_FLAG);
		} else {
			setMessage(request, ConstantParams.EDIT_FAIL_FLAG);
		}
		request.getRequestDispatcher("/jsp/user/changePwd.jsp").forward(
				request, response);
	}

	private void listBook(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 验证用户是否填写了特定的页数来显示
		String pageIndex = request.getParameter("pageIndex");
		// 接收用户的查询名字
		String categoryId = request.getParameter("categoryId");
		String classId = request.getParameter("classId");
		String keywords = request.getParameter("keywords");
		int recordCount = adminService.getBookSearchItemCount(categoryId,
				classId, keywords);// 获得记录的总条数
		int currentPage = 1;// 当前页是第一页
		String pageNum = request.getParameter("pageNum");

		if (pageNum != null && !"".equals(pageNum)) {
			currentPage = Integer.parseInt(pageNum);
		} else if (pageIndex != null && !"".equals(pageIndex)) {
			currentPage = Integer.parseInt(pageIndex);
		}

		DividePage pUtil = new DividePage(ConstantParams.PAGE_SIZE,
				recordCount, currentPage);
		int start = pUtil.getFromIndex();
		int end = pUtil.getToIndex();
		// 已经进行分页之后的数据集合
		List<Books> list = adminService.listBook(categoryId, classId, keywords,
				start, end);
		request.setAttribute("pUtil", pUtil);
		request.setAttribute("totalPages", (pUtil.getRecordCount()
				+ ConstantParams.PAGE_SIZE - 1)
				/ ConstantParams.PAGE_SIZE);
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("classId", classId);
		request.setAttribute("listBook", list);
		request.setAttribute("startNo", start + 1);
		request.setAttribute("keywords", keywords);
		request.setAttribute("listCategoryMaps", getCategoryList());
		if (categoryId != null && !"".equals(categoryId)) {
			request.setAttribute("listClassMaps", getClassMap(categoryId));
		}

		request.getRequestDispatcher("/jsp/book/listBook.jsp").forward(request,
				response);
	}

	private void deleteBook(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获得选中的复选框的值
		String bookId = request.getParameter("bookId");
		boolean flag = adminService.deleteBook(bookId);
		if (flag) {
			setMessage(request, ConstantParams.DELETE_SUCCESS_FLAG);
		} else {
			setMessage(request, ConstantParams.DELETE_FAIL_FLAG);
		}

		request.setAttribute("listCategoryMaps", getCategoryList());
		request.getRequestDispatcher("/AdminAction?type=listBook").forward(
				request, response);

	}

	private void editBook(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		// bookName,bookDesc,categoryId,classId,bookUrl

		// 保存上载文件
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 构建一个文件上传类
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		fileUpload.setFileSizeMax(20 * 1024 * 1024);
		fileUpload.setSizeMax(30 * 1024 * 1024);
		List<FileItem> fileItems = null;
		Map<String, Object> params = new HashMap<String, Object>();

		try {
			fileItems = fileUpload.parseRequest(request);
			for (FileItem fileItem : fileItems) {
				if (fileItem.isFormField()) {
					if ("bookName".equals(fileItem.getFieldName())) {
						params.put("bookName", fileItem.getString("utf-8"));
					}
					if ("bookDesc".equals(fileItem.getFieldName())) {
						params.put("bookDesc", fileItem.getString("utf-8"));
					}
					if ("categoryId".equals(fileItem.getFieldName())) {
						params.put("categoryId", fileItem.getString("utf-8"));
					}
					if ("classId".equals(fileItem.getFieldName())) {
						params.put("classId", fileItem.getString("utf-8"));
					}
					if ("reUpload".equals(fileItem.getFieldName())) {
						params.put("reUpload", fileItem.getString("utf-8"));
					}
					if ("bookId".equals(fileItem.getFieldName())) {
						params.put("bookId", fileItem.getString("utf-8"));
					}
				}
			}
			if (params.get("reUpload") != null
					&& "1".equals(params.get("reUpload"))) {
				for (FileItem fileItem : fileItems) {
					if (!fileItem.isFormField()) {
						String docName = fileItem.getName();
						if (docName != null && !"".equals(docName)) {
							// 上传文件保存路径
							String chapterDir = ConstantParams.FILE_SAVE_PATH
									+ File.separator + params.get("categoryId")
									+ "-" + params.get("classId");
							FileUtils.createDir(chapterDir);
							File saveFile = new File(chapterDir, getFileName()
									+ docName.substring(docName
											.lastIndexOf(".")));
							fileItem.write(saveFile);
							params.put(
									"bookUrl",
									chapterDir.substring(chapterDir
											.lastIndexOf("/") + 1)
											+ File.separator
											+ saveFile.getName());
						} else {
							params.put("bookUrl", "");
						}
					}
				}
			} else {
				params.put("bookUrl", "");
			}
		} catch (Exception e) {
			System.out.println("-------》上传文件失败");
			e.printStackTrace();
		}

		boolean flag = adminService.editBook(params);
		if (flag) {
			setMessage(request, ConstantParams.EDIT_SUCCESS_FLAG);
		} else {
			setMessage(request, ConstantParams.EDIT_FAIL_FLAG);
		}
		request.setAttribute("categoryId", params.get("categoryId"));
		request.setAttribute("classId", params.get("classId"));
		request.setAttribute("bookName", params.get("bookName"));
		request.setAttribute("bookDesc", params.get("bookDesc"));
		request.setAttribute("bookId", params.get("bookId"));
		request.setAttribute("bookUrl", params.get("bookUrl"));
		request.setAttribute("listCategoryMaps", getCategoryList());
		request.setAttribute("listClassMaps",
				getClassMap(String.valueOf(params.get("categoryId"))));
		request.getRequestDispatcher("/jsp/book/editBook.jsp").forward(request,
				response);
	}

	private void viewBook(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bookId = request.getParameter("bookId");
		Books b = adminService.viewBook(bookId);
		// request.setAttribute("book", b);
		request.setAttribute("categoryId", b.getCategoryId());
		request.setAttribute("classId", b.getClassId());
		request.setAttribute("bookName", b.getBookName());
		request.setAttribute("bookDesc", b.getBookDesc());
		request.setAttribute("bookId", b.getBookId());
		request.setAttribute("bookUrl", b.getBookUrl());
		request.setAttribute("listCategoryMaps", getCategoryList());
		request.setAttribute("listClassMaps",
				getClassMap(String.valueOf(b.getCategoryId())));
		request.getRequestDispatcher("/jsp/book/editBook.jsp").forward(request,
				response);
	}

	private void addBook(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		// bookName,bookDesc,categoryId,classId,bookUrl

		request.setAttribute("listCategoryMaps", getCategoryList());
		// 保存上载文件
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 构建一个文件上传类
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		fileUpload.setFileSizeMax(20 * 1024 * 1024);
		fileUpload.setSizeMax(30 * 1024 * 1024);
		List<FileItem> fileItems = null;
		Map<String, Object> params = new HashMap<String, Object>();

		try {
			fileItems = fileUpload.parseRequest(request);
			for (FileItem fileItem : fileItems) {
				if (fileItem.isFormField()) {
					if ("bookName".equals(fileItem.getFieldName())) {
						params.put("bookName", fileItem.getString("utf-8"));
					}
					if ("bookDesc".equals(fileItem.getFieldName())) {
						params.put("bookDesc", fileItem.getString("utf-8"));
					}
					if ("categoryId".equals(fileItem.getFieldName())) {
						params.put("categoryId", fileItem.getString("utf-8"));
					}
					if ("classId".equals(fileItem.getFieldName())) {
						params.put("classId", fileItem.getString("utf-8"));
					}
				}
			}

			// 检验文档名称是否重复
			String categoryId = (String) params.get("categoryId");
			String classId = (String) params.get("classId");
			String bookName = (String) params.get("bookName");
			if (adminService.checkBookName(categoryId, classId, bookName)) {
				setMessage(request, ConstantParams.REPEAT_BOOK_NAME_FLAG);
				request.setAttribute("classId", classId);
				request.setAttribute("bookName", bookName);
				request.setAttribute("categoryId", categoryId);
				request.setAttribute("bookDesc", params.get("bookDesc"));
				request.getRequestDispatcher("/jsp/book/addBook.jsp").forward(
						request, response);
			} else {

				for (FileItem fileItem : fileItems) {
					if (!fileItem.isFormField()) {
						String docName = fileItem.getName();
						if (docName != null && !"".equals(docName)) {
							// 上传文件保存路径

							// String chapterDir = ConstantParams.FILE_SAVE_PATH
							// + File.separator + params.get("categoryId")
							// + File.separator + params.get("classId");
							// FileUtils.createDir(chapterDir);
							// File saveFile = new File(chapterDir, docName);
							// fileItem.write(saveFile);
							// params.put(
							// "bookUrl",
							// chapterDir.substring(chapterDir
							// .lastIndexOf("/") + 1)
							// + File.separator
							// + saveFile.getName());

							String chapterDir = ConstantParams.FILE_SAVE_PATH
									+ File.separator + params.get("categoryId")
									+ "-" + params.get("classId");
							FileUtils.createDir(chapterDir);
							File saveFile = new File(chapterDir, getFileName()
									+ docName.substring(docName
											.lastIndexOf(".")));
							fileItem.write(saveFile);
							params.put(
									"bookUrl",
									chapterDir.substring(chapterDir
											.lastIndexOf(File.separator) + 1)
											+ File.separator
											+ saveFile.getName());
						} else {
							params.put("bookUrl", "");
						}
					}
				}

				boolean flag = adminService.addBook(params);
				if (flag) {
					setMessage(request, ConstantParams.ADD_SUCCESS_FLAG);
				} else {
					setMessage(request, ConstantParams.ADD_FAIL_FLAG);
				}
				request.getRequestDispatcher("/AdminAction?type=listBook")
						.forward(request, response);

			}

		} catch (Exception e) {
			System.out.println("-------》上传文件失败");
			e.printStackTrace();
		}

	}

	private void initAddBook(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获得所有 已有目录的列表getCategoryList();
		request.setAttribute("listCategoryMaps", getCategoryList());
		// 版本2-----单独上传文件然后指定目录
		// request.setAttribute("listFiles",
		// FileUtils.getPathFiles(ConstantParams.FILE_SAVE_PATH));
		request.getRequestDispatcher("/jsp/book/addBook.jsp").forward(request,
				response);
	}

	private void listClass(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 验证用户是否填写了特定的页数来显示
		String pageIndex = request.getParameter("pageIndex");
		// 接收用户的查询名字
		String categoryId = request.getParameter("categoryId");
		String keywords = request.getParameter("keywords");
		int recordCount = adminService.getClassSearchItemCount(categoryId,
				keywords);// 获得记录的总条数
		int currentPage = 1;// 当前页是第一页
		String pageNum = request.getParameter("pageNum");

		if (pageNum != null && !"".equals(pageNum)) {
			currentPage = Integer.parseInt(pageNum);
		} else if (pageIndex != null && !"".equals(pageIndex)) {
			currentPage = Integer.parseInt(pageIndex);
		}

		DividePage pUtil = new DividePage(ConstantParams.PAGE_SIZE,
				recordCount, currentPage);
		int start = pUtil.getFromIndex();
		int end = pUtil.getToIndex();
		// 已经进行分页之后的数据集合
		List<BookClass> list = adminService.listBookClass(categoryId, keywords,
				start, end);
		request.setAttribute("pUtil", pUtil);
		request.setAttribute("totalPages", (pUtil.getRecordCount()
				+ ConstantParams.PAGE_SIZE - 1)
				/ ConstantParams.PAGE_SIZE);
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("listClass", list);
		request.setAttribute("keywords", keywords);
		request.setAttribute("listCategoryMaps", getCategoryList());

		request.getRequestDispatcher("/jsp/class/listClass.jsp").forward(
				request, response);

	}

	private void deleteClass(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获得选中的复选框的值
		String classId = request.getParameter("classId");
		boolean flag = adminService.deleteBookClass(classId);
		if (flag) {
			setMessage(request, ConstantParams.DELETE_SUCCESS_FLAG);
		} else {
			setMessage(request, ConstantParams.DELETE_FAIL_FLAG);
		}
		request.setAttribute("listCategoryMaps", getCategoryList());
		request.getRequestDispatcher("/AdminAction?type=listClass").forward(
				request, response);
	}

	private void editClass(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获得修改后的变量的值
		String classid = request.getParameter("classId");
		String className = request.getParameter("className");
		String categoryId = request.getParameter("categoryId");
		;
		boolean flag = adminService.editBookClass(classid, className,
				categoryId);
		request.setAttribute("classId", classid);
		request.setAttribute("className", className);
		request.setAttribute("categoryId", categoryId);
		if (flag) {
			setMessage(request, ConstantParams.EDIT_SUCCESS_FLAG);
		} else {
			setMessage(request, ConstantParams.EDIT_FAIL_FLAG);
		}
		request.setAttribute("listCategoryMaps", getCategoryList());
		request.getRequestDispatcher("/jsp/class/editClass.jsp").forward(
				request, response);

	}

	private void viewClass(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String classid = request.getParameter("classId");
		BookClass bookClass = adminService.viewBookClassInfo(classid);

		request.setAttribute("classId", bookClass.getClassId());
		request.setAttribute("className", bookClass.getClassName());
		request.setAttribute("categoryId", bookClass.getCategoryId());
		request.setAttribute("listCategoryMaps", getCategoryList());
		request.getRequestDispatcher("/jsp/class/editClass.jsp").forward(
				request, response);

	}

	private void addClass(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 表单含有文件要提交
		response.setContentType("text/html;charset=utf-8");
		String className = request.getParameter("className");
		String categoryId = request.getParameter("categoryId");

		if (adminService.checkClassName(categoryId, className)) {
			setMessage(request, ConstantParams.REPEAT_CLASS_NAME_FLAG);
			request.setAttribute("className", className);
			request.setAttribute("categoryId", categoryId);
			request.getRequestDispatcher("/jsp/class/addClass.jsp").forward(
					request, response);
		} else {
			boolean flag = adminService.addBookClass(className, categoryId);
			if (flag) {
				setMessage(request, ConstantParams.ADD_SUCCESS_FLAG);
			} else {
				setMessage(request, ConstantParams.ADD_FAIL_FLAG);
			}
			request.getRequestDispatcher("/AdminAction?type=listClass")
					.forward(request, response);
		}
	}

	private void initAddClass(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获得所有 已有目录的列表getCategoryList();
		request.getSession()
				.setAttribute("listCategoryMaps", getCategoryList());
		request.getRequestDispatcher("/jsp/class/addClass.jsp").forward(
				request, response);

	}

	private void listCategory(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 验证用户是否填写了特定的页数来显示
		String pageIndex = request.getParameter("pageIndex");

		// 接收用户的查询名字
		String keywords = request.getParameter("keywords");
		int recordCount = adminService.getCategorySearchItemCount(keywords);// 获得记录的总条数
		int currentPage = 1;// 当前页是第一页
		String pageNum = request.getParameter("pageNum");
		if (keywords == null || "".equals(keywords)) {
			if (pageNum != null && !"".equals(pageNum)) {
				currentPage = Integer.parseInt(pageNum);
			} else if (pageIndex != null && !"".equals(pageIndex)) {
				currentPage = Integer.parseInt(pageIndex);
			}
		}

		DividePage pUtil = new DividePage(ConstantParams.PAGE_SIZE,
				recordCount, currentPage);
		int start = pUtil.getFromIndex();
		int end = pUtil.getToIndex();
		List<BookCategory> list = adminService.listCategorys(keywords, start,
				end);
		request.setAttribute("pUtil", pUtil);
		request.setAttribute("totalPages", (pUtil.getRecordCount()
				+ ConstantParams.PAGE_SIZE - 1)
				/ ConstantParams.PAGE_SIZE);
		request.setAttribute("listCategory", list);
		request.setAttribute("keywords", keywords);
		request.getRequestDispatcher("/jsp/category/listCategory.jsp").forward(
				request, response);

	}

	private void deleteCategory(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获得选中的复选框的值
		String categoryId = request.getParameter("categoryId");
		boolean flag = adminService.deleteCategory(categoryId);
		if (flag) {
			setMessage(request, ConstantParams.DELETE_SUCCESS_FLAG);
		} else {
			setMessage(request, ConstantParams.DELETE_FAIL_FLAG);
		}
		request.getRequestDispatcher("/AdminAction?type=listCategory").forward(
				request, response);

	}

	private void editCategory(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获得修改后的变量的值
		String categoryid = request.getParameter("categoryId");
		String categoryName = request.getParameter("categoryName");

		BookCategory category = adminService.editCategory(categoryid,
				categoryName);
		request.setAttribute("categoryId", categoryid);
		request.setAttribute("categoryName", categoryName);
		// 通过level的值来查询map中的值
		if (category != null && !"".equals(category)) {
			setMessage(request, ConstantParams.EDIT_SUCCESS_FLAG);
		} else {
			setMessage(request, ConstantParams.EDIT_FAIL_FLAG);
		}

		request.getRequestDispatcher("/jsp/category/editCategory.jsp").forward(
				request, response);

	}

	private void viewCategory(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String categoryid = request.getParameter("categoryId");
		BookCategory category = adminService.viewCategoryInfo(categoryid);
		request.setAttribute("categoryId", category.getCategoryId());
		request.setAttribute("categoryName", category.getCategoryName());
		// 通过level的值来查询map中的值
		request.getRequestDispatcher("/jsp/category/editCategory.jsp").forward(
				request, response);

	}

	private void addCategory(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 表单含有文件要提交
		response.setContentType("text/html;charset=utf-8");
		String categoryName = request.getParameter("categoryName");

		boolean flag = adminService.addCategory(categoryName);
		if (flag) {
			setMessage(request, ConstantParams.ADD_SUCCESS_FLAG);
		} else {
			setMessage(request, ConstantParams.ADD_FAIL_FLAG);
		}

		request.getRequestDispatcher("/AdminAction?type=listCategory").forward(
				request, response);
	}

	// User start
	private void listOperator(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 显示某一用户的所有历史记录
		String userId = request.getParameter("userId");
		List<OperatorLog> listOperator = adminService.listOperator(userId);
		request.setAttribute("listOperator", listOperator);
		request.setAttribute("operatorUserId", userId);
		request.getRequestDispatcher("/jsp/user/listUserOperator.jsp").forward(
				request, response);
	}

	private void listFavorite(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 显示某一用户的收藏文档列表
		String userId = request.getParameter("userId");
		List<FavouriteBook> listFavorite = adminService
				.listFavoriteBook(userId);
		request.setAttribute("listFavorite", listFavorite);
		request.getRequestDispatcher("/jsp/user/listUserFavorite.jsp").forward(
				request, response);
	}

	// 增加新用户的时候的方法
	private void addUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 表单含有文件要提交
		String path = request.getContextPath();
		response.setContentType("text/html;charset=utf-8");
		String userName = request.getParameter("userName");
		String pswd = request.getParameter("password");
		String telephone = request.getParameter("telephone");
		List<Object> params = new ArrayList<Object>();
		params.add(userName);// 用户名
		params.add(pswd);// 密码
		params.add(telephone);// 电话号码

		if (adminService.checkTelephone(telephone)) {
			setMessage(request, ConstantParams.REPEAT_TELEPHONE_FLAG);
			request.setAttribute("userName", userName);
			request.setAttribute("password", pswd);
			request.setAttribute("password1", pswd);
			request.setAttribute("telephone", telephone);
			request.getRequestDispatcher("/jsp/user/addUser.jsp").forward(
					request, response);
		} else {
			boolean flag = adminService.addUser(params);
			if (flag) {
				setMessage(request, ConstantParams.ADD_SUCCESS_FLAG);
				request.getRequestDispatcher("/AdminAction?type=listUser")
						.forward(request, response);
			} else {
				setMessage(request, ConstantParams.ADD_FAIL_FLAG);
				request.getRequestDispatcher("/AdminAction?type=listUser")
						.forward(request, response);
			}
		}

	}

	private void viewUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid = request.getParameter("userId");
		Users user = adminService.viewUserInfo(userid);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/jsp/user/editUser.jsp").forward(request,
				response);
	}

	private void editUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获得修改后的变量的值
		String userid = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String telephone = request.getParameter("telephone");
		String isAvailable = request.getParameter("isAvailable");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", userName);
		params.put("password", password);
		params.put("telephone", telephone);
		params.put("isAvailable", isAvailable);
		Users user = adminService.editUser(userid, params);
		request.setAttribute("user", user);
		setMessage(request, ConstantParams.EDIT_SUCCESS_FLAG);
		request.getRequestDispatcher("/jsp/user/editUser.jsp").forward(request,
				response);
	}

	private void deleteUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getContextPath();
		// 获得选中的复选框的值
		String userId = request.getParameter("userId");
		boolean flag = adminService.deleteUser(userId);
		String rs = null;
		if (flag) {
			rs = ConstantParams.DELETE_SUCCESS_FLAG;
		} else {
			rs = ConstantParams.DELETE_FAIL_FLAG;
		}

		response.sendRedirect(path + "/AdminAction?type=listUser&respMsg=" + rs);
	}

	// 查询用户的列表的时候显示
	private void listUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 验证用户是否填写了特定的页数来显示
		String pageIndex = request.getParameter("pageIndex");

		// 接收用户的查询名字
		String keywords = request.getParameter("keywords");
		String tempAvailable = request.getParameter("isAvailable");
		int recordCount = adminService.getListUserItemCount(keywords,
				tempAvailable);// 获得记录的总条数
		int currentPage = 1;// 当前页是第一页
		String pageNum = request.getParameter("pageNum");

		if (pageNum != null && !"".equals(pageNum)) {
			currentPage = Integer.parseInt(pageNum);
		} else if (pageIndex != null && !"".equals(pageIndex)) {
			currentPage = Integer.parseInt(pageIndex);
		}

		DividePage pUtil = new DividePage(ConstantParams.PAGE_SIZE,
				recordCount, currentPage);
		int start = pUtil.getFromIndex();
		int end = pUtil.getToIndex();
		// 已经进行分页之后的数据集合
		List<Users> list = adminService.listUsers(keywords, tempAvailable,
				start, end);
		request.setAttribute("pUtil", pUtil);
		request.setAttribute("totalPages", (pUtil.getRecordCount()
				+ ConstantParams.PAGE_SIZE - 1)
				/ ConstantParams.PAGE_SIZE);
		request.setAttribute("listUser", list);
		request.setAttribute("startNo", start + 1);
		request.setAttribute("keywords", keywords);
		request.setAttribute("isAvailable", tempAvailable);
		setMessage(request, request.getParameter(ConstantParams.RETURN_MSG));
		request.getRequestDispatcher("/jsp/user/listUser.jsp").forward(request,
				response);
	}

	// User end
	public List<Map<String, String>> getCategoryList() {
		return adminService.getAllCategoryList();
	}

	public List<Map<String, String>> getClassMap(String categoryId) {
		return adminService.getClassMap(categoryId);
	}

	public List<Map<String, String>> getBookMap(String classId) {
		return adminService.getBookMap(classId);
	}

	private String getFileName() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		return dateFormat.format(new Date());
	}

}
