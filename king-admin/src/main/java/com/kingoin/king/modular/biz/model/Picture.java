package com.kingoin.king.modular.biz.model;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 * 
 * @author serson
 * @date 2019年11月21日
 * @time 下午5:20:10
 */
public class Picture {
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 商品id
	 */
	private Integer basicinfoId;
	/**
	 * 图片名
	 */
	private String pic;
	/**
	 * 类型
	 */
	private Integer type;
	/**
	 * 类型说明
	 */
	private String typeStr;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBasicinfoId() {
		return basicinfoId;
	}

	public void setBasicinfoId(Integer basicinfoId) {
		this.basicinfoId = basicinfoId;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
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
		return "Picture [id=" + id + ", basicinfoId=" + basicinfoId + ", pic=" + pic + ", type=" + type + ", typeStr="
				+ typeStr + ", status=" + status + ", dateAdd=" + dateAdd + ", addUser=" + addUser + ", dateUpdate="
				+ dateUpdate + ", updateUser=" + updateUser + "]";
	}
}
