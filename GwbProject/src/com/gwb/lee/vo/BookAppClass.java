package com.gwb.lee.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class BookAppClass implements Serializable {

	private int classId;
	private String className;
	private int categoryId;

	public BookAppClass() {
		// TODO Auto-generated constructor stub
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "BookClass [classId=" + classId + ", className=" + className
				+ ", categoryId=" + categoryId + "]";
	}

}
