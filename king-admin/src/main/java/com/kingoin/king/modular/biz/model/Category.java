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
 * @date 2019年11月19日
 * @time 下午13:31:28
 */
@TableName("biz_category")
public class Category extends Model<Category> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 父类ID
	 */
	private Integer pid;
	/**
	 * 父类名称
	 */
	private String pname;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 图标照片
	 */
	private String icon;
	/**
	 * 层级
	 */
	private Integer level;
	/**
	 * 排序
	 */
	private Integer num;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 状态说明
	 */
	private String statusStr;
	/**
	 * 说明
	 */
	private String remark;
	/**
	 * 类型
	 */
	private String type;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
		return "Category [id=" + id + ", pid=" + pid + ", pname=" + pname + ", name=" + name + ", icon=" + icon
				+ ", level=" + level + ", num=" + num + ", status=" + status + ", statusStr=" + statusStr + ", remark="
				+ remark + ", type=" + type + ", dateAdd=" + dateAdd + ", addUser=" + addUser + ", dateUpdate="
				+ dateUpdate + ", updateUser=" + updateUser + "]";
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
