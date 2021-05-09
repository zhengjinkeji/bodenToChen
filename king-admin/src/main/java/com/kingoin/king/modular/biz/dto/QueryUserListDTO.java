package com.kingoin.king.modular.biz.dto;

/**
 * <p>
 *
 * </p>
 * 
 * @author serson
 * @date 2019年12月9日
 * @time 下午3:57:16
 */
public class QueryUserListDTO {

	private Integer id;

	private String account;

	private String name;

	private Integer status;

	private String beginDate;

	private String endDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
