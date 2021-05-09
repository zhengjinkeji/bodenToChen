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
public class UserInfo {

	private Integer id;
	private String account;// 账户名
	private String pic;// 照片
	private String picType;// 照片类型

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

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPicType() {
		return picType;
	}

	public void setPicType(String picType) {
		this.picType = picType;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", account=" + account + ", pic=" + pic + ", picType=" + picType + "]";
	}

}
