package com.gwb.lee.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class OperatorLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int logId;
	private int userId;
	private String userName;
	private String telephone;
	private String logType;
	private String logInfo;
	private Timestamp logTs;

	public OperatorLog() {
		// TODO Auto-generated constructor stub
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public OperatorLog(int logId, String userName, String logType,
			String logInfo, Timestamp logTs) {
		super();
		this.logId = logId;
		this.userName = userName;
		this.logType = logType;
		this.logInfo = logInfo;
		this.logTs = logTs;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	public Timestamp getLogTs() {
		return logTs;
	}

	public void setLogTs(Timestamp logTs) {
		this.logTs = logTs;
	}

	@Override
	public String toString() {
		return "OperatorLog [logId=" + logId + ", userName=" + userName
				+ ", logType=" + logType + ", logInfo=" + logInfo + ", logTs="
				+ logTs + "]";
	}
}
