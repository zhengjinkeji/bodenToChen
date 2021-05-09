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
 * @date 2019年11月15日
 * @time 上午9:45:28
 */
@TableName("biz_banner")
public class Banner extends Model<Banner> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 照片
	 */
	private String pic;
	/**
	 * 链接
	 */
	private String linkUrl;
	/**
	 * 商品ID
	 */
	private Integer businessId;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
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
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Banner [id=" + id + ", title=" + title + ", pic=" + pic + ", linkUrl=" + linkUrl + ", businessId="
				+ businessId + ", status=" + status + ", statusStr=" + statusStr + ", remark=" + remark + ", type="
				+ type + ", dateAdd=" + dateAdd + ", addUser=" + addUser + ", dateUpdate=" + dateUpdate
				+ ", updateUser=" + updateUser + "]";
	}

}
