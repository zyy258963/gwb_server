package com.gwb.lee.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gwb.lee.service.AndroidService;
import com.gwb.lee.util.ConstantParams;
import com.gwb.lee.util.JdbcUtils;
import com.gwb.lee.vo.Ad;
import com.gwb.lee.vo.BookAppClass;
import com.gwb.lee.vo.BookCategory;
import com.gwb.lee.vo.Books;
import com.gwb.lee.vo.FavouriteBook;
import com.gwb.lee.vo.Users;

public class AndroidDao implements AndroidService {

	private JdbcUtils jdbcUtils = null;

	public AndroidDao() {
		jdbcUtils = new JdbcUtils();
	}

	@Override
	public Map<String, Object> login(String telephone, String password,
			String macAddress) {
		// TODO Auto-generated method stub
		Map<String, Object> map = null;
//		String sql = "select * from users where telephone=? and password=? ";
//		List<Object> params = new ArrayList<Object>();
//		params.add(telephone);
//		params.add(password);
		
//新需求   不在检查密码
		String sql = "select * from users where telephone=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(telephone);
//		params.add(password);
		
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, params);
			// 登陆成功 则将mac地址插入数据库中
			if (!map.isEmpty()) {
				if (map.get("macAddress") != null
						&& !"".equals(map.get("macAddress"))) {

				} else {
					String updateSql = " update users set macAddress=? where userId=?";
					List<Object> para = new ArrayList<Object>();
					para.add(macAddress);
					para.add(map.get("userId"));

					boolean flag = jdbcUtils.updateByPreparedStatement(
							updateSql, para);

					if (!flag) {
						return null;
					} else {
						map.put("macAddress", macAddress);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return map;
	}
	
	@Override
	public Map<String, Object> appLogin(String telephone, String macAddress) {
		// TODO Auto-generated method stub
		Map<String, Object> map = null;
		String sql = "select * from users where telephone=?  ";
		List<Object> params = new ArrayList<Object>();
		params.add(telephone);
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, params);
			// 登陆成功 则将mac地址插入数据库中
			if (!map.isEmpty()) {
//				如果 该手机号的 mac地址不为空并且与请求参数一致，返回正确
				if (map.get("macAddress") != null
						&& !"".equals(map.get("macAddress")) && map.get("macAddress").equals(macAddress)) {
					return map;
				} else if("0".equals(map.get("macAddress"))){
					String updateSql = " update users set macAddress=? where userId=?";
					List<Object> para = new ArrayList<Object>();
					para.add(macAddress);
					para.add(map.get("userId"));

					boolean flag = jdbcUtils.updateByPreparedStatement(
							updateSql, para);

					if (!flag) {
						map.clear();
						map.put("msg", "updateerror");
						return map;
					} else {
						map.put("macAddress", macAddress);
						return map;
					}
				}else {
					map.clear();
					map.put("msg", "nomatch");
					return map;
				}
			}else {
//				手机号为空，表明没有交费
				map = new HashMap<String, Object>();
				map.put("msg", "notelephone");
				return map;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return null;
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
	public List<BookCategory> getAllCategoryList() {
		List<BookCategory> list = new ArrayList<BookCategory>();
		String selectTopMenu = "select * from bookcategory bc order by bc.categoryId ";
		try {
			jdbcUtils.getConnection();

			list = jdbcUtils.findMoreRefResult(selectTopMenu, null,
					BookCategory.class);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getCategoryList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String selectTopMenu = "select bc.categoryId,bc.categoryName from bookcategory bc order by bc.categoryId ";
		try {
			jdbcUtils.getConnection();

			ResultSet subRs = jdbcUtils.findResultSet(selectTopMenu, null);
			Map<String, Object> map = new LinkedHashMap<String, Object>();
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
	public List<Map<String, Object>> getClassMap(String categoryId) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String selectTopMenu = "select bc.classId,bc.className from bookclass bc where bc.categoryId= "
				+ Integer.parseInt(categoryId);
		try {
			jdbcUtils.getConnection();
			// 获得所有的顶级菜单。
			ResultSet topRs = jdbcUtils.findResultSet(selectTopMenu, null);
			Map<String, Object> map = new HashMap<String, Object>();
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
	public List<BookAppClass> getClassByCategory(String categoryId) {
		List<BookAppClass> list = new ArrayList<BookAppClass>();
		String sql = "select * from bookclass where categoryId= "
				+ Integer.parseInt(categoryId);
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreRefResult(sql, null, BookAppClass.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	@Override
	public List<Books> getBookByClass(String classId) {
		List<Books> list = new ArrayList<Books>();
		String sql = "select * from books where classId= "
				+ Integer.parseInt(classId);
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreRefResult(sql, null, Books.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	@Override
	public List<Books> listBook(String classId, String keywords, int start,
			int end) {
		List<Books> list = new ArrayList<Books>();
		String sql = "select b.*,bc.categoryName,bcls.className from books b,bookcategory bc,bookclass bcls where"
				+ " b.categoryId=bc.categoryId and b.classId=bcls.classId ";
		// limit ?,
		StringBuffer buffer = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();
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
	public List<Books> listBook(String classId) {
		List<Books> list = new ArrayList<Books>();
		String sql = "select b.*,bc.categoryName,bcls.className from books b,bookcategory bc,bookclass bcls where"
				+ " b.categoryId=bc.categoryId and b.classId=bcls.classId ";
		// limit ?,
		StringBuffer buffer = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();
		if (classId != null && !"".equals(classId)) {
			buffer.append(" and b.classId=? ");
			params.add(Integer.parseInt(classId));
		}
		buffer.append(" order by b.categoryId,b.classId,b.bookId ");
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
	public List<Books> searchBook(String keywords, int start, int end) {
		List<Books> list = new ArrayList<Books>();
		String sql = "select b.*,bc.categoryName,bcls.className from books b,bookcategory bc,bookclass bcls where"
				+ " b.categoryId=bc.categoryId and b.classId=bcls.classId ";
		// limit ?,
		StringBuffer buffer = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();
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
	public List<Books> searchBook(String keywords) {
		List<Books> list = new ArrayList<Books>();
		String sql = "select b.*,bc.categoryName,bcls.className from books b,bookcategory bc,bookclass bcls where"
				+ " b.categoryId=bc.categoryId and b.classId=bcls.classId ";
		// limit ?,
		StringBuffer buffer = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();
		if (keywords != null && !"".equals(keywords)) {
			buffer.append(" and b.bookName like ? ");
			params.add("%" + keywords + "%");
		}
		buffer.append(" order by b.categoryId,b.classId,b.bookId ");
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
	public List<FavouriteBook> listFavouritebook(String userId) {
		List<FavouriteBook> list = new ArrayList<FavouriteBook>();
		String sql = "select fb.favouriteId,u.userId,u.userName,bs.bookId,bs.bookName,bs.bookUrl,fb.createTs from books bs,users u,favouritebook fb where u.isAvailable=1 and u.userId=fb.userId and fb.bookId = bs.bookId ";
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
	public String addFavouritebook(String userId, String bookId) {
		boolean flag = false;

		// 检查之前是否已经将这个文档加入到 收藏列表
		String selectSql = "select * from favouritebook fb where fb.userId="
				+ Integer.parseInt(userId) + " and fb.bookId="
				+ Integer.parseInt(bookId);
		try {
			jdbcUtils.getConnection();
			List<FavouriteBook> tempList = jdbcUtils.findMoreRefResult(
					selectSql, null, FavouriteBook.class);
			if (tempList != null && !"".equals(tempList) && tempList.size() > 0) {
				return ConstantParams.HTTP_SUCCESS;
			}

			String insertSql = "insert into favouritebook(userId,bookId,createTs) values(?,?,?)";
			List<Object> params = new ArrayList<Object>();
			params.add(Integer.parseInt(userId));
			params.add(Integer.parseInt(bookId));
			params.add(new Date());

			// jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(insertSql, params);
			System.out.println("---add favorite book-->>>" + flag);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag ? ConstantParams.HTTP_SUCCESS : ConstantParams.HTTP_FAIL;
	}

	@Override
	public List<FavouriteBook> deleteFavouritebook(String telephone, String favouriteId) {
		List<FavouriteBook> list = null;
		// 先删除数据
		String deleteSql = "delete from favouritebook where favouriteId="
				+ Integer.parseInt(favouriteId);
		try {
			jdbcUtils.getConnection();
			
			if (jdbcUtils.updateByPreparedStatement(deleteSql, null)) {
				list = new ArrayList<FavouriteBook>();
				String sql = "select fb.favouriteId,u.userId,u.userName,bs.bookId,bs.bookName,bs.bookUrl,fb.createTs from books bs,users u,favouritebook fb where u.isAvailable=1 and u.userId=fb.userId and fb.bookId = bs.bookId ";
				List<Object> param = new ArrayList<Object>();
				if (telephone != null && !"".equals(telephone)) {
					sql += " and fb.telephone = ? ";
					param.add(telephone);
				}
				sql += " order by fb.createTs";

				list = jdbcUtils.findMoreRefResult(sql, param, FavouriteBook.class);
			}else {
				return null;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	@Override
	public List<Ad> listAd(String adPosition) {
		String sql  = null;
		if (adPosition != null && !"".equals(adPosition)) {
			sql = "select * from ad where adPosition= "
				+ Integer.parseInt(adPosition);
		}else {
			sql = "select * from ad ";
		}
		List<Ad> list = null;
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
	
	/**
	 * 过滤用户 电话和mac
	 * 
	 * 如果 匹配成功，返回1
	 * 如果 匹配不成功，返回 2 
	 * 
	 * */
	@Override
	public int filterUser(Object telephone, Object macAddress) {
		String sql = "select * from users where isAvailable=1 and telephone=? and macAddress=? and macAddress!='0' ";
		List<Object> params = new ArrayList<Object>();
		params.add(telephone.toString());
		params.add(macAddress.toString());
		try {
			jdbcUtils.getConnection();
		    Map<String, Object>	map = jdbcUtils.findSimpleResult(sql, params);
		    if (map!=null && !"".equals(map) && !map.isEmpty()) {
				return ConstantParams.STATUS_FILTER_SUCCESS;
			}else {
				return ConstantParams.STATUS_FILTER_NO_MACADDRESS;
			}
		    
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return ConstantParams.STATUS_FILTER_ERROR;
	}

//	@Override
//	public Map<String, Object> appLogin(String telephone, String macAddress) {
//		// TODO Auto-generated method stub
//		Map<String, Object> map = null;
//		String sql = "select * from users where isAvailable=1 and telephone=?  ";
//		List<Object> params = new ArrayList<Object>();
//		params.add(telephone);
//		try {
//			jdbcUtils.getConnection();
//			map = jdbcUtils.findSimpleResult(sql, params);
//			// 登陆成功 则将mac地址插入数据库中
//			if (!map.isEmpty()) {
////				如果 该手机号的 mac地址不为空并且与请求参数一致，返回正确
//				if (map.get("macAddress") != null
//						&& !"".equals(map.get("macAddress")) && map.get("macAddress").equals(macAddress)) {
//					return map;
//				} else if("0".equals(map.get("macAddress"))){
//					String updateSql = " update users set macAddress=? where userId=?";
//					List<Object> para = new ArrayList<Object>();
//					para.add(macAddress);
//					para.add(map.get("userId"));
//
//					boolean flag = jdbcUtils.updateByPreparedStatement(
//							updateSql, para);
//
//					if (!flag) {
//						return null;
//					} else {
//						map.put("macAddress", macAddress);
//						return map;
//					}
//				}else {
//					return null;
//				}
//			}else {
////				手机号为空，表明没有交费
//				return null;
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} finally {
//			jdbcUtils.releaseConn();
//		}
//		return null;
//	}

	@Override
	public void log(String telephone, String logType, String logInfo) {
		
		// 检查之前是否已经将这个文档加入到 收藏列表
		String sql = "insert into operatorlog(telephone,logType,logInfo,logTs) values(?,?,?,?) ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(telephone);
		params.add(logType);
		params.add(logInfo);
		params.add(new Date());
				
		try {
			jdbcUtils.getConnection();
			jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
	}
	
	


}
