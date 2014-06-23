package com.gwb.lee.vo;

import java.io.Serializable;

public class Ad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int adId;
	private String adName;
	private String adPic;
	private String adUrl;
	private int adPosition;

	public Ad() {
		// TODO Auto-generated constructor stub
	}

	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public String getAdPic() {
		return adPic;
	}

	public void setAdPic(String adPic) {
		this.adPic = adPic;
	}

	public int getAdPosition() {
		return adPosition;
	}

	public void setAdPosition(int adPosition) {
		this.adPosition = adPosition;
	}

	public String getAdUrl() {
		return adUrl;
	}

	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}

	@Override
	public String toString() {
		return "Ad [adId=" + adId + ", adName=" + adName + ", adPic=" + adPic
				+ ", adPosition=" + adPosition + "]";
	}

}
