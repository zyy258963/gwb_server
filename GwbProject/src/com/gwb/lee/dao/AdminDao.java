package com.gwb.lee.dao;

import java.awt.print.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gwb.lee.service.AdminService;
import com.gwb.lee.util.JdbcUtils;
import com.gwb.lee.vo.Ad;
import com.gwb.lee.vo.BookCategory;
import com.gwb.lee.vo.BookClass;
import com.gwb.lee.vo.Books;
import com.gwb.lee.vo.FavouriteBook;
import com.gwb.lee.vo.OperatorLog;
import com.gwb.lee.vo.Users;

public class AdminDao implements AdminService {

	private JdbcUtils jdbcUtils = null;

	public AdminDao() {
		jdbcUtils = new JdbcUtils();
	}

	@Override
	public boolean login(String userName, String password) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql = "select * from admin where userName=? and password=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(userName);
		params.add(password);
		try {
			jdbcUtils.getConnection();
			Map<String, Object> map = jdbcUtils.findSimpleResult(sql, params);
			flag = map.isEmpty() ? false : true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public boolean checkTelephone(String telephone) {
		boolean flag = false;
		String sql = "select * from users where telephone=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(telephone);
		try {
			jdbcUtils.getConnection();
			Map<String, Object> map = jdbcUtils.findSimpleResult(sql, params);
			flag = map.isEmpty() ? false : true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	@Override
	public boolean checkClassName(String categoryId, String className) {
		boolean flag = false;
		String sql = "select * from bookclass where className=? and categoryId=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(className);
		params.add(Integer.parseInt(categoryId));
		try {
			jdbcUtils.getConnection();
			Map<String, Object> map = jdbcUtils.findSimpleResult(sql, params);
			flag = map.isEmpty() ? false : true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;

	}
	
	@Override
	public boolean checkBookName(String categoryId, String classId,
			String bookName) {
		boolean flag = false;
		String sql = "select * from books where categoryId=? and classId=? and bookName=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(Integer.parseInt(categoryId));
		params.add(Integer.parseInt(classId));
		params.add(bookName);
		try {
			jdbcUtils.getConnection();
			Map<String, Object> map = jdbcUtils.findSimpleResult(sql, params);
			flag = map.isEmpty() ? false : true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	@Override
	public boolean addUser(List<Object> params) {
		boolean flag = false;
		String sql = "insert into users(userName,password,telephone) values(?,?,?)";
		try {
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
			System.out.println("---addUser-->>>" + flag);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gwb.lee.service.UserService#getSearchItemCount(java.lang.String)
	 */
	@Override
	public int getListUserItemCount(String keywords, String isAvailable) {
		int result = 0;
		Map<String, Object> map = null;
		StringBuffer buffer = new StringBuffer(
				" select count(*) mycount from users where  ");
		List<Object> params = new ArrayList<Object>();
		if (isAvailable != null && !"".equals(isAvailable)
				&& "0".equals(isAvailable)) {
			buffer.append(" isAvailable = ? ");
			params.add(0);
		} else {
			buffer.append(" isAvailable = ? ");
			params.add(1);
		}
		if (keywords != null && !"".equals(keywords)) {
			buffer.append(" and userName like ? ");
			params.add("%" + keywords + "%");
		}
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(buffer.toString(), params);
			result = Integer.parseInt(map.get("mycount").toString());
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public List<Users> listUsers(String keywords, String isAvailable,
			int start, int end) {
		List<Users> list = new ArrayList<Users>();
		String sql = "select * from users where ";
		// limit ?,?
		StringBuffer buffer = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();
		if (isAvailable != null && !"".equals(isAvailable)
				&& "0".equals(isAvailable)) {
			buffer.append(" isAvailable = ? ");
			params.add(0);
		} else {
			buffer.append(" isAvailable = ? ");
			params.add(1);
		}
		if (keywords != null && !"".equals(keywords)) {
			buffer.append(" and userName like ? ");
			params.add("%" + keywords + "%");
		}
		buffer.append(" limit ?,? ");
		params.add(start);
		params.add(end);
		try {
			jdbcUtils.getConnection();
			List<Map<String, Object>> temp = jdbcUtils.findMoreResult(
					buffer.toString(), params);
			for (Map<String, Object> map : temp) {
				Users users = new Users();
				users.setUserId(Integer.parseInt(map.get("userId").toString()));
				users.setUserName(map.get("userName").toString());
				users.setPassword(map.get("password").toString());
				users.setTelephone(map.get("telephone").toString());
				users.setMacAddress(map.get("macAddress").toString());
				users.setIsAvailable(Integer.parseInt(map.get("isAvailable")
						.toString()));
				list.add(users);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	@Override
	public Users editUser(String userid, Map<String, Object> paramMap) {
		StringBuffer sqlBuffer = new StringBuffer("update users u set  ");
		List<Object> params = new ArrayList<Object>();
		// paramMap 中数据格式为： userName,password,telephone

		if (paramMap.get("userName") != null
				&& !"".equals(paramMap.get("userName"))) {
			sqlBuffer.append(" u.userName=?, ");
			params.add(paramMap.get("userName"));
		}

		if (paramMap.get("password") != null
				&& !"".equals(paramMap.get("password"))) {
			sqlBuffer.append(" u.password=?, ");
			params.add(paramMap.get("password"));
		}

		if (paramMap.get("telephone") != null
				&& !"".equals(paramMap.get("telephone"))) {
			sqlBuffer.append(" u.telephone=? ,");
			params.add(paramMap.get("telephone"));
		}
		if ("0".equals(paramMap.get("isAvailable"))) {
			sqlBuffer.append(" u.isAvailable=0 ,");
		} else {
			sqlBuffer.append(" u.isAvailable=1 ,");
		}
		sqlBuffer = new StringBuffer(sqlBuffer.substring(0,
				sqlBuffer.length() - 1));
		sqlBuffer.append(" where u.userId = ? ");
		params.add(Integer.parseInt(userid));
		System.out.println("------->>" + sqlBuffer.toString());

		try {
			jdbcUtils.getConnection();
			boolean flag = jdbcUtils.updateByPreparedStatement(
					sqlBuffer.toString(), params);
			if (flag) {
				String sql = "select * from users u where u.userId = "
						+ Integer.parseInt(userid);
				return jdbcUtils.findSimpleRefResult(sql, null, Users.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return null;
	}

	@Override
	public boolean unbindUser(String userid) {
		boolean flag  = false;
		String sql = "update users set macAddress='0' where userId="
				+ Integer.parseInt(userid);
		try {
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public Users viewUserInfo(String userId) {
		String sql = "select * from users u where u.userId=?";
		List<Object> params = new ArrayList<Object>();
		params.add(Integer.parseInt(userId));
		Users user = null;
		try {
			jdbcUtils.getConnection();
			user = jdbcUtils.findSimpleRefResult(sql, params, Users.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return user;
	}

//	@Override
//	public boolean deleteUser(String userId) {
//		boolean flag = false;
//		String sql = "update users u set u.isAvailable=?  where u.userId=? ";
//		List<Object> params = new ArrayList<Object>();
//		params.add(Integer.parseInt("0"));
//		params.add(Integer.parseInt(userId));
//		try {
//			jdbcUtils.getConnection();
//			flag = jdbcUtils.updateByPreparedStatement(sql, params);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			jdbcUtils.releaseConn();
//		}
//		return flag;
//	}
	
	public boolean deleteManyUser(String userIds) {
		if (userIds!=null && !"".equals(userIds)) {
			boolean flag = false;
			String sql = "delete from users where userId in ("+userIds+")";
			
			try {
				jdbcUtils.getConnection();
				flag = jdbcUtils.updateByPreparedStatement(sql.toString(), null);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				jdbcUtils.releaseConn();
			}
			return flag;
		}else {
			return false;
		}
	}
	
	public boolean deleteManyUserOperator(String userIds) {
		if (userIds!=null && !"".equals(userIds)) {
			boolean flag = false;
			String sql = "delete from operatorlog where logId in ("+userIds+")";
			
			try {
				jdbcUtils.getConnection();
				flag = jdbcUtils.updateByPreparedStatement(sql.toString(), null);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				jdbcUtils.releaseConn();
			}
			return flag;
		}else {
			return false;
		}
	}
	
	@Override
	public boolean deleteManyBook(String bookIds) {
		if (bookIds!=null && !"".equals(bookIds)) {
			boolean flag = false;
			String sql = "delete from books where bookId in ("+bookIds+")";
			
			try {
				jdbcUtils.getConnection();
				flag = jdbcUtils.updateByPreparedStatement(sql.toString(), null);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				jdbcUtils.releaseConn();
			}
			return flag;
		}else {
			return false;
		}
	}
	
	
	@Override
	public boolean deleteUser(String userId) {
		boolean flag = false;
		String sql = "delete from users where userId=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(Integer.parseInt(userId));
		try {
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public List<OperatorLog> listOperator(String userId) {
		// his.logId = logId;
		// this.userId = userId;
		// this.userName = userName;
		// this.logType = logType;
		// this.logInfo = logInfo;
		// this.logTs = logTs;
		List<OperatorLog> list = new ArrayList<OperatorLog>();
		String sql = "select ol.logId logId,u.userName userName,u.telephone telephone,ol.logType logType,ol.logInfo logInfo,ol.logTs logTs  from operatorlog ol,users u  where u.telephone=ol.telephone ";
		List<Object> param = new ArrayList<Object>();
		if (userId != null && !"".equals(userId)) {
			sql += " and u.userId = ? ";
			param.add(Integer.parseInt(userId));
		}
		sql += " order by ol.logTs desc";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreRefResult(sql, param, OperatorLog.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	@Override
	public List<FavouriteBook> listFavoriteBook(String userId) {
		List<FavouriteBook> list = new ArrayList<FavouriteBook>();
		String sql = "select fb.favouriteId,u.userId,u.userName,bs.bookId,bs.bookName,fb.createTs from books bs,users u,favouritebook fb where u.userId=fb.userId and fb.bookId = bs.bookId ";
		List<Object> param = new ArrayList<Object>();
		if (userId != null && !"".equals(userId)) {
			sql += " and fb.userId = ? ";
			param.add(userId);
		}
		sql += " order by fb.createTs";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreRefResult(sql, param, FavouriteBook.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	@Override
	public boolean addCategory(String categoryName) {
		boolean flag = false;
		String sql = "insert into bookcategory(categoryName) values(?)";
		List<Object> params = new ArrayList<Object>();
		params.add(categoryName);
		try {
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public int getClassSearchItemCount(String categoryId, String keywords) {
		int result = 0;
		Map<String, Object> map = null;
		StringBuffer buffer = new StringBuffer(
				" select count(*) mycount from bookclass where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (keywords != null && !"".equals(keywords)) {
			buffer.append(" and className like ? ");
			params.add("%" + keywords + "%");
		}
		if (categoryId != null && !"".equals(categoryId)) {
			buffer.append(" and categoryId=? ");
			params.add(Integer.parseInt(categoryId));
		}
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(buffer.toString(), params);
			result = Integer.parseInt(map.get("mycount").toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public List<BookClass> listBookClass(String categoryId, String keywords,
			int start, int end) {
		List<BookClass> list = new ArrayList<BookClass>();
		String sql = "select bc.*,bca.categoryName from bookclass bc,bookcategory bca where bc.categoryId=bca.categoryId ";
		// limit ?,
		StringBuffer buffer = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();
		if (keywords != null && !"".equals(keywords)) {
			buffer.append(" and bc.className like ? ");
			params.add("%" + keywords + "%");
		}
		if (categoryId != null && !"".equals(categoryId)) {
			buffer.append(" and bc.categoryId=? ");
			params.add(Integer.parseInt(categoryId));
		}
		buffer.append(" limit ?,? ");
		params.add(start);
		params.add(end);
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreRefResult(buffer.toString(), params,
					BookClass.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	@Override
	public BookCategory editCategory(String categoryid, String categoryName) {
		StringBuffer sqlBuffer = new StringBuffer(
				"update bookcategory b set categoryName=? where categoryId=?  ");
		List<Object> params = new ArrayList<Object>();
		params.add(categoryName);
		params.add(Integer.parseInt(categoryid));
		try {
			jdbcUtils.getConnection();
			boolean flag = jdbcUtils.updateByPreparedStatement(
					sqlBuffer.toString(), params);
			if (flag) {
				String sql = "select * from bookcategory b where b.categoryId = "
						+ Integer.parseInt(categoryid);
				return jdbcUtils.findSimpleRefResult(sql, null,
						BookCategory.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return null;
	}

	@Override
	public boolean deleteBookClass(String classId) {
		boolean flag = false;
		String sql = "delete from bookclass where classId="
				+ Integer.parseInt(classId);
		try {
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public boolean editBookClass(String classid, String className,
			String categoryId) {
		boolean flag = false;
		StringBuffer sqlBuffer = new StringBuffer(
				"update bookclass b set className=?,categoryId=? where classId=?  ");
		List<Object> params = new ArrayList<Object>();
		params.add(className);
		params.add(Integer.parseInt(categoryId));
		params.add(Integer.parseInt(classid));
		try {
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sqlBuffer.toString(),
					params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public BookClass viewBookClassInfo(String classid) {
		BookClass bookClass = null;
		String sql = "select bc.*,bca.categoryName from bookclass bc,bookcategory bca where bc.categoryId=bca.categoryId and bc.classId="
				+ Integer.parseInt(classid);
		try {
			jdbcUtils.getConnection();
			bookClass = jdbcUtils.findSimpleRefResult(sql, null,
					BookClass.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return bookClass;
	}

	@Override
	public BookCategory viewCategoryInfo(String categoryid) {
		String sql = "select * from bookcategory where categoryId=?";
		List<Object> params = new ArrayList<Object>();
		params.add(Integer.parseInt(categoryid));
		BookCategory category = null;
		try {
			jdbcUtils.getConnection();
			category = jdbcUtils.findSimpleRefResult(sql, params,
					BookCategory.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return category;
	}

	@Override
	public boolean addBookClass(String className, String categoryId) {
		boolean flag = false;
		String sql = "insert into bookclass(className,categoryId) values(?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(className);
		params.add(Integer.parseInt(categoryId));
		try {
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
			System.out.println("---addUser-->>>" + flag);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public int getCategorySearchItemCount(String keywords) {
		int result = 0;
		Map<String, Object> map = null;
		StringBuffer buffer = new StringBuffer(
				" select count(*) mycount from bookcategory where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (keywords != null && !"".equals(keywords)) {
			buffer.append(" and categoryName like ? ");
			params.add("%" + keywords + "%");
		}
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(buffer.toString(), params);
			result = Integer.parseInt(map.get("mycount").toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public List<BookCategory> listCategorys(String keywords, int start, int end) {
		List<BookCategory> list = new ArrayList<BookCategory>();
		String sql = "select * from bookcategory where 1=1 ";
		// limit ?,
		StringBuffer buffer = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();
		if (keywords != null) {
			buffer.append(" and categoryName like ? ");
			params.add("%" + keywords + "%");
		}
		buffer.append(" limit ?,? ");
		params.add(start);
		params.add(end);
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreRefResult(buffer.toString(), params,
					BookCategory.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	@Override
	public boolean deleteCategory(String categoryId) {
		boolean flag = false;
		String sql = "delete from bookcategory where categoryId="
				+ Integer.parseInt(categoryId);
		try {
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public boolean changePwd(String userName, String password) {
		boolean flag = false;
		String sql = "update admin set password=? where userName=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(password);
		params.add(userName);
		try {
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public List<Map<String, String>> getAllCategoryList() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String selectTopMenu = "select bc.categoryId,bc.categoryName from bookcategory bc order by bc.categoryId ";
		try {
			jdbcUtils.getConnection();

			ResultSet subRs = jdbcUtils.findResultSet(selectTopMenu, null);
			Map<String, String> map = new LinkedHashMap<String, String>();
			while (subRs.next()) {
				map.put(subRs.getString("categoryId"),
						subRs.getString("categoryName"));
			}
			list.add(map);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	/***
	 * 
	 * 获得某一行业下面的专业类型
	 * 
	 * 
	 */
	@Override
	public List<Map<String, String>> getClassMap(String categoryId) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String selectTopMenu = "select bc.classId,bc.className from bookclass bc where bc.categoryId= "
				+ Integer.parseInt(categoryId);
		try {
			jdbcUtils.getConnection();
			// 获得所有的顶级菜单。
			ResultSet topRs = jdbcUtils.findResultSet(selectTopMenu, null);
			Map<String, String> map = new HashMap<String, String>();
			while (topRs.next()) {
				map.put(topRs.getString("classId"),
						topRs.getString("className"));
			}
			list.add(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	@Override
	public List<Map<String, String>> getBookMap(String classId) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String selectTopMenu = "select bc.bookId,bc.bookName from books bc where bc.classId= "
				+ Integer.parseInt(classId);
		try {
			jdbcUtils.getConnection();
			// 获得所有的顶级菜单。
			ResultSet topRs = jdbcUtils.findResultSet(selectTopMenu, null);
			while (topRs.next()) {
				Map<String, String> map = new HashMap<String, String>();
				map.put(topRs.getString("bookId"), topRs.getString("bookName"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	@Override
	public boolean deleteBook(String bookId) {
		boolean flag = false;
		String sql = "delete from books where bookId=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(Integer.parseInt(bookId));
		try {
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public List<Books> listBook(String categoryId, String classId,
			String keywords, int start, int end) {
		List<Books> list = new ArrayList<Books>();
		String sql = "select b.*,bc.categoryName,bcls.className from books b,bookcategory bc,bookclass bcls where"
				+ " b.categoryId=bc.categoryId and b.classId=bcls.classId ";
		// limit ?,
		StringBuffer buffer = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();
		if (categoryId != null && !"".equals(categoryId)) {
			buffer.append(" and b.categoryId=? ");
			params.add(Integer.parseInt(categoryId));
		}
		if (classId != null && !"".equals(classId)) {
			buffer.append(" and b.classId=? ");
			params.add(Integer.parseInt(classId));
		}
		if (keywords != null && !"".equals(keywords)) {
			buffer.append(" and b.bookName like ? ");
			params.add("%" + keywords + "%");
		}
		buffer.append(" order by b.categoryId,b.classId,b.bookId limit ?,? ");
		params.add(start);
		params.add(end);
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreRefResult(buffer.toString(), params,
					Books.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	@Override
	public int getBookSearchItemCount(String categoryId, String classId,
			String keywords) {
		int result = 0;
		Map<String, Object> map = null;
		StringBuffer sql = new StringBuffer(
				" select count(*) mycount from books b,bookcategory bc,bookclass bcls where b.categoryId=bc.categoryId and b.classId=bcls.classId ");
		List<Object> params = new ArrayList<Object>();
		if (categoryId != null && !"".equals(categoryId)) {
			sql.append(" and b.categoryId=? ");
			params.add(Integer.parseInt(categoryId));
		}
		if (classId != null && !"".equals(classId)) {
			sql.append(" and b.classId=? ");
			params.add(Integer.parseInt(classId));
		}
		if (keywords != null && !"".equals(keywords)) {
			sql.append(" and b.bookName like ? ");
			params.add("%" + keywords + "%");
		}
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql.toString(), params);
			result = Integer.parseInt(map.get("mycount").toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return result;
	}

	@Override
	public boolean addBook(Map<String, Object> params) {
		boolean flag = false;
		String sql = "insert into books(bookName,bookDesc,categoryId,classId,bookUrl,chapterNum) values(?,?,?,?,?,?)";
		List<Object> ps = new ArrayList<Object>();
		ps.add(params.get("bookName"));
		ps.add(params.get("bookDesc"));
		ps.add(Integer.parseInt(params.get("categoryId").toString()));
		ps.add(Integer.parseInt(params.get("classId").toString()));
		ps.add(params.get("bookUrl"));
		ps.add(1);
		try {
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, ps);
			System.out.println("---addUser-->>>" + flag);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public Books viewBook(String bookId) {
		String sql = "select * from books where bookId=?";
		List<Object> params = new ArrayList<Object>();
		params.add(Integer.parseInt(bookId));
		Books book = null;
		try {
			jdbcUtils.getConnection();
			book = jdbcUtils.findSimpleRefResult(sql, params, Books.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return book;
	}

	@Override
	public boolean editBook(Map<String, Object> params) {
		boolean flag = false;
		StringBuffer sqlBuffer = new StringBuffer(
				"update books set categoryId=?,classId=?,bookName=?,bookDesc=?");

		String categoryId = String.valueOf(params.get("categoryId"));
		String classId = String.valueOf(params.get("classId"));
		String bookId = String.valueOf(params.get("bookId"));
		String bookName = String.valueOf(params.get("bookName"));
		String bookDesc = String.valueOf(params.get("bookDesc"));
		String bookUrl = String.valueOf(params.get("bookUrl"));

		List<Object> objects = new ArrayList<Object>();
		objects.add(Integer.parseInt(categoryId));
		objects.add(Integer.parseInt(classId));
		objects.add(bookName);
		objects.add(bookDesc);

		if (bookUrl != null && !"".equals(bookUrl)) {
			sqlBuffer.append(",bookUrl=? ");
			objects.add(bookUrl);
		}
		sqlBuffer.append(" where bookId=? ");
		objects.add(Integer.parseInt(bookId));

		try {
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sqlBuffer.toString(),
					objects);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public List<Ad> listAd() {
		List<Ad> list = new ArrayList<Ad>();
		String sql = "select * from ad order by adPosition,adId  ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreRefResult(sql, null, Ad.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	@Override
	public boolean addAd(Map<String, Object> params) {
		boolean flag = false;
		String sql = "insert into ad(adName,adPic,adUrl,adPosition) values(?,?,?,?)";
		List<Object> ps = new ArrayList<Object>();
		ps.add(params.get("adName"));
		ps.add(params.get("adPic"));
		ps.add(params.get("adUrl"));
		ps.add(Integer.parseInt(params.get("adPosition").toString()));
		try {
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, ps);
			System.out.println("---addUser-->>>" + flag);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public Ad viewAd(String adId) {
		String sql = "select * from ad where adId=" + Integer.parseInt(adId);
		Ad ad = null;
		try {
			jdbcUtils.getConnection();
			ad = jdbcUtils.findSimpleRefResult(sql, null, Ad.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return ad;
	}

	@Override
	public boolean deleteAd(String adId) {
		String sql = "delete from ad where adId=" + Integer.parseInt(adId);
		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
}
