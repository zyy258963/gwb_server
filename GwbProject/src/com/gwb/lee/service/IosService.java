package com.gwb.lee.service;

import java.util.List;
import java.util.Map;

import com.gwb.lee.vo.Ad;
import com.gwb.lee.vo.BookAppClass;
import com.gwb.lee.vo.BookCategory;
import com.gwb.lee.vo.BookClass;
import com.gwb.lee.vo.Books;
import com.gwb.lee.vo.FavouriteBook;
import com.gwb.lee.vo.OperatorLog;
import com.gwb.lee.vo.Users;

public interface IosService {

	Map<String, Object> login(String userName, String password, String macAddress);

	Users editUser(String userid, Map<String, Object> params);

	Users viewUserInfo(String userid);

	boolean changePwd(String userName, String password);


	List<BookCategory> getAllCategoryList();

	List<Map<String, Object>> getClassMap(String categoryId);

	List<Map<String, String>> getBookMap(String classId);

	List<Books> listBook(String classId,
			String keywords, int start, int end);

	List<Books> listBook(String classId);

	List<Books> searchBook(String keywords, int start, int end);

	List<Books> searchBook(String keywords);

	List<Map<String, Object>> getCategoryList();

	List<BookAppClass> getClassByCategory(String categoryId);

	List<Books> getBookByClass(String classId);

	List<FavouriteBook> deleteFavouritebook(String userId, String favouriteId);

	String addFavouritebook(String userId, String bookId);

	List<FavouriteBook> listFavouritebook(String userId);

	List<Ad> listAd(String adPosition);

	Map<String, Object> appLogin(String telephone, String macAddress);
//	Map<String, Object> appLoginTest(String telephone, String macAddress);

	void log(String userId, String logSearch, String keywords);


	

}
