package com.gwb.lee.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class BookChapter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int bookId;
	private int chapterId;
	private String chapterName;
	private int chapterRank;
	private int chapterPage;
	private String chapterUrl;

	public BookChapter() {
		// TODO Auto-generated constructor stub
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
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

	public int getChapterPage() {
		return chapterPage;
	}

	public void setChapterPage(int chapterPage) {
		this.chapterPage = chapterPage;
	}

	public String getChapterUrl() {
		return chapterUrl;
	}

	public void setChapterUrl(String chapterUrl) {
		this.chapterUrl = chapterUrl;
	}

	@Override
	public String toString() {
		return "BookChapter [bookId=" + bookId + ", chapterId=" + chapterId
				+ ", chapterName=" + chapterName + ", chapterRank="
				+ chapterRank + ", chapterPage=" + chapterPage
				+ ", chapterUrl=" + chapterUrl + "]";
	}

}
