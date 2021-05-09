/**
 * Basicinfo详情对话框（可用于添加和修改对话框）
 */

var PicInfoDlg = {
	picInfoData : {},
	validateFields : {
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
 * 设置对话框中的数据
 * 
 * @param key
 *            数据的名称
 * @param val
 *            数据的具体值
 */
PicInfoDlg.set = function(key, val) {
	this.picInfoData[key] = (typeof value == "undefined") ? $("#" + key).val()
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
PicInfoDlg.get = function(key) {
	return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
PicInfoDlg.close = function() {
	parent.layer.close(window.parent.Pic.layerIndex);
};
/**
 * 清除数据
 */
PicInfoDlg.clearData = function() {
	this.picInfoData = {};
};

/**
 * 收集数据
 */
PicInfoDlg.collectData = function() {
	this.set('basicinfoId').set('pic');//
};

/**
 * 验证数据是否为空
 */
PicInfoDlg.validate = function() {
	$('#picInfoForm').data("bootstrapValidator").resetForm();
	$('#picInfoForm').bootstrapValidator('validate');
	return $("#picInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加信息
 */
PicInfoDlg.addSubmit = function() {
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
	var ajax = new $ax(Feng.ctxPath + "/basicinfo/picadd", function(data) {
		Feng.success("添加成功!");
		window.parent.Pic.table.refresh();
		PicInfoDlg.close();
		window.parent.Basicinfo.table.refresh();
	}, function(data) {
		Feng.error("添加失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.picInfoData);
	ajax.set("picName",picName);
	ajax.start();
};
/**
 * 提交更新图片信息
 
PicInfoDlg.editPicSubmit = function() {
	this.clearData();
	this.collectData();

	if (!this.validate()) {
		return;
	}

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
   
};*/
/**
 * 提交修改
 
PicInfoDlg.editSubmit = function() {

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
};*/
$(function() {
	Feng.initValidator("picInfoForm", PicInfoDlg.validateFields);
});
