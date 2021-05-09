package com.kingoin.king.modular.biz.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回给前台的提示（最终转化为json形式）
 *
 * @author jack
 */
public class Result {
	/**
	 * 
	 */
	private int code = 200;
	private String message ="ok";
	private List<Object> data = new ArrayList<Object>();
	private Map<String, Object> map = new HashMap<String, Object>();
	private List<Map<String, List<Map<String, Object>>>> listData = new ArrayList<Map<String, List<Map<String, Object>>>>();
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();
	private Boolean hasMore = true;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<Map<String, List<Map<String, Object>>>> getListData() {
		return listData;
	}

	public void setListData(List<Map<String, List<Map<String, Object>>>> listData) {
		this.listData = listData;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public List<Map<String, Object>> getList2() {
		return list2;
	}

	public void setList2(List<Map<String, Object>> list2) {
		this.list2 = list2;
	}

	public List<Map<String, Object>> getList3() {
		return list3;
	}

	public void setList3(List<Map<String, Object>> list3) {
		this.list3 = list3;
	}

	public Boolean getHasMore() {
		return hasMore;
	}

	public void setHasMore(Boolean hasMore) {
		this.hasMore = hasMore;
	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", message=" + message + ", data=" + data + ", map=" + map + ", listData="
				+ listData + ", list=" + list + ", list2=" + list2 + ", list3=" + list3 + ", hasMore=" + hasMore + "]";
	}

}
