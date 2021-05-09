/**
 * 通知管理初始化
 */
var Meterial = {
    id: "MeterialTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Meterial.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '文件名', field: 'meterial_name', align: 'center', valign: 'middle', sortable: true},
         {title: '链接地址', field: 'url', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Meterial.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Meterial.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加本地文件
 */
Meterial.openAddNotice = function () {
    var index = layer.open({
        type: 2,
        title: '本地资料上传',
        area: ['800px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/notice/meterial_add'
    });
    this.layerIndex = index;
};
/**
 * 点击网络连接文件
 */
Meterial.openAddLinkNotice = function () {
    var index = layer.open({
        type: 2,
        title: '链接资料上传',
        area: ['800px', '300px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/notice/net_meterial_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看通知详情
 */
Meterial.openNoticeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '资料修改',
            area: ['800px', '800px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/notice/meterial_update/' + Meterial.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除通知
 */
Meterial.delete = function () {
    if (this.check()) {

        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/notice/deleteMeterial", function (data) {
                Feng.success("删除成功!");
                Meterial.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("noticeId", Meterial.seItem.id);
            ajax.start();
        };

        Feng.confirm("是否删除 " + Meterial.seItem.meterial_name + "?", operation);
    }
};

/**
 * 查询通知列表
 */
Meterial.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Meterial.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Meterial.initColumn();
    var table = new BSTable(Meterial.id, "/notice/meterialList", defaultColunms);
    table.setPaginationType("client");
    Meterial.table = table.init();
});
