/**
 * 发布管理的单例
 */
var Banner = {
    id: "BannerTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Banner.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '标题', field: 'title', align: 'center', valign: 'middle', sortable: true},
        //{title: '照片', field: 'pic', align: 'center', valign: 'middle', sortable: true},
        {
    		title : '照片',
    		field : 'pic',
    		align : 'center',
    		valign : 'middle',
    		sortable : true,
    		formatter: function typeformatter(cellvalue, options, rowObject) {
    			if(cellvalue!=null && cellvalue!=undefined){
    				//var selectHtml="<img alt src= \""+Feng.ctxPath+"/upload_images/"+cellvalue+"/>";
    				var selectHtml="<img src= \""+"https://www.caiufh.com/cai_images/banner/"+cellvalue+"\" alt='image'></img>";
    				//var selectHtml="<img src= \""+"http://111.229.181.74:8081/image/banner/"+cellvalue+"\" alt='image'></img>";
    				//var selectHtml="<img src= \""+Feng.ctxPath+"/upload_images/banner/"+cellvalue+"\" alt='image'></img>";
    				//var selectHtml="<img src= \""+Feng.ctxPath+"/upload_images/"+cellvalue+"\" width='47px' height='49px'></img>";
    				//var selectHtml=<img alt="image"  src="http://localhost:8080/image/banner/"+cellvalue+"\" alt='image'></img>";
    			}else {
    				var selectHtml="无";
    			}
                return selectHtml;
                }
    	},
        {title: '商品id', field: 'businessId', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'statusStr', align: 'center', valign: 'middle', sortable: true},
        {title: '说明', field: 'remark', align: 'center', valign: 'middle', sortable: true},
        {title: '类型', field: 'typeStr', align: 'center', valign: 'middle', sortable: true},
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
Banner.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
    	Banner.seItem = selected[0];
        return true;
    }
};

/**
 * 点击修改按钮时
 */
Banner.openChange = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改轮播信息',
            area: ['1000px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/banner/banner_edit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 点击更新图片按钮时
 */
Banner.editPic = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '更新轮播图片',
            area: ['500px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/banner/banner_edit_pic/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 点击添加banner信息
 */
Banner.openAdd = function () {

    var index = layer.open({
        type: 2,
        title: '添加轮播信息',
        area: ['1000px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/banner/banner_add'
    });
    this.layerIndex = index;
};

/**
 * 删除发布信息
 */
Banner.del = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/banner/remove", function () {
                Feng.success("删除成功!");
                Banner.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("bannerId", Banner.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否删除 " + Banner.seItem.title ,operation);
    }
};

/**
 * 搜索发布管理
 */
Banner.search = function () {
    var queryData = {};
    queryData['title'] = $("#title").val();
    Banner.table.refresh({query: queryData});
};

/**
 * 重置
 */
Banner.resetSearch = function () {
    $("#title").val("");
    Banner.search();
};

$(function () {
    var defaultColunms = Banner.initColumn();
    var table = new BSTable(Banner.id, "/banner/list", defaultColunms);
    table.setPaginationType("client");
    table.init();
    Banner.table = table;
});

