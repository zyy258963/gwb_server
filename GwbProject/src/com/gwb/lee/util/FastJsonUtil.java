package com.gwb.lee.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class FastJsonUtil {

	public FastJsonUtil() {
		// TODO Auto-generated constructor stu
	}

	public static String createJsonString(Object value) {
		return JSON.toJSONString(value);
	}

	public static String getResultMapJson(String headerStatus,
			Map<String, Object> content) {
		Map<String, Object> map = new HashMap<String, Object>();

		// 构造 header
		if (ConstantParams.HTTP_STATUS_HEADER_SUCCESS.equals(headerStatus)) {
			map.put(ConstantParams.HTTP_TITLE_HEADER,
					ConstantParams.HTTP_SUCCESS_HEADER);
		} else if (ConstantParams.HTTP_STATUS_HEADER_FAIL.equals(headerStatus)) {
			map.put(ConstantParams.HTTP_TITLE_HEADER,
					ConstantParams.HTTP_FAIL_HEADER);
		}

		// 构造 content
		map.put(ConstantParams.HTTP_TITLE_CONTENT, content);

		return createJsonString(map);
	}

	public static String getResultListMapJson(String headerStatus,
			List<Map<String, Object>> content) {
		Map<String, Object> map = new HashMap<String, Object>();

		// 构造 header
		if (ConstantParams.HTTP_STATUS_HEADER_SUCCESS.equals(headerStatus)) {
			map.put(ConstantParams.HTTP_TITLE_HEADER,
					ConstantParams.HTTP_SUCCESS_HEADER);
		} else if (ConstantParams.HTTP_STATUS_HEADER_FAIL.equals(headerStatus)) {
			map.put(ConstantParams.HTTP_TITLE_HEADER,
					ConstantParams.HTTP_FAIL_HEADER);
		}
		// 构造 content
		map.put(ConstantParams.HTTP_TITLE_CONTENT, content);

		return createJsonString(map);
	}

	public static String getResultListJson(String headerStatus, Object content) {
		Map<String, Object> map = new HashMap<String, Object>();

		// 构造 header
		if (ConstantParams.HTTP_STATUS_HEADER_SUCCESS.equals(headerStatus)) {
			map.put(ConstantParams.HTTP_TITLE_HEADER,
					ConstantParams.HTTP_SUCCESS_HEADER);
		} else if (ConstantParams.HTTP_STATUS_HEADER_FAIL.equals(headerStatus)) {
			map.put(ConstantParams.HTTP_TITLE_HEADER,
					ConstantParams.HTTP_FAIL_HEADER);
		} else if (ConstantParams.HTTP_STATUS_HEADER_FAIL_NO_MAC.equals(headerStatus)) {
			map.put(ConstantParams.HTTP_TITLE_HEADER,
					ConstantParams.HTTP_FAIL_NO_MAC_HEADER);
		}
		// 构造 content
		map.put(ConstantParams.HTTP_TITLE_CONTENT, content);

		return createJsonString(map);
	}

}
