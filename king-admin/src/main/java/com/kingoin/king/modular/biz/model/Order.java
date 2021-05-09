package com.kingoin.king.modular.biz.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 *	订单实体类
 * </p>
 * @author serson
 * @date 2019年12月28日
 * @time 下午3:30:57
 */
@TableName("biz_item_order")
public class Order extends Model<Order>{
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 订单编号
	 */
	private String orderNum;
	/**
	 * 价格
	 */
	private Double price;
	/**
	 * 用户Id
	 */
	private Integer userId;
	/**
	 * 联系人
	 */
	private String linMan;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 联系电话
	 */
	private String mobile;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private Date dateAdd;
	/**
	 * 订单关闭时间
	 */
	private Date dateClose;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 完成时间
	 */
	private Date dateFinish;
	/**
	 * 积分
	 */
	private Integer scores;
	/**
	 * 物流公司编码
	 */
	private String trackingCode;
	/**
	 * 物流单号
	 */
	private String trackingNum;
	/**
	 * 支付凭证
	 */
	private String voucher;
	/**
	 * 支付方式
	 */
	private String payType;
	/**
	 * 所需喜豆
	 */
	private Integer xidou;//所需喜豆
	
	
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Integer getXidou() {
		return xidou;
	}

	public void setXidou(Integer xidou) {
		this.xidou = xidou;
	}
	
	public Date getDateClose() {
		return dateClose;
	}

	public void setDateClose(Date dateClose) {
		this.dateClose = dateClose;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public String getTrackingCode() {
		return trackingCode;
	}

	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}

	public String getTrackingNum() {
		return trackingNum;
	}

	public void setTrackingNum(String trackingNum) {
		this.trackingNum = trackingNum;
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

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLinMan() {
		return linMan;
	}

	public void setLinMan(String linMan) {
		this.linMan = linMan;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getDateAdd() {
		return dateAdd;
	}

	public void setDateAdd(Date dateAdd) {
		this.dateAdd = dateAdd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}

	public Integer getScores() {
		return scores;
	}

	public void setScores(Integer scores) {
		this.scores = scores;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNum=" + orderNum + ", price=" + price + ", userId=" + userId + ", linMan="
				+ linMan + ", address=" + address + ", mobile=" + mobile + ", remark=" + remark + ", dateAdd=" + dateAdd
				+ ", dateClose=" + dateClose + ", status=" + status + ", dateFinish=" + dateFinish + ", scores="
				+ scores + ", trackingCode=" + trackingCode + ", trackingNum=" + trackingNum + ", voucher=" + voucher
				+ ", payType=" + payType + ", xidou=" + xidou + "]";
	}

	

	
	
}
