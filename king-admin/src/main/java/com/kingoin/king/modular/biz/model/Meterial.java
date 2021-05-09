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
@TableName("tt_material")
public class Meterial extends Model<Meterial>{
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 素材名称
	 */
	private String meterialName;

	/**
	 * 素菜类型
	 */
	private String type;
	/**
	 * 素菜类型
	 */
	private String content;
	/**
	 * 上传时间
	 */
	private Date createDate;

	private String createBy;

	private String remark;

	private Integer isValid;

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
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

	public String getMeterialName() {
		return meterialName;
	}

	public void setMeterialName(String meterialName) {
		this.meterialName = meterialName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Meterial{" +
				"id=" + id +
				", meterialName='" + meterialName + '\'' +
				", type='" + type + '\'' +
				", content='" + content + '\'' +
				", createDate=" + createDate +
				", createBy='" + createBy + '\'' +
				", remark='" + remark + '\'' +
				", isValid=" + isValid +
				'}';
	}
}
