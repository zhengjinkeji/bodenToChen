/**
 * 通知管理初始化
 */
var Type = {
    id: "TypeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Type.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '品类名', field: 'typeName', align: 'center', valign: 'middle', sortable: true},
        {title: '发布者', field: 'createBy', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createDate', align: 'center', valign: 'middle', sortable: true},
        {title: '备注', field: 'remark', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Type.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Type.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加通知
 */
Type.openAddNotice = function () {
    var index = layer.open({
        type: 2,
        title: '添加品类',
        area: ['800px', '300px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/notice/type_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看通知详情
 */
Type.openNoticeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '资料修改',
            area: ['800px', '300px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/notice/type_update/' + Type.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除通知
 */
Type.delete = function () {
    if (this.check()) {

        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/notice/deleteType", function (data) {
                Feng.success("删除成功!");
                Type.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("typeId", Type.seItem.id);
            ajax.start();
        };

        Feng.confirm("是否删除 " + Type.seItem.typeName + "?", operation);
    }
};

/**
 * 查询通知列表
 */
Type.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Type.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Type.initColumn();
    var table = new BSTable(Type.id, "/notice/typeList", defaultColunms);
    table.setPaginationType("client");
    Type.table = table.init();
});
