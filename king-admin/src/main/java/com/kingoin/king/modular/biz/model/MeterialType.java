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
@TableName("tt_material_type")
public class MeterialType extends Model<MeterialType>{
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 素材名称
	 */
	private String typeName;
	/**
	 * 上传时间
	 */
	private Date createDate;

	private String createBy;

	private String remark;

	private Integer isValid;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	@Override
	public String toString() {
		return "MeterialType{" +
				"id=" + id +
				", typeName='" + typeName + '\'' +
				", createDate=" + createDate +
				", createBy='" + createBy + '\'' +
				", remark='" + remark + '\'' +
				", isValid=" + isValid +
				'}';
	}
}
