/**
 * 商品管理的单例
 */
var Basicinfo = {
    id: "BasicinfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Basicinfo.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        //{title: '照片', field: 'pic', align: 'center', valign: 'middle', sortable: true},
        {
    		title : '封面图片',
    		field : 'pic',
    		align : 'center',
    		valign : 'middle',
    		sortable : true,
    		formatter: function typeformatter(cellvalue, options, rowObject) {
    			if(cellvalue!=null && cellvalue!=undefined){
    				//var selectHtml="<img alt src= \""+Feng.ctxPath+"/upload_images/"+cellvalue+"/>";
    				//var selectHtml="<img src= \""+"http://111.229.181.74:8081/image/basicinfo/"+cellvalue+"\" alt='image'></img>";
    				var selectHtml="<img src= \""+"https://www.caiufh.com/cai_images/basicinfo/"+cellvalue+"\" alt='image'></img>";
    				//var selectHtml="<img src= \""+Feng.ctxPath+"/upload_images/basicinfo/"+cellvalue+"\" alt='image'></img>";
    				//var selectHtml="<img src= \""+Feng.ctxPath+"/upload_images/"+cellvalue+"\" width='47px' height='49px'></img>";
    				//var selectHtml=<img alt="image"  src="http://localhost:8080/upload_images/${user.avatar}" width='47px' height='49px'/>
    			}else {
    				var selectHtml="无";
    			}
                return selectHtml;
                }
    	},
        {title: '类别', field: 'categoryName', align: 'center', valign: 'middle', sortable: true},
        {title: '最低价', field: 'minPrice',visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '原价', field: 'originalPrice',visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '喜豆价', field: 'xidou', align: 'center', valign: 'middle', sortable: true},
        {title: '已售', field: 'numberOrders', align: 'center', valign: 'middle', sortable: true},
        {title: '库存', field: 'store', align: 'center', valign: 'middle', sortable: true},
        {title: '积分', field: 'score',visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '特征', field: 'characteristic', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'statusStr', align: 'center', valign: 'middle', sortable: true},
        {title: '首页推荐', field: 'recommendStr', align: 'center', valign: 'middle', sortable: true},
        {title: '箱规格', field: 'box', align: 'center', valign: 'middle', sortable: true},
        {title: '说明', field: 'remark', visible: false,align: 'center', valign: 'middle', sortable: true},
        //{title: '类型', field: 'type', align: 'center', valign: 'middle', sortable: true},
        {title: '添加时间', field: 'dateAdd', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '添加人', field: 'addUser', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '修改时间', field: 'dateUpdate', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '修改人', field: 'updateUser', visible: false, align: 'center', valign: 'middle', sortable: true}
        ]
    return columns;
};


/**
 * 检查是否选中
 */
Basicinfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
    	Basicinfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击修改按钮时
 */
Basicinfo.openChange = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改商品信息',
            area: ['1000px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/basicinfo/basicinfo_edit_xd/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 添加图片
 */
Basicinfo.addPic = function () {
	if (this.check()) {
	    var index = layer.open({
	        type: 2,
	        title: '添加图片',
	        area: ['500px', '300px'], //宽高
	        fix: false, //不固定
	        maxmin: true,
	        content: Feng.ctxPath + '/basicinfo/pic_add2/'+this.seItem.id
	    });
	    this.layerIndex = index;
	  }
};
/**
 * 点击更新图片按钮时
 */
Basicinfo.editPic = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '商品图片',
            area: ['1000px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/basicinfo/basicinfo_edit_pic/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 点击添加Basicinfo信息
 */
Basicinfo.openAdd = function () {

    var index = layer.open({
        type: 2,
        title: '添加商品信息',
        area: ['1000px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/basicinfo/basicinfo_add_xd'
    });
    this.layerIndex = index;
};
/**
 * 规格配置
 */
Basicinfo.assign = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '规格配置',
            area: ['300px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/basicinfo/basicinfo_assign/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 删除发布管理信息
 */
Basicinfo.del = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/basicinfo/remove", function () {
                Feng.success("删除成功!");
                Basicinfo.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("basicinfoId", Basicinfo.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否删除 " + Basicinfo.seItem.name ,operation);
    }
};
/**
 * 设置首页推荐
 */
Basicinfo.recommend = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/basicinfo/recommend", function () {
                Feng.success("设置成功!");
                Basicinfo.table.refresh();
            }, function (data) {
                Feng.error("设置成功!" + data.responseJSON.message + "!");
            });
            ajax.set("id", Basicinfo.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否设置为首页推荐商品",operation);
    }
};
/**
 * 搜索管理
 */
Basicinfo.search = function () {
    var queryData = {};
    queryData['name'] = $("#name").val();
    Basicinfo.table.refresh({query: queryData});
};

/**
 * 重置
 */
Basicinfo.resetSearch = function () {
    $("#name").val("");
    Basicinfo.search();
};

$(function () {
    var defaultColunms = Basicinfo.initColumn();
    var table = new BSTable(Basicinfo.id, "/basicinfo/list_xd", defaultColunms);
    table.setPaginationType("client");
    table.init();
    Basicinfo.table = table;
});

