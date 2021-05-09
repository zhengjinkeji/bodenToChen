/**
 * 发布管理的单例
 */
var Category = {
    id: "CategoryTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Category.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '上级类别', field: 'pname', align: 'center', valign: 'middle', sortable: true},
        //{title: '照片', field: 'pic', align: 'center', valign: 'middle', sortable: true},
        {
    		title : '图标',
    		field : 'icon',
    		align : 'center',
    		valign : 'middle',
    		sortable : true,
    		formatter: function typeformatter(cellvalue, options, rowObject) {
    			if(cellvalue!=null && cellvalue!='' && cellvalue!=undefined){
    				//var selectHtml="<img alt src= \""+Feng.ctxPath+"/upload_images/"+cellvalue+"/>";
    				//var selectHtml="<img src= \""+"http://111.229.181.74:8081/image/category/"+cellvalue+"\" alt='image'></img>";
    				var selectHtml="<img src= \""+"https://www.caiufh.com/cai_images/category/"+cellvalue+"\" alt='image'></img>";
    				//var selectHtml="<img src= \""+Feng.ctxPath+"/upload_images/category/"+cellvalue+"\" alt='image'></img>";
    				//var selectHtml="<img src= \""+Feng.ctxPath+"/upload_images/"+cellvalue+"\" width='47px' height='49px'></img>";
    				//var selectHtml=<img alt="image"  src="http://localhost:8080/upload_images/${user.avatar}" width='47px' height='49px'/>
    			}else {
    				var selectHtml="无";
    			}
                return selectHtml;
                }
    	},
        {title: '层级', field: 'levelStr', align: 'center', valign: 'middle', sortable: true},
        //{title: '序号', field: 'num', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'statusStr', align: 'center', valign: 'middle', sortable: true},
        {title: '说明', field: 'remark', align: 'center', valign: 'middle', sortable: true},
        //{title: '类型', field: 'type', align: 'center', valign: 'middle', sortable: true},
        {title: '添加时间', field: 'dateAdd', align: 'center', valign: 'middle', sortable: true},
        {title: '添加人', field: 'addUser', align: 'center', valign: 'middle', sortable: true},
        {title: '修改时间', field: 'dateUpdate', align: 'center', valign: 'middle', sortable: true},
        {title: '修改人', field: 'updateUser', align: 'center', valign: 'middle', sortable: true}
        ]
    return columns;
};


/**
 * 检查是否选中
 */
Category.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
    	Category.seItem = selected[0];
        return true;
    }
};

/**
 * 点击修改按钮时
 */
Category.openChange = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改类别信息',
            area: ['1000px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/category/category_edit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 点击更新图片按钮时
 */
Category.editPic = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '更新类别图片',
            area: ['500px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/category/category_edit_pic/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 点击添加信息
 */
Category.openAdd = function () {

    var index = layer.open({
        type: 2,
        title: '添加类别信息',
        area: ['1000px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/category/category_add'
    });
    this.layerIndex = index;
};

/**
 * 删除管理信息
 */
Category.del = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/category/remove", function () {
                Feng.success("删除成功!");
                Category.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("categoryId", Category.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否删除 " + Category.seItem.name ,operation);
    }
};

/**
 * 搜索发布管理
 */
Category.search = function () {
    var queryData = {};
    queryData['name'] = $("#name").val();
    Category.table.refresh({query: queryData});
};

/**
 * 重置
 */
Category.resetSearch = function () {
    $("#name").val("");
    Category.search();
};

$(function () {
    var defaultColunms = Category.initColumn();
    var table = new BSTable(Category.id, "/category/list", defaultColunms);
    table.setPaginationType("client");
    table.init();
    Category.table = table;
});

