/**
 * 商品图片管理的单例
 */
var Pic = {
    id: "PicTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Pic.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
        //{title: '照片', field: 'pic', align: 'center', valign: 'middle', sortable: true},
        {
    		title : '图片',
    		field : 'pic',
    		align : 'center',
    		valign : 'middle',
    		sortable : true,
    		formatter: function typeformatter(cellvalue, options, rowObject) {
    			if(cellvalue!=null && cellvalue!=undefined){
    				//var selectHtml="<img alt src= \""+Feng.ctxPath+"/upload_images/"+cellvalue+"/>";
    				var selectHtml="<img src= \""+"https://www.caiufh.com/cai_images/basicinfo/"+cellvalue+"\" alt='image'></img>";
    				//var selectHtml="<img src= \""+"http://111.229.181.74:8081/image/basicinfo/"+cellvalue+"\" alt='image'></img>";
    				//var selectHtml="<img src= \""+Feng.ctxPath+"/upload_images/basicinfo/"+cellvalue+"\" alt='image'></img>";
    				//var selectHtml="<img src= \""+Feng.ctxPath+"/upload_images/"+cellvalue+"\" width='47px' height='49px'></img>";
    				//var selectHtml=<img alt="image"  src="http://localhost:8080/upload_images/${user.avatar}" width='47px' height='49px'/>
    			}else {
    				var selectHtml="无";
    			}
                return selectHtml;
                }
    	},
        {title: '类别', field: 'picTypeStr', align: 'center', valign: 'middle', sortable: true},
        //{title: '说明', field: 'remark', align: 'center', valign: 'middle', sortable: true},
        //{title: '类型', field: 'type', align: 'center', valign: 'middle', sortable: true},
        {title: '添加时间', field: 'date_add', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '添加人', field: 'add_user', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '修改时间', field: 'date_update', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '修改人', field: 'update_user', visible: true, align: 'center', valign: 'middle', sortable: true}
        ]
    return columns;
};


/**
 * 检查是否选中
 */
Pic.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
    	Pic.seItem = selected[0];
        return true;
    }
};

/**
 * 取消封面
 */

Pic.openChange2 = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/basicinfo/cancel_cover", function () {
                Feng.success("取消成功!");
                Pic.table.refresh();
                window.parent.Basicinfo.table.refresh();
            }, function (data) {
                Feng.error("取消失败!" + data.responseJSON.message + "!");
            });
            ajax.set("picId", Pic.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否取消封面 " ,operation);
    }
};
/**
 * 设置封面
 */

Pic.openChange1 = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/basicinfo/cover", function () {
                Feng.success("设置成功!");
                Pic.table.refresh();
                window.parent.Basicinfo.table.refresh();
            }, function (data) {
                Feng.error("设置失败!" + data.responseJSON.message + "!");
            });
            ajax.set("picId", Pic.seItem.id);
            ajax.set("basicinfoId", basicinfoId);
            ajax.start();
        };
        Feng.confirm("是否设置封面" ,operation);
    }
};

/**
 * 删除信息
 */
Pic.del = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/basicinfo/pic_remove", function () {
                Feng.success("删除成功!");
                Pic.table.refresh();
                window.parent.Basicinfo.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("picId", Pic.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否删除 " ,operation);
    }
};
/**
 * 添加
 */
Pic.openAdd = function () {

    var index = layer.open({
        type: 2,
        title: '添加图片',
        area: ['500px', '300px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/basicinfo/pic_add/'+basicinfoId
    });
    this.layerIndex = index;
};

var basicinfoId = $("#basicinfoId").val();
$(function () {
    var defaultColunms = Pic.initColumn();
    
   
    var table = new BSTable(Pic.id, "/basicinfo/pic_list/"+basicinfoId, defaultColunms);
    table.setPaginationType("client");
    table.init();
    Pic.table = table;
});

