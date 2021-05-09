package com.kingoin.king.modular.biz.dto;

import com.kingoin.king.modular.biz.model.Order;
import com.kingoin.king.modular.biz.service.IOrderService;
import com.kingoin.king.modular.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定时任务类
 * </p>
 * @author serson
 * @date 2020年1月6日
 * @time 上午11:46:48
 */

public class FileInfoDto {
	private String fileType;
	private String fileId;
	private String params;
	private String fileTypeParams;
	private String fileIdParams;
	private String orderNumParams;
	private String remarkParams;

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getFileTypeParams() {
		return fileTypeParams;
	}

	public void setFileTypeParams(String fileTypeParams) {
		this.fileTypeParams = fileTypeParams;
	}

	public String getFileIdParams() {
		return fileIdParams;
	}

	public void setFileIdParams(String fileIdParams) {
		this.fileIdParams = fileIdParams;
	}

	public String getOrderNumParams() {
		return orderNumParams;
	}

	public void setOrderNumParams(String orderNumParams) {
		this.orderNumParams = orderNumParams;
	}

	public String getRemarkParams() {
		return remarkParams;
	}

	public void setRemarkParams(String remarkParams) {
		this.remarkParams = remarkParams;
	}

	@Override
	public String toString() {
		return "FileInfoDto{" +
				"fileType='" + fileType + '\'' +
				", fileId='" + fileId + '\'' +
				", params='" + params + '\'' +
				", fileTypeParams='" + fileTypeParams + '\'' +
				", fileIdParams='" + fileIdParams + '\'' +
				", orderNumParams='" + orderNumParams + '\'' +
				", remarkParams='" + remarkParams + '\'' +
				'}';
	}
}
