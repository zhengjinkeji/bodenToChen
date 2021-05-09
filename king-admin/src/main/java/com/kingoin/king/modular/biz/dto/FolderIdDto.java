package com.kingoin.king.modular.biz.dto;

/**
 * <p>
 * 定时任务类
 * </p>
 * @author serson
 * @date 2020年1月6日
 * @time 上午11:46:48
 */

public class FolderIdDto {
	private String fileName;
	private String folderId;
	private String remark;
	private String orderNum;
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
}
