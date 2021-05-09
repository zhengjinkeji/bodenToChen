/**
 * 详情对话框（可用于添加和修改对话框）
 */

var CategoryInfoDlg = {
	categoryInfoData : {},
	validateFields : {
		name : {
			validators : {
				notEmpty : {
					message : '名称不能为空'
				}
			}
		},
		level : {
			validators : {
				notEmpty : {
					message : '层级不能为空'
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
		pid : {
			validators : {
				notEmpty : {
					message : '类别不能为空'
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
CategoryInfoDlg.set = function(key, val) {
	this.categoryInfoData[key] = (typeof value == "undefined") ? $("#" + key).val()
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
CategoryInfoDlg.get = function(key) {
	return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
CategoryInfoDlg.close = function() {
	parent.layer.close(window.parent.Category.layerIndex);
};
/**
 * 清除数据
 */
CategoryInfoDlg.clearData = function() {
	this.categoryInfoData = {};
};

/**
 * 收集数据
 */
CategoryInfoDlg.collectData = function() {
	this.set('id').set('name').set('icon').set('level').set('pid').set('num').set('remark').set('status');//
};

/**
 * 验证数据是否为空
 */
CategoryInfoDlg.validate = function() {
	$('#categoryInfoForm').data("bootstrapValidator").resetForm();
	$('#categoryInfoForm').bootstrapValidator('validate');
	return $("#categoryInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加信息
 */
CategoryInfoDlg.addSubmit = function() {
	
	
	
	this.clearData();
	this.collectData();

	if (!this.validate()) {
		return;
	}
	var file = $("#icon")[0].files[0];
	var name = $("#icon").val();
	//var nameArray = name.split("\\");
	var formData = new FormData();
	formData.append("file",$("#icon")[0].files[0]);
	formData.append("fileName","category");
	var picName = "";
    //判断： 如果没有选择文件，则提示显示提示信息并关闭弹窗
    if ($("#icon")[0].files[0] != null && $("#icon")[0].files[0] != ""){
        //alert("请选择文件后再提交");
        //parent.layer.close(window.parent.Prop.layerIndex);
        //return;
    	$.ajax({
    		url : Feng.ctxPath+"/banner/upload",
    		type:'POST',
    		async : false,
    		// 告诉jQuery不要去处理发送的数据
    		processData : false,
    		data:formData,
    		// 告诉jQuery不要去设置Content-Type请求头
            contentType : false,
            //beforeSend:function(){
            //	alert("正在进行，请稍后");
            //},
            success:function(responseStr){
            	picName = responseStr;
            }
    	});
        
    }
    //formData.append("name",nameArray[nameArray.length-1]);
	
	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/category/add", function(data) {
		Feng.success("添加成功!");
		window.parent.Category.table.refresh();
		CategoryInfoDlg.close();
	}, function(data) {
		Feng.error("添加失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.categoryInfoData);
	ajax.set("picName",picName);
	ajax.start();
};
/**
 * 提交更新图片信息
 */
CategoryInfoDlg.editPicSubmit = function() {
	this.clearData();
	this.collectData();

	if (!this.validate()) {
		return;
	}
	debugger;
	var file = $("#icon")[0].files[0];
	var  name= $("#icon").val();
	//var nameArray = name.split("\\");
	
	var picName = "";
    //判断： 如果没有选择文件，则提示显示提示信息并关闭弹窗
    if (file == undefined  && name == ""){
        alert("请选择文件后再提交");
        //parent.layer.close(window.parent.Prop.layerIndex);
        return;
    	
        
    }
    var formData = new FormData();
	formData.append("file",$("#icon")[0].files[0]);
	formData.append("fileName","category");
    $.ajax({
		url : Feng.ctxPath+"/banner/upload",
		type:'POST',
		async : false,
		// 告诉jQuery不要去处理发送的数据
		processData : false,
		data:formData,
		// 告诉jQuery不要去设置Content-Type请求头
        contentType : false,
        //beforeSend:function(){
        //	alert("正在进行，请稍后");
        //},
        success:function(responseStr){
        	picName = responseStr;
        }
	});
	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/category/edit", function(data) {
		Feng.success("修改成功!");
		window.parent.Category.table.refresh();
		CategoryInfoDlg.close();
	}, function(data) {
		Feng.error("修改失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.categoryInfoData);
	ajax.set("picName",picName);
	ajax.set("picEdit",true);
	ajax.start();
   
};
/**
 * 提交修改
 */
CategoryInfoDlg.editSubmit = function() {
	
	this.clearData();
	this.collectData();

	if (!this.validate()) {
		return;
	}
	
	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/category/edit", function(data) {
		Feng.success("修改成功!");
		window.parent.Category.table.refresh();
		CategoryInfoDlg.close();
	}, function(data) {
		Feng.error("修改失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.categoryInfoData);
	ajax.set("picEdit",false);
	ajax.start();
};
//监听选中事件
$('#level').change(function(data){
	//获取选中项的值
    var value = $("#level option:selected").attr("value");
   if(value==1){
    	$("#pid").val("0");
    	$("#pid").attr("disabled", "disabled");
    }else{
    	$("#pid").val("");
    	$("#pid").removeAttr("disabled");
    	$('#pid  option:eq(1)').attr("disabled", "disabled");
    }
    	
    //console.log("value=%s",value);
    /*if(value==1){
    	$("#driverInfoContent").find("div").eq(6).show();
    	$("#driverInfoContent").find("div").eq(7).show();
    	$("#driverInfoContent").find("div").eq(8).show();
    	
    	$("#driverInfoContent").find("div").eq(9).hide();
    	$("#driverInfoContent").find("div").eq(10).hide();
    	$("#driverInfoContent").find("div").eq(11).hide();
    	
    }else{
    	$("#driverInfoContent").find("div").eq(9).show();
    	$("#driverInfoContent").find("div").eq(10).show();
    	$("#driverInfoContent").find("div").eq(11).show();
    	
    	$("#driverInfoContent").find("div").eq(6).hide();
		$("#driverInfoContent").find("div").eq(7).hide();
		$("#driverInfoContent").find("div").eq(8).hide();
    }*/
});
$(function() {
	Feng.initValidator("categoryInfoForm", CategoryInfoDlg.validateFields);
	 //初始化选项
    $("#level").val($("#levelVal").val());
    $("#pid").val($("#pidVal").val());
    $("#status").val($("#categoryStatus").val());
    if($("#levelVal").val()==1){
    	$("#pid").attr("disabled", "disabled");
    }else if($("#levelVal").val()==2){
    	$('#pid  option:eq(1)').attr("disabled", "disabled");
    }
   // console.log($("#pidVal").val());
    
    //$("#pic").val(1111);
    /*$("#driverInfoContent").find("div").eq(6).hide();
	$("#driverInfoContent").find("div").eq(7).hide();
	$("#driverInfoContent").find("div").eq(8).hide();*/
    
});
