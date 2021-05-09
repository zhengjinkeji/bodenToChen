/**
 * 商品图片管理的单例
 */
var NumSpec = {
    id: "PicTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
NumSpec.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '范围', field: 'scope', align: 'center', valign: 'middle', sortable: true},
        {title: '系数', field: 'xishu', align: 'center', valign: 'middle', sortable: true},
        //{title: '说明', field: 'remark', align: 'center', valign: 'middle', sortable: true},
        //{title: '类型', field: 'type', align: 'center', valign: 'middle', sortable: true},
        {title: '添加时间', field: 'create_time', visible: true, align: 'center', valign: 'middle', sortable: true},
        //{title: '添加人', field: 'add_user', visible: true, align: 'center', valign: 'middle', sortable: true}
        ]
    return columns;
};


/**
 * 检查是否选中
 */
NumSpec.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
    	NumSpec.seItem = selected[0];
        return true;
    }
};
/**
 * 点击修改按钮时
 */
NumSpec.openChange = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改数量配置',
            area: ['400px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/basicinfo/num_spec_edit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};


/**
 * 删除信息
 */
NumSpec.del = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/basicinfo/remove_num_spec", function () {
                Feng.success("删除成功!");
                NumSpec.table.refresh();
                window.parent.NumSpec.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", NumSpec.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否删除 " ,operation);
    }
};

/**
 * 添加
 */
NumSpec.openAdd = function () {

    var index = layer.open({
        type: 2,
        title: '添加数量配置',
        area: ['400px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/basicinfo/num_spec_add/'+basicinfoId
    });
    this.layerIndex = index;
};

var basicinfoId = $("#basicinfoId").val();
$(function () {
    var defaultColunms = NumSpec.initColumn();   
    var table = new BSTable(NumSpec.id, "/basicinfo/num_spec_list/"+basicinfoId, defaultColunms);
    table.setPaginationType("client");
    table.init();
    NumSpec.table = table;
});

