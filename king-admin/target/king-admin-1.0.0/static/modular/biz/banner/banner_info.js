/**
 * banner详情对话框（可用于添加和修改对话框）
 */

var BannerInfoDlg = {
	bannerInfoData : {},
	validateFields : {
		title : {
			validators : {
				notEmpty : {
					message : '标题不能为空'
				}
			}
		},
		type : {
			validators : {
				notEmpty : {
					message : '类型不能为空'
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
		}
	}
};
/**
 * ,
		pic : {
			validators : {
				notEmpty : {
					message : '图片不能为空'
				}
			}
		}
 */
/**
 * 名称信息验证

$("#name").blur(function() {
	var name = $("#name").val();
	var propId = $('#id').val();
	$.ajax({
		type : "post",
		url : Feng.ctxPath + '/prop/check',
		data : {
			"propId" : propId,
			"name" : name
		},
		async : false,
		dataType : "html",
		success : function(data) {
			if (data == "1") {
				$("#name").val("");
				$("#nameC").html("名称“ " + name + " ” 已存在");
			} else {
				$("#nameC").html("");
			}
		}
	})
}); */
/**
 * 联系人电话信息验证
 
$("#phone").blur(function() {
	// debugger;
	var phone = $("#phone").val();
	console.log(phone);
	var reg = /^1[3|4|5|7|8][0-9]\d{8}$/;
	if (reg.test(phone)) {
		$("#phoneC").html("");
	} else {
		$("#phone").val("");
		$("#phoneC").html(phone + " 手机格式不正确");
	}
});*/
/**
 * 邮箱验证
 
$("#email")
		.blur(
				function() {
					// debugger;
					var email = $("#email").val();
					console.log(email);
					var reg = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
					if (reg.test(email) && email != "") {
						$("#emailC").html("");// 可有可无
					} else {
						$("#email").val("");
						$("#emailC").html(email + " 邮箱格式不正确");
					}
				})*/

/**
 * 设置对话框中的数据
 * 
 * @param key
 *            数据的名称
 * @param val
 *            数据的具体值
 */
BannerInfoDlg.set = function(key, val) {
	this.bannerInfoData[key] = (typeof value == "undefined") ? $("#" + key).val()
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
BannerInfoDlg.get = function(key) {
	return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
BannerInfoDlg.close = function() {
	parent.layer.close(window.parent.Banner.layerIndex);
};
/**
 * 清除数据
 */
BannerInfoDlg.clearData = function() {
	this.bannerInfoData = {};
};

/**
 * 收集数据
 */
BannerInfoDlg.collectData = function() {
	this.set('id').set('title').set('pic').set('businessId').set('type').set('remark').set('status');//
};

/**
 * 验证数据是否为空
 */
BannerInfoDlg.validate = function() {
	$('#banenrInfoForm').data("bootstrapValidator").resetForm();
	$('#banenrInfoForm').bootstrapValidator('validate');
	return $("#banenrInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加信息
 */
BannerInfoDlg.addSubmit = function() {
	
	
	
	this.clearData();
	this.collectData();

	if (!this.validate()) {
		return;
	}
	
	var file = $("#pic")[0].files[0];
	var name = $("#pic").val();
	var nameArray = name.split("\\");
	var formData = new FormData();
	formData.append("file",$("#pic")[0].files[0]);
	var picName = "";
    //判断： 如果没有选择文件，则提示显示提示信息并关闭弹窗
    if ($("#pic")[0].files[0] == null && $("#pic")[0].files[0] == ""){
        alert("请选择文件后再提交");
        //parent.layer.close(window.parent.Prop.layerIndex);
        return;
    	
        
    }
    //formData.append("name",nameArray[nameArray.length-1]);
    formData.append("fileName","banner");
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
	var ajax = new $ax(Feng.ctxPath + "/banner/add", function(data) {
		Feng.success("添加成功!");
		window.parent.Banner.table.refresh();
		BannerInfoDlg.close();
	}, function(data) {
		Feng.error("添加失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.bannerInfoData);
	ajax.set("picName",picName);
	
	ajax.start();
};
/**
 * 提交更新图片信息
 */
BannerInfoDlg.editPicSubmit = function() {
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
	formData.append("fileName","banner");
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
	var ajax = new $ax(Feng.ctxPath + "/banner/edit", function(data) {
		Feng.success("修改成功!");
		window.parent.Banner.table.refresh();
		BannerInfoDlg.close();
	}, function(data) {
		Feng.error("修改失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.bannerInfoData);
	ajax.set("picName",picName);
	ajax.set("picEdit",true);
	ajax.start();
   
};
/**
 * 提交修改
 */
BannerInfoDlg.editSubmit = function() {

	this.clearData();
	this.collectData();

	if (!this.validate()) {
		return;
	}
	
	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/banner/edit", function(data) {
		Feng.success("修改成功!");
		window.parent.Banner.table.refresh();
		BannerInfoDlg.close();
	}, function(data) {
		Feng.error("修改失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.bannerInfoData);
	ajax.set("picEdit",false);
	ajax.start();
};
$(function() {
	Feng.initValidator("banenrInfoForm", BannerInfoDlg.validateFields);
	 //初始化选项
    $("#type").val($("#bannerType").val());
    $("#status").val($("#bannerStatus").val());
    //$("#pic").val(1111);
});
