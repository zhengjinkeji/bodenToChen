/**
 * 初始化字典详情对话框
 */
var SpecInfoDlg = {
    count: $("#itemSize").val(),
    dictName: '',			//字典的名称
    mutiString: '',		//拼接字符串内容(拼接字典条目)
    typeName:'',
    itemTemplate: $("#itemTemplate").html()
};

/**
 * item获取新的id
 */
SpecInfoDlg.newId = function () {
    if(this.count == undefined){
        this.count = 0;
    }
    this.count = this.count + 1;
    return "dictItem" + this.count;
};

/**
 * 关闭此对话框
 */
SpecInfoDlg.close = function () {
    parent.layer.close(window.parent.Spec.layerIndex);
};

/**
 * 添加条目
 */
SpecInfoDlg.addItem = function () {
    $("#itemsArea").append(this.itemTemplate);
    $("#dictItem").attr("id", this.newId());
};

/**
 * 删除item
 */
SpecInfoDlg.deleteItem = function (event) {
    var obj = Feng.eventParseObject(event);
    obj = obj.is('button') ? obj : obj.parent();
    obj.parent().parent().remove();
};

/**
 * 清除为空的item Dom
 */
SpecInfoDlg.clearNullDom = function(){
    $("[name='dictItem']").each(function(){
        var num = $(this).find("[name='itemNum']").val();
        var name = $(this).find("[name='itemName']").val();
        if(num == '' || name == ''){
            $(this).remove();
        }
    });
};

/**
 * 收集添加的数据
 */
SpecInfoDlg.collectData = function () {
    this.clearNullDom();
    var mutiString = "";
    $("[name='dictItem']").each(function(){
        var num = $(this).find("[name='itemNum']").val();
        var name = $(this).find("[name='itemName']").val();
        var goodnum = $(this).find("[name='goodNum']").val();
        mutiString = mutiString + (num + ":"+ name + ":" +  goodnum+ ";");
    });
    this.dictName = $("#dictName").val();
    this.typeName = $("#typeName").val();
    this.mutiString = mutiString;
};


/**
 * 提交添加字典
 */
SpecInfoDlg.addSubmit = function () {
    this.collectData();
    if($("#typeName").val()==null||$("#typeName").val()==''){
    	alert("类别为空");
    	return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/spec/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Spec.table.refresh();
        SpecInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set('specName',this.dictName);
    ajax.set('typeName',this.typeName);
    ajax.set('specValues',this.mutiString);
    ajax.start();
};

/**
 * 提交修改
 */
SpecInfoDlg.editSubmit = function () {
    this.collectData();
    var ajax = new $ax(Feng.ctxPath + "/spec/update", function (data) {
        Feng.success("修改成功!");
        window.parent.Spec.table.refresh();
        SpecInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set('dictId',$("#dictId").val());
    ajax.set('dictName',this.dictName);
    ajax.set('typeName',this.typeName);
    ajax.set('dictValues',this.mutiString);
    ajax.start();
};
$(function() {
	 //初始化选项
    $("#typeName").val($("#typeNameVal").val());
});