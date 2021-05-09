package com.kingoin.king.modular.biz.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 *
 * </p>
 * 
 * @author serson
 * @date 2019年11月25日
 * @time 上午11:12:25
 */
@TableName("biz_specification")
public class Specification extends Model<Specification> {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 值
	 */
	private Double num;
	/**
	 * 父级字典
	 */
	private Integer pid;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 类别
	 */
	private String typeName;

	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 添加时间
	 */
	private Date dateAdd;
	/**
	 * 添加人
	 */
	private String addUser;
	/**
	 * 修改时间
	 */
	private Date dateUpdate;
	/**
	 * 修改人
	 */
	private String updateUser;
	/**
	 * 商品数量
	 */
	private Integer store;
	
	public Integer getStore() {
		return store;
	}

	public void setStore(Integer store) {
		this.store = store;
	}

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

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Override
	public String toString() {
		return "Specification [id=" + id + ", num=" + num + ", pid=" + pid + ", name=" + name + ", typeName=" + typeName
				+ ", status=" + status + ", dateAdd=" + dateAdd + ", addUser=" + addUser + ", dateUpdate=" + dateUpdate
				+ ", updateUser=" + updateUser + ", store=" + store + "]";
	}

	

}
