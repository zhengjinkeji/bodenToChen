/**
 * 管理初始化
 */
var Spec = {
    id: "SpecTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Spec.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        //{title: '详情', field: 'detail', align: 'center', valign: 'middle', sortable: true},
        {
            title : '详情',
            field : 'detail',
            align : 'center',
            valign : 'middle',
            sortable : true,
            formatter: function typeformatter(cellvalue, options, rowObject) {
                if(cellvalue!=null && cellvalue!=undefined){
                   var selectHtml=cellvalue.substring(0, 20)+"......"
                }else {
                    var selectHtml="无";
                }
                return selectHtml;
            }
        },
        {title: '类别', field: 'typeName', align: 'center', valign: 'middle', sortable: true},
        {title: '添加时间', field: 'dateAdd', align: 'center', valign: 'middle', sortable: true},
        {title: '添加人', field: 'addUser', align: 'center', valign: 'middle', sortable: true},
        //{title: '修改时间', field: 'dateUpdate', align: 'center', valign: 'middle', sortable: true},
        //{title: '修改人', field: 'updateUser', align: 'center', valign: 'middle', sortable: true}
        ];
};

/**
 * 检查是否选中
 */
Spec.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
    	Spec.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Spec.openAddDict = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['900px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/spec/spec_add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改
 */
Spec.openChange = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/spec/spec_edit/' + Spec.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Spec.del = function () {
    if (this.check()) {

        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/spec/delete", function (data) {
                Feng.success("删除成功!");
                Spec.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("specId", Spec.seItem.id);
            ajax.start();
        };

        Feng.confirm("是否刪除 " + Spec.seItem.name + "?", operation);
    }
};
/**
 * 重置
 */
Spec.resetSearch = function () {
    $("#condition").val("");
    Spec.search();
};
/**
 * 查询列表
 */
Spec.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Spec.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Spec.initColumn();
    var table = new BSTable(Spec.id, "/spec/list", defaultColunms);
    table.setPaginationType("client");
    Spec.table = table.init();
});
