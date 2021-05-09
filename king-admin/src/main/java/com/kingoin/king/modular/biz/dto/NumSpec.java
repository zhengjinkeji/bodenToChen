package com.kingoin.king.modular.biz.dto;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 * @author serson
 * @date 2020年3月18日
 * @time 下午3:57:08
 */
public class NumSpec {
	private Integer id;
	private Integer bid;//商品ID
	private Integer begin;
	private Integer end;
	private Integer xishu;
	private Date createtime;
	private Integer userId;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getBegin() {
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public Integer getXishu() {
		return xishu;
	}
	public void setXishu(Integer xishu) {
		this.xishu = xishu;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "NumSpec [id=" + id + ", bid=" + bid + ", begin=" + begin + ", end=" + end + ", xishu=" + xishu
				+ ", createtime=" + createtime + ", userId=" + userId + "]";
	}
	
	
}
