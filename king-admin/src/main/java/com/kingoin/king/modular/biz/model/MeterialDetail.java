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
@TableName("tt_meterial_detail")
public class MeterialDetail extends Model<MeterialDetail>{
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
	 * 排序序号
	 */
	private String remark;

	/**
	 * 排序序号
	 */
	private String url;

	/**
	 * 上传时间
	 */
	private Date createDate;

	/**
	 * 是否有效
	 */
	private Integer isValid;
	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "MeterialDetail{" +
				"id=" + id +
				", meterialName='" + meterialName + '\'' +
				", remark='" + remark + '\'' +
				", url='" + url + '\'' +
				", createDate=" + createDate +
				", isValid=" + isValid +
				'}';
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
}
