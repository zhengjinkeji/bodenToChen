package com.kingoin.king.modular.biz.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *	素材资料实体类
 * </p>
 * @author serson
 * @date 2019年12月28日
 * @time 下午3:30:57
 */
@TableName("tt_gzh_user")
public class GzhUser extends Model<GzhUser>{
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 电话号码
	 */
	private String mobilePhone;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 性别
	 */
    private String gender;

	/**
	 * 姓名
	 */
	private String userName;

	/**
	 * 是否回访过
	 */
	private Integer is_trace;

	/**
	 * 是否管理员
	 */
	private Integer is_admin;

	/**
	 * 上传时间
	 */
	private Date createDate;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getIs_trace() {
		return is_trace;
	}

	public void setIs_trace(Integer is_trace) {
		this.is_trace = is_trace;
	}

	public Integer getIs_admin() {
		return is_admin;
	}

	public void setIs_admin(Integer is_admin) {
		this.is_admin = is_admin;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
