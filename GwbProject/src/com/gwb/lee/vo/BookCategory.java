package com.gwb.lee.vo;

import java.io.Serializable;

public class BookCategory implements Serializable,SuperVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int categoryId;
	private String categoryName;

	public BookCategory() {
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
