package com.kingoin.king.modular.biz.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 商品类
 * </p>
 * @author serson
 * @date 2019年11月21日
 * @time 下午1:12:23
 */
@TableName("biz_basicinfo")
public class Basicinfo extends Model<Basicinfo>{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键ID
	 */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	/**
	 * 商品名称
	 */
	private String name;
	/**
	 * 分类id
	 */
	private Integer categoryId;
	/**
	 * 特征
	 */
	private String characteristic;
	/**
	 * 积分 比例
	 */
	private Double  score;
	/**
	 * 库存
	 */
	private Integer store;
	/**
	 * 最低价
	 */
	private Double minPrice;
	/**
	 * 原价
	 */
	private Double originalPrice;
	/**
	 * 已售
	 */
	private Integer numberOrders;
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
	 * recommend
	 * 推荐商品
	 */
	private Integer recommend;
	/**
	 * 喜豆价
	 */
	private Integer xidou;
	/**
	 * type商品类型
	 */
	private String type;
	/**
	 * 一箱数量
	 */
	private Integer box;
	
	


	public Integer getBox() {
		return box;
	}


	public void setBox(Integer box) {
		this.box = box;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Integer getXidou() {
		return xidou;
	}


	public void setXidou(Integer xidou) {
		this.xidou = xidou;
	}


	public Integer getRecommend() {
		return recommend;
	}


	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}


	public Integer getStore() {
		return store;
	}


	public void setStore(Integer store) {
		this.store = store;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	public String getCharacteristic() {
		return characteristic;
	}


	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}


	public Double getScore() {
		return score;
	}


	public void setScore(Double score) {
		this.score = score;
	}


	public Double getMinPrice() {
		return minPrice;
	}


	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}


	public Double getOriginalPrice() {
		return originalPrice;
	}


	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}


	public Integer getNumberOrders() {
		return numberOrders;
	}


	public void setNumberOrders(Integer numberOrders) {
		this.numberOrders = numberOrders;
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
		return "Basicinfo [id=" + id + ", name=" + name + ", categoryId=" + categoryId + ", characteristic="
				+ characteristic + ", score=" + score + ", store=" + store + ", minPrice=" + minPrice
				+ ", originalPrice=" + originalPrice + ", numberOrders=" + numberOrders + ", status=" + status
				+ ", statusStr=" + statusStr + ", remark=" + remark + ", dateAdd=" + dateAdd + ", addUser=" + addUser
				+ ", dateUpdate=" + dateUpdate + ", updateUser=" + updateUser + ", recommend=" + recommend + ", xidou="
				+ xidou + ", type=" + type + ", box=" + box + "]";
	}


	

	
	
	
}
