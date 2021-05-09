/**
 * 审核新用户管理的单例
 */
var Check = {
    id: "checkTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Check.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '账号', field: 'account', align: 'center', valign: 'middle', sortable: true},
        {title: '名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        //{title: '照片', field: 'pic', align: 'center', valign: 'middle', sortable: true},
        {
    		title : '身份证正面',
    		field : 'card1',
    		align : 'center',
    		valign : 'middle',
    		sortable : true,
    		formatter: function typeformatter(cellvalue, options, rowObject) {
    			if(cellvalue!=null && cellvalue!=undefined){
    				//var selectHtml="<img alt src= \""+Feng.ctxPath+"/upload_images/"+cellvalue+"/>";
    				var selectHtml="<a target='_blank' href=\""+"https://www.caiufh.com/cai_images/user_info/"+cellvalue+"\"><img src= \""+"https://www.caiufh.com/cai_images/user_info/"+cellvalue+"\" onclick='card1("+"\""+cellvalue+"\")' alt='image'></img></a>";
    				//var selectHtml="<a target='_blank' href=\""+"http://111.229.181.74:8081/image/user_info/"+cellvalue+"\"><img src= \""+"http://111.229.181.74:8081/image/user_info/"+cellvalue+"\" alt='image'></img></a>";
    				//var selectHtml=<img alt="image"  src="http://localhost:8080/image/banner/"+cellvalue+"\" alt='image'></img>";
    			}else {
    				var selectHtml="无";
    			}
                return selectHtml;
                }
    	},
    	{
    		title : '身份证反面',
    		field : 'card2',
    		align : 'center',
    		valign : 'middle',
    		sortable : true,
    		formatter: function typeformatter(cellvalue, options, rowObject) {
    			if(cellvalue!=null && cellvalue!=undefined){
    				//var selectHtml="<img alt src= \""+Feng.ctxPath+"/upload_images/"+cellvalue+"/>";
    				//var selectHtml="<img src= \""+"https://www.wscyun.cn/image/user_info/"+cellvalue+"\"  alt='image'></img>";
    				var selectHtml="<a target='_blank' href=\""+"https://www.caiufh.com/cai_images/user_info/"+cellvalue+"\"><img src= \""+"https://www.caiufh.com/cai_images/user_info/"+cellvalue+"\" alt='image'></img></a>";
    				//var selectHtml="<a target='_blank' href=\""+"http://111.229.181.74:8081/image/user_info/"+cellvalue+"\"><img src= \""+"http://111.229.181.74:8081/image/user_info/"+cellvalue+"\" alt='image'></img></a>";
    				//var selectHtml="<img src= \""+"http://localhost:8080/image/banner/"+cellvalue+"\" alt='image'></img>";
    				//var selectHtml=<img alt="image"  src="http://localhost:8080/image/banner/"+cellvalue+"\" alt='image'></img>";
    			}else {
    				var selectHtml="无";
    			}
                return selectHtml;
                }
    	},
    	{
    		title : '营业执照副本',
    		field : 'card3',
    		align : 'center',
    		valign : 'middle',
    		sortable : true,
    		formatter: function typeformatter(cellvalue, options, rowObject) {
    			if(cellvalue!=null && cellvalue!=undefined){
    				//var selectHtml="<img alt src= \""+Feng.ctxPath+"/upload_images/"+cellvalue+"/>";
    				//var selectHtml="<img src= \""+"https://www.wscyun.cn/image/user_info/"+cellvalue+"\" alt='image'></img>";
    				var selectHtml="<a target='_blank' href=\""+"https://www.caiufh.com/cai_images/user_info/"+cellvalue+"\"><img src= \""+"https://www.caiufh.com/cai_images/user_info/"+cellvalue+"\" alt='image'></img></a>";
    				//var selectHtml="<a target='_blank' href=\""+"http://111.229.181.74:8081/image/user_info/"+cellvalue+"\"><img src= \""+"http://111.229.181.74:8081/image/user_info/"+cellvalue+"\" alt='image'></img></a>";
    				//var selectHtml="<img src= \""+"http://localhost:8080/image/banner/"+cellvalue+"\" alt='image'></img>";
    				//var selectHtml=<img alt="image"  src="http://localhost:8080/image/banner/"+cellvalue+"\" alt='image'></img>";
    			}else {
    				var selectHtml="无";
    			}
                return selectHtml;
                }
    	},
        {title: '状态', field: 'statusStr', align: 'center', valign: 'middle', sortable: true},
        {title: '添加时间', field: 'createtime', align: 'center', valign: 'middle', sortable: true},
        ]
    return columns;
};

function card1(pic){
	//console.log(pic)
	//window.location.href="https://www.wscyun.cn/image/user_info/"+pic
}

/**
 * 检查是否选中
 */
Check.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
    	Check.seItem = selected[0];
        return true;
    }
};


/**
 * 点击查看图片按钮时
 */
Check.checkPic = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '图片',
            area: ['1000px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/check/checkPic/' + this.seItem.account
        });
        this.layerIndex = index;
    }
};


/**
 * 通过
 */
Check.pass = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/check/pass", function () {
                Feng.success("审核成功!");
                Check.table.refresh();
            }, function (data) {
                Feng.error("审核失败!" + data.responseJSON.message + "!");
            });
            ajax.set("userId", Check.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否通过 " + Check.seItem.account ,operation);
    }
};
/**
 * 不通过
 */
Check.reject = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/check/delete", function () {
                Feng.success("驳回成功!");
                Check.table.refresh();
            }, function (data) {
                Feng.error("驳回失败!" + data.responseJSON.message + "!");
            });
            ajax.set("userId", Check.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否驳回 " + Check.seItem.account ,operation);
    }
};
/**
 * 搜索发布管理
 */
Check.search = function () {
    var queryData = {};
    queryData['title'] = $("#title").val();
    Check.table.refresh({query: queryData});
};

/**
 * 重置
 */
Check.resetSearch = function () {
    $("#title").val("");
    Check.search();
};

$(function () {
    var defaultColunms = Check.initColumn();
    var table = new BSTable(Check.id, "/check/list", defaultColunms);
    table.setPaginationType("client");
    table.init();
    Check.table = table;
});

