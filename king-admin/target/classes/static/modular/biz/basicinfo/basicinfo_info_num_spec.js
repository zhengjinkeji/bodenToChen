/**
 * Basicinfo详情对话框（可用于添加和修改对话框）
 */

var NumSpecInfoDlg = {
	basicinfoInfoData : {},
	validateFields : {
		begin : {
			validators : {
				notEmpty : {
					message : '不能为空'
				}
			}
		},
		end : {
			validators : {
				notEmpty : {
					message : '不能为空'
				}
			}
		},
		xishu : {
			validators : {
				notEmpty : {
					message : '不能为空'
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
NumSpecInfoDlg.set = function(key, val) {
	this.basicinfoInfoData[key] = (typeof value == "undefined") ? $("#" + key).val()
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
NumSpecInfoDlg.get = function(key) {
	return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
NumSpecInfoDlg.close = function() {
	parent.layer.close(window.parent.NumSpec.layerIndex);
};
/**
 * 清除数据
 */
NumSpecInfoDlg.clearData = function() {
	this.basicinfoInfoData = {};
};

/**
 * 收集数据
 */
NumSpecInfoDlg.collectData = function() {
	this.set("id").set("bid").set('begin').set("end").set('xishu');//
};

/**
 * 验证数据是否为空
 */
NumSpecInfoDlg.validate = function() {
	$('#basicinfoInfoForm').data("bootstrapValidator").resetForm();
	$('#basicinfoInfoForm').bootstrapValidator('validate');
	return $("#basicinfoInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加信息
 */

NumSpecInfoDlg.addSubmit = function() {
	
	this.clearData();
	this.collectData();

	if (!this.validate()) {
		return;
	}
	
	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/basicinfo/add_num_spec", function(data) {
		Feng.success("添加成功!");
		window.parent.NumSpec.table.refresh();
		NumSpecInfoDlg.close();
	}, function(data) {
		Feng.error("添加失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.basicinfoInfoData);
	//ajax.set("bid",picName);
	ajax.start();
};

/**
 * 提交修改
 */
NumSpecInfoDlg.editSubmit = function() {

	this.clearData();
	this.collectData();

	if (!this.validate()) {
		return;
	}
	
	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/basicinfo/edit_num_spec", function(data) {
		Feng.success("修改成功!");
		window.parent.NumSpec.table.refresh();
		NumSpecInfoDlg.close();
	}, function(data) {
		Feng.error("修改失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.basicinfoInfoData);
	ajax.start();
};


$(function() {
	Feng.initValidator("basicinfoInfoForm", NumSpecInfoDlg.validateFields);
	 //初始化选项
    //$("#categoryId").val($("#categoryType").val());
    //$("#status").val($("#basicinfoStatus").val());
    //$("#recommend").val($("#recommendVal").val());
    
    //$("#pic").val(1111);
});
