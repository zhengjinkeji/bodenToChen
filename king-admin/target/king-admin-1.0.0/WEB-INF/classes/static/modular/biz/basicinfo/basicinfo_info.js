/**
 * Basicinfo详情对话框（可用于添加和修改对话框）
 */

var BasicinfoInfoDlg = {
	basicinfoInfoData : {},
	validateFields : {
		name : {
			validators : {
				notEmpty : {
					message : '名称不能为空'
				}
			}
		},
		minPrice : {
			validators : {
				notEmpty : {
					message : '最低价不能为空'
				},
				regexp: {//正则验证
                	regexp: /^(([1-9]{1}\d*)|(0{1}))(\.\d{2})$/,
	                 message: '请输入正确的格式'
	            }
			}
		},
		numberOrders : {
			validators : {
				notEmpty : {
					message : '已售不能为空'
				}
			}
		},
		characteristic : {
			validators : {
				notEmpty : {
					message : '特征不能为空'
				}
			}
		},
		score : {
			validators : {
				notEmpty : {
					message : '积分不能为空'
				},
				regexp: {//正则验证
                	regexp: /^(([1-9]{1}\d*)|(0{1}))(\.\d{2})$/,
	                 message: '请输入正确的格式'
	            }
			}
		},
		originalPrice : {
			validators : {
				notEmpty : {
					message : '原价不能为空'
				},
				regexp: {//正则验证
                	regexp: /^(([1-9]{1}\d*)|(0{1}))(\.\d{2})$/,
	                 message: '请输入正确的格式'
	             }
			}
		},
		categoryId : {
			validators : {
				notEmpty : {
					message : '类别不能为空'
				}
			}
		},
		status : {
			validators : {
				notEmpty : {
					message : '状态不能为空'
				}
			}
		},
		pic : {
			validators : {
				notEmpty : {
					message : '图片不能为空'
				}
			}
		},
		store : {
			validators : {
				notEmpty : {
					message : '库存不能为空'
				}
			}
		},
		recommend : {
			validators : {
				notEmpty : {
					message : '是否推荐不能为空'
				}
			}
		},
		box : {
			validators : {
				notEmpty : {
					message : '规格不能为空'
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
BasicinfoInfoDlg.set = function(key, val) {
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
BasicinfoInfoDlg.get = function(key) {
	return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
BasicinfoInfoDlg.close = function() {
	parent.layer.close(window.parent.Basicinfo.layerIndex);
};
/**
 * 清除数据
 */
BasicinfoInfoDlg.clearData = function() {
	this.basicinfoInfoData = {};
};

/**
 * 收集数据
 */
BasicinfoInfoDlg.collectData = function() {
	this.set('id').set("pic").set('name').set('minPrice').set('numberOrders')
	.set('characteristic').set('remark').set('score').set('store').set('originalPrice')
	.set('categoryId').set('status').set('recommend').set('box').set('type');//
};

/**
 * 验证数据是否为空
 */
BasicinfoInfoDlg.validate = function() {
	$('#basicinfoInfoForm').data("bootstrapValidator").resetForm();
	$('#basicinfoInfoForm').bootstrapValidator('validate');
	return $("#basicinfoInfoForm").data('bootstrapValidator').isValid();
};
/**
 * 提交添加图片
 */
BasicinfoInfoDlg.addSubmitPic = function() {
	this.clearData();
	this.collectData();
	if (!this.validate()) {
		return;
	}
	var file = $("#pic")[0].files[0];
	var name = $("#pic").val();
	
	var picName = "";
    //判断： 如果没有选择文件，则提示显示提示信息并关闭弹窗
	if (file == undefined  && name == ""){
        alert("请选择文件后再提交");
        //parent.layer.close(window.parent.Prop.layerIndex);
        return;
    	
    }
    var formData = new FormData();
	formData.append("file",$("#pic")[0].files[0]);
    //formData.append("name",nameArray[nameArray.length-1]);
	formData.append("fileName","basicinfo");
	$.ajax({
		url : Feng.ctxPath+"/banner/upload",
		type:'POST',
		async : false,
		// 告诉jQuery不要去处理发送的数据
		processData : false,
		data:formData,
		// 告诉jQuery不要去设置Content-Type请求头
        contentType : false,
       /* beforeSend:function(){
        	alert("正在进行，请稍后");
        },*/
        success:function(responseStr){
        	picName = responseStr;
        }
	});
	
	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/basicinfo/picadd2", function(data) {
		Feng.success("添加成功!");
		window.parent.Basicinfo.table.refresh();
		BasicinfoInfoDlg.close();
	}, function(data) {
		Feng.error("添加失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.basicinfoInfoData);
	ajax.set("picName",picName);
	ajax.start();
};
/**
 * 提交添加信息
 */
BasicinfoInfoDlg.addSubmit = function() {
	
	this.clearData();
	this.collectData();

	if (!this.validate()) {
		return;
	}
	
	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/basicinfo/add", function(data) {
		Feng.success("添加成功!");
		window.parent.Basicinfo.table.refresh();
		BasicinfoInfoDlg.close();
	}, function(data) {
		Feng.error("添加失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.basicinfoInfoData);
	ajax.start();
};
/**
 * 提交更新图片信息
 */
BasicinfoInfoDlg.editPicSubmit = function() {
	this.clearData();
	this.collectData();

	if (!this.validate()) {
		return;
	}
	
	var file = $("#pic")[0].files[0];
	var name = $("#pic").val();
	
	var picName = "";
    //判断： 如果没有选择文件，则提示显示提示信息并关闭弹窗
	if (file == undefined  && name == ""){
        alert("请选择文件后再提交");
        //parent.layer.close(window.parent.Prop.layerIndex);
        return;
    	
    }
    var formData = new FormData();
	formData.append("file",$("#pic")[0].files[0]);
    //formData.append("name",nameArray[nameArray.length-1]);
	formData.append("fileName","basicinfo");
	$.ajax({
		url : Feng.ctxPath+"/Basicinfo/upload",
		type:'POST',
		async : false,
		// 告诉jQuery不要去处理发送的数据
		processData : false,
		data:formData,
		// 告诉jQuery不要去设置Content-Type请求头
        contentType : false,
       /* beforeSend:function(){
        	alert("正在进行，请稍后");
        },*/
        success:function(responseStr){
        	picName = responseStr;
        }
	});
	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/Basicinfo/edit", function(data) {
		Feng.success("修改成功!");
		window.parent.Basicinfo.table.refresh();
		BasicinfoInfoDlg.close();
	}, function(data) {
		Feng.error("修改失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.basicinfoInfoData);
	ajax.set("picName",picName);
	ajax.set("picEdit",true);
	ajax.start();
   
};
/**
 * 提交修改
 */
BasicinfoInfoDlg.editSubmit = function() {

	this.clearData();
	this.collectData();

	if (!this.validate()) {
		return;
	}
	
	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/basicinfo/edit", function(data) {
		Feng.success("修改成功!");
		window.parent.Basicinfo.table.refresh();
		BasicinfoInfoDlg.close();
	}, function(data) {
		Feng.error("修改失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.basicinfoInfoData);
	ajax.start();
};
$(function() {
	Feng.initValidator("basicinfoInfoForm", BasicinfoInfoDlg.validateFields);
	 //初始化选项
    $("#categoryId").val($("#categoryType").val());
    $("#status").val($("#basicinfoStatus").val());
    $("#recommend").val($("#recommendVal").val());
    
    //$("#pic").val(1111);
});
