package com.kingoin.king.modular.biz.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 商品和规格关联表
 * </p>
 *
 */
@TableName("biz_relation")
public class Relation2 extends Model<Relation2> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 菜单id
	 */
	private Long specId;
	/**
	 * 商品id
	 */
	private Integer basicinfoId;
	/**
	 * 添加日期
	 */
	private Date dateAdd;
	/**
	 * 添加人
	 */
	private String addUser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getSpecId() {
		return specId;
	}

	public void setSpecId(Long specId) {
		this.specId = specId;
	}

	public Integer getBasicinfoId() {
		return basicinfoId;
	}

	public void setBasicinfoId(Integer basicinfoId) {
		this.basicinfoId = basicinfoId;
	}

	public Date getDateAdd() {
		return dateAdd;
	}

	public void setDateAdd(Date dateAdd) {
		this.dateAdd = dateAdd;
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Relation [id=" + id + ", specId=" + specId + ", basicinfoId=" + basicinfoId + ", dateAdd=" + dateAdd
				+ ", addUser=" + addUser + "]";
	}

}
