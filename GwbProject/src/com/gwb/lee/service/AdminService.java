package com.gwb.lee.service;

import java.awt.print.Book;
import java.util.List;
import java.util.Map;

import com.gwb.lee.vo.Ad;
import com.gwb.lee.vo.BookCategory;
import com.gwb.lee.vo.BookClass;
import com.gwb.lee.vo.Books;
import com.gwb.lee.vo.FavouriteBook;
import com.gwb.lee.vo.OperatorLog;
import com.gwb.lee.vo.Users;

public interface AdminService {

	boolean login(String userName, String password);

	List<OperatorLog> listOperator(String userId);

	List<FavouriteBook> listFavoriteBook(String userId);

	boolean addUser(List<Object> params);

	Users editUser(String userid, Map<String, Object> params);

	boolean deleteUser(String userId);

	List<Users> listUsers(String keywords, String tempAvailable, int start,
			int end);

	int getListUserItemCount(String keywords, String tempAvailable);

	Users viewUserInfo(String userid);

	boolean addCategory(String categoryName);

	int getClassSearchItemCount(String categoryId, String keywords);

	List<BookClass> listBookClass(String categoryId, String keywords,
			int start, int end);

	boolean deleteBookClass(String classId);

	BookClass viewBookClassInfo(String classid);

	BookCategory viewCategoryInfo(String categoryid);

	int getCategorySearchItemCount(String keywords);

	List<BookCategory> listCategorys(String keywords, int start, int end);

	boolean deleteCategory(String categoryId);

	boolean changePwd(String userName, String password);

	BookCategory editCategory(String categoryid, String categoryName);

	List<Map<String, String>> getAllCategoryList();

	List<Map<String, String>> getClassMap(String categoryId);

	List<Map<String, String>> getBookMap(String classId);

	boolean addBookClass(String className, String categoryId);

	boolean editBookClass(String classid, String className, String categoryId);

	boolean deleteBook(String bookId);

	List<Books> listBook(String categoryId, String classId,
			String keywords, int start, int end);

	int getBookSearchItemCount(String categoryId, String classId,
			String keywords);

	boolean addBook(Map<String, Object> params);

	Books viewBook(String bookId);

	boolean editBook(Map<String, Object> params);

	List<Ad> listAd();

	boolean addAd(Map<String, Object> params);

	Ad viewAd(String adId);

	boolean deleteAd(String adId);

	boolean unbindUser(String userid);

	boolean checkTelephone(String telephone);

	boolean checkClassName(String categoryId, String className);

	boolean checkBookName(String categoryId, String classId, String bookName);

	boolean deleteManyUser(String userIds);

	boolean deleteManyBook(String bookIds);

	boolean deleteManyUserOperator(String userIds);


}
