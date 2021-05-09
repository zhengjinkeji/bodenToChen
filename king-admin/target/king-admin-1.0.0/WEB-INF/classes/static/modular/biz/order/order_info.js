/**
 * 详情对话框（可用于添加和修改对话框）
 */

var OrderInfoDlg = {
	orderInfoData : {},
	validateFields : {
		
		linMan : {
			validators : {
				notEmpty : {
					message : '联系人不能为空'
				}
			}
		},
		mobile : {
			validators : {
				notEmpty : {
					message : '联系电话不能为空'
				}
			}
		},
		payType : {
			validators : {
				notEmpty : {
					message : '支付类型不能为空'
				}
			}
		},
		address : {
			validators : {
				notEmpty : {
					message : '地址不能为空'
				}
			}
		}
	}
};


/**
 * 设置对话框中的数据
 * 
 * @param key
 *            数据的名称
 * @param val
 *            数据的具体值
 */
OrderInfoDlg.set = function(key, val) {
	this.orderInfoData[key] = (typeof value == "undefined") ? $("#" + key).val()
			: value;
	return this;
};

/**
 * 设置对话框中的数据
 * 
 * @param key
 *            数据的名称
 * @param val
 *            数据的具体值
 */
OrderInfoDlg.get = function(key) {
	return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
OrderInfoDlg.close = function() {
	parent.layer.close(window.parent.Order.layerIndex);
};
/**
 * 清除数据
 */
OrderInfoDlg.clearData = function() {
	this.orderInfoData = {};
};

/**
 * 收集数据
 */
OrderInfoDlg.collectData = function() {
	this.set('id').set('orderNum').set('mobile').set('price').set('linMan')
	.set('address').set('trackingCode').set('trackingNum').set('payType');//
};

/**
 * 验证数据是否为空
 */
OrderInfoDlg.validate = function() {
	$('#orderInfoForm').data("bootstrapValidator").resetForm();
	$('#orderInfoForm').bootstrapValidator('validate');
	return $("#orderInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加信息
 */
OrderInfoDlg.addSubmit = function() {
	
	
	
	this.clearData();
	this.collectData();
	if (!this.validate()) {
		return;
	}
	var trackingCode = $("#trackingCode").val();
	var trackingNum = $("#trackingNum").val();
	if(trackingCode ==null || trackingCode ==''  ){
		Feng.info("请选填物流信息！");
		return;
	}
	if(trackingNum == null || trackingNum == ''){
		Feng.info("请选填物流信息！");
		return;
	}
	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/order/tracking_add", function(data) {
		Feng.success("添加成功!");
		window.parent.Order.table.refresh();
		OrderInfoDlg.close();
	}, function(data) {
		Feng.error("添加失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.orderInfoData);
	ajax.start();
};
OrderInfoDlg.addPayTypeSubmit = function() {

	this.clearData();
	this.collectData();
	if (!this.validate()) {
		return;
	}
	
	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/order/pass", function(data) {
		Feng.success("添加成功!");
		window.parent.Order.table.refresh();
		OrderInfoDlg.close();
	}, function(data) {
		Feng.error("添加失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.orderInfoData);
	ajax.start();
};

/**
 * 提交修改
 */
OrderInfoDlg.editSubmit = function() {
	this.clearData();
	this.collectData();

	if (!this.validate()) {
		return;
	}
	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/order/edit", function(data) {
		Feng.success("修改成功!");
		window.parent.Order.table.refresh();
		OrderInfoDlg.close();
	}, function(data) {
		Feng.error("修改失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.orderInfoData);
	ajax.start();
};
$(function() {
	Feng.initValidator("orderInfoForm", OrderInfoDlg.validateFields);
	 //初始化选项
    $("#trackingCode").val($("#trackingCodeVal").val());
    if($("#status").val()=='1'){
    	//$("#trackingCode").hide();
    	//$("#trackingNum").hide();
    }else if($("#status").val()=='2'){
    	$("#price").attr("disabled","disabled");
    	$("#linMan").attr("disabled","disabled");
    	$("#address").attr("disabled","disabled");
    	$("#mobile").attr("disabled","disabled");
    }
    
});
