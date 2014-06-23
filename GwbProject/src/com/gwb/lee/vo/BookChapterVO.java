package com.gwb.lee.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class BookChapterVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int chapterId;
	private String chapterName;
	private int chapterRank;
	private String chapterUrl;
	private int bookId;
	private String bookName;
	private int categoryId;
	private String categoryName;
	private int classId;
	private String className;
	private Timestamp createTs;
	private int isAvailable;

	public BookChapterVO() {
		// TODO Auto-generated constructor stub
	}

	public int getChapterId() {
		return chapterId;
	}

	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public int getChapterRank() {
		return chapterRank;
	}

	public void setChapterRank(int chapterRank) {
		this.chapterRank = chapterRank;
	}

	public String getChapterUrl() {
		return chapterUrl;
	}

	public void setChapterUrl(String chapterUrl) {
		this.chapterUrl = chapterUrl;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Timestamp getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Timestamp createTs) {
		this.createTs = createTs;
	}

	public int getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	@Override
	public String toString() {
		return "BookChapterVO [chapterId=" + chapterId + ", chapterName="
				+ chapterName + ", chapterRank=" + chapterRank
				+ ", chapterUrl=" + chapterUrl + ", bookId=" + bookId
				+ ", bookName=" + bookName + ", categoryId=" + categoryId
				+ ", categoryName=" + categoryName + ", classId=" + classId
				+ ", className=" + className + ", createTs=" + createTs
				+ ", isAvailable=" + isAvailable + "]";
	}

}